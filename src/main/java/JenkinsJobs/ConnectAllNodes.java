package JenkinsJobs;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ConnectAllNodes {
	static String sheetName = "ConnectNodes";

	static String filePath = "C:\\Users\\VS065203\\OneDrive - Cerner Corporation\\Desktop\\CentralizedJenkins\\JobCreation\\CJ_AllData.xlsx";

	static String username = null;

	static String password = null;

	static Workbook wb = null;

	static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) throws EncryptedDocumentException, IOException {

		if (filePath == null) {
			System.out.print("Enter the Excel Path : ");

			filePath = input.nextLine();

			System.out.println();
		}
		if (sheetName == null) {
			System.out.print("Enter the SheetName : ");

			sheetName = input.nextLine();

			System.out.println();
		}
	
		FileInputStream inputFile = new FileInputStream(filePath);

		wb = WorkbookFactory.create(inputFile);

		int LastRow = wb.getSheet(sheetName).getLastRowNum();

		System.out.print("Total Number of records in excel : " + LastRow + "\n\n");
		
		try {
			for (int i = 1; i <= LastRow; i++) {

				String nodeLink = GetNodeLinkFromExcel(wb, sheetName, i, 1);
				
				String nodeLinkNum = GetNodeLinkFromExcel(wb, sheetName, i, 0);
				
				Thread.sleep(2000);
				
				String nodeParameter=
						"<hudson.model.StringParameterDefinition>\r\n"
						+ "<name>"+nodeLinkNum+"</name>\r\n"
						+ "<description/>\r\n"
						+ "<defaultValue>"+nodeLink+"</defaultValue>\r\n"
						+ "<trim>false</trim>\r\n"
						+ "</hudson.model.StringParameterDefinition>";
				
				String connectNode=
						"<org.jenkinsci.plugins.postbuildscript.model.PostBuildStep>\r\n"
						+ "<results>\r\n"
						+ "<string>SUCCESS</string>\r\n"
						+ "</results>\r\n"
						+ "<role>SLAVE</role>\r\n"
						+ "<buildSteps>\r\n"
						+ "<hudson.tasks.BatchFile>\r\n"
						+ "<command>cd c:/Temp&#xD;javaw -jar EConnect.jar \"%"+nodeLinkNum+"%\" \"%uname%\" \"%upass%\"</command>\r\n"
						+ "<configuredLocalRules/>\r\n"
						+ "</hudson.tasks.BatchFile>\r\n"
						+ "</buildSteps>\r\n"
						+ "<stopOnFailure>false</stopOnFailure>\r\n"
						+ "</org.jenkinsci.plugins.postbuildscript.model.PostBuildStep>";
		
				System.out.println(nodeParameter);//nodeParameter  connectNode
			}

			input.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}
			public static String GetNodeLinkFromExcel(Workbook wb, String Sheet, int Row, int col) {
				return wb.getSheet(sheetName).getRow(Row).getCell(col).toString();
			}
	
}
