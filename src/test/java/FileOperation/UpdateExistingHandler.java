package FileOperation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class UpdateExistingHandler {

	public static void main(String[] args) throws IOException {

		Scanner input = new Scanner(System.in);

		System.out.print("Enter the repo Path : ");

		String rootPath = input.nextLine();

		System.out.println();

		System.out.print("Enter the Type of File : ");

		String fileType = input.nextLine();

		System.out.println();

		String xlPath = ".\\Mill_RepoChange.xlsx";

		String sheetName = "Handler-Mapper";

		Workbook wb = WorkbookFactory.create(new FileInputStream(xlPath));

		int LastRow = wb.getSheet(sheetName).getLastRowNum();

		System.out.print("Total Number Lines in excel at given path is " + xlPath + " :- " + LastRow + "\n\n");

		HashMap<String, String> hmap = new HashMap<>();

		for (int i = 1; i <= LastRow + 1; i++) {

			try {
				if ((getHandlerFromXl(wb, sheetName, i, 0) != null) || (getHandlerFromXl(wb, sheetName, i, 1) != null)
						|| (getHandlerFromXl(wb, sheetName, i, 2) != null)

						|| (getHandlerFromXl(wb, sheetName, i, 3) != null)
						|| (getHandlerFromXl(wb, sheetName, i, 4) != null)) {

					hmap.put(getHandlerFromXl(wb, sheetName, i, 0), getHandlerFromXl(wb, sheetName, i, 4));

					hmap.put(getHandlerFromXl(wb, sheetName, i, 1), getHandlerFromXl(wb, sheetName, i, 4));

					hmap.put(getHandlerFromXl(wb, sheetName, i, 2), getHandlerFromXl(wb, sheetName, i, 4));

					hmap.put(getHandlerFromXl(wb, sheetName, i, 3), getHandlerFromXl(wb, sheetName, i, 4));
				}
			} catch (Exception e) { // e.printStackTrace();
			}
		}
		// hmap.forEach((key, value) -> System.out.println(key + " : " + value));

		// System.out.println(hmap.size());

		List<String> lst = new ArrayList<String>();

		lst = getFilesInDir(rootPath);

		System.out.print("Total Number of Files in the given Path " + rootPath + " is : " + lst.size() + "\n");

		for (int i = 0; i < lst.size(); i++) {

			String cFile = lst.get(i);

			if (cFile.contains(fileType)) {

				System.out.println("\nUpdating the Existing Handlers With new one's in the File - " + cFile);

				hmap.forEach((key, value) -> {
					try {
						replaceFileContent(rootPath + cFile, key, value);
					} catch (IOException e) { // e.printStackTrace();
					}
				});
			}
		}

		input.close();
	}

	public static String getHandlerFromXl(Workbook wb, String xlSheet, int xlRow, int xlCol) {
		return wb.getSheet(xlSheet).getRow(xlRow).getCell(xlCol).toString();
	}

	public static void replaceFileContent(String fileName, String oldLine, String newLine) throws IOException {

		Scanner sc = new Scanner(new File(fileName));

		// instantiating the StringBuffer class
		StringBuffer buffer = new StringBuffer();

		// Reading lines of the file and appending them to StringBuffer
		while (sc.hasNextLine()) {
			buffer.append(sc.nextLine() + System.lineSeparator());
		}

		String fileContents = buffer.toString();
		// System.out.println("Contents of the file: "+fileContents);

		// closing the Scanner object
		sc.close();

		// Replacing the old line with new line
		fileContents = fileContents.replaceAll("(?i)" + oldLine.toLowerCase(), newLine);

		// instantiating the FileWriter class
		FileWriter writer = new FileWriter(new File(fileName));
		// System.out.println("");
		// System.out.println("new data: "+fileContents);
		writer.append(fileContents);
		writer.flush();
	}

	public static List<String> getFilesInDir(String dirPath) throws IOException {

		List<String> lst = new ArrayList<String>();

		Path dir = FileSystems.getDefault().getPath(dirPath);

		DirectoryStream<Path> stream = Files.newDirectoryStream(dir);

		for (Path path : stream)
			lst.add(path.getFileName().toString());

		stream.close();

		return lst;
	}
}
