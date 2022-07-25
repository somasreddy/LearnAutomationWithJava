package FileOperation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChangeFileContents{


	public static void main(String[] args) throws IOException {

		ChangeFileContents cmr=new ChangeFileContents();

		List<String> lst=new ArrayList<String>();

		Scanner input = new Scanner(System.in);

		System.out.print("Enter the repo Path : ");

		String rootPath=input.nextLine();//"C:\\EggplantSuites\\IPDev-MPages-CareCompass\\IPDev-MPages-CareCompass.suite\\Resources\\";
		System.out.println();

		System.out.print("Enter the Type of File : ");	

		String fileType = input.nextLine();	

		System.out.println();

		System.out.print("Enter the String to be Updated : ");

		String oldLine = input.nextLine();

		System.out.println();

		System.out.print("Enter the String to replace with : ");

		String newLine = input.nextLine();

		System.out.println();

		lst=cmr.getFilesInDir(rootPath);

		System.out.print("Total Number of Files in the given Path "+rootPath+" is : "+lst.size()+"\n");

		System.out.println();

		for(int i=0; i<lst.size();i++) {

			String cFile=lst.get(i);

			if (cFile.contains(fileType)) {

				System.out.println("Updating the content in the File - "+cFile+"\n");

				cmr.replaceFileContent(rootPath+cFile, oldLine, newLine);	
			}

		}
		input.close();
	}


	public void replaceFileContent(String fileName,String oldLine, String newLine ) throws IOException {

		Scanner sc = new Scanner(new File(fileName));

		//instantiating the StringBuffer class
		StringBuffer buffer = new StringBuffer();	

		//Reading lines of the file and appending them to StringBuffer
		while (sc.hasNextLine()) {
			buffer.append(sc.nextLine()+System.lineSeparator());
		}

		String fileContents = buffer.toString();
		//System.out.println("Contents of the file: "+fileContents);

		//closing the Scanner object
		sc.close();

		//Replacing the old line with new line
		fileContents = fileContents.replaceAll(oldLine, newLine);

		//instantiating the FileWriter class
		FileWriter writer = new FileWriter(new File(fileName));
		System.out.println("");
		//System.out.println("new data: "+fileContents);
		writer.append(fileContents);
		writer.flush();
	}

	public List<String> getFilesInDir(String dirPath) throws IOException {

		List<String> lst=new ArrayList<String>();

		Path dir = FileSystems.getDefault().getPath(dirPath);

		DirectoryStream<Path> stream = Files.newDirectoryStream( dir );

		for (Path path : stream) 
			lst.add(path.getFileName().toString());

		stream.close();

		return lst;
	}

}
