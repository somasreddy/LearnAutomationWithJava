package JenkinsJobs;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelData {
	static String sheetName="1501-List";
	static String filePath="C:\\com.RQM\\Exe\\MRD-1501-Plan-List.xls";

	public static void main(String[] args) throws InvalidFormatException, IOException {
		FileInputStream inputFile=new FileInputStream(filePath);
		Workbook wb=WorkbookFactory.create(inputFile);
		int LastRow=wb.getSheet(sheetName).getLastRowNum();
		for(int i=1;i<=LastRow;i++) {
			int LastCell=wb.getSheet(sheetName).getRow(i).getLastCellNum();
			  for(int j=0;j<1;j++) { 
				  Cell  CellVal=wb.getSheet(sheetName).getRow(i).getCell(0); 
				  System.out.println(CellVal+" "); 
			  }
		}
		for(int i=1;i<=LastRow;i++) {
			int LastCell=wb.getSheet(sheetName).getRow(i).getLastCellNum();
			  for(int j=0;j<1;j++) { 
				  Cell  CellVal=wb.getSheet(sheetName).getRow(i).getCell(2); 
				  System.out.println(CellVal+" "); 
			  }
		}
	}

}
