package RQMLinks;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class UpdateTCWithRQMLinks {
	
	static String repoPath = null;
	
	static String tCName = null;
	
	static String tcLink = null;
	
	static String sheetName = null;
	  
    static String filePath = null;
    
    static Workbook wb = null;
	  
	static Scanner input = new Scanner(System.in);
	
	public String GetTCFromExcel(Workbook wb, String Sheet, int Row) { return wb.getSheet(sheetName).getRow(Row).getCell(0).toString(); }
	
	public String GetDataFromExcel(Workbook wb, String Sheet, int Row, int Col) { return wb.getSheet(sheetName).getRow(Row).getCell(Col).toString(); }
	  
public static void main(String[] args) throws IOException, InvalidFormatException {
	
	HashSet hs= new HashSet<String>();
	
	UpdateTCWithRQMLinks utc = new UpdateTCWithRQMLinks();
		  
	  System.out.print("Enter the Excel Path : ");
	  
	  filePath = input.nextLine();
	  
	  System.out.println();
	  
	  System.out.print("Enter the SheetName : ");
	  
	  sheetName = input.nextLine();
	  
	  System.out.println();
	  
	  System.out.print("Enter the repo Path : ");
	  
	  repoPath = input.nextLine();
	  
	  System.out.println();
	  
	  FileInputStream inputFile = new FileInputStream(filePath);
	  
	  wb = WorkbookFactory.create(inputFile);
	  
	  int LastRow = wb.getSheet(sheetName).getLastRowNum();
	  
	  System.out.print("Total Number of Scripts in excel : " + LastRow + "\n\n");
	  	  
	  for (int i = 1; i <= LastRow; i++) {
	  
	  tCName = utc.GetDataFromExcel(wb, sheetName, i, 0);
	  
	  tcLink= utc.GetDataFromExcel(wb, sheetName, i, 1);
	  
	  String status=utc.UpdateFile(repoPath,tCName,tcLink);
	  
	  System.out.println(status);  
	  
	  utc.writeToExcelFile(filePath,sheetName,status,i,2);
	  
	  System.out.println("\n<====================================================================================>\n");
	  
	  	}
	  }

public static String readTextFile(String fileName) throws IOException {
    String content = new String(Files.readAllBytes(Paths.get(fileName)));
    return content;
}

public static List<String> readTextFileByLines(String fileName) throws IOException {
    List<String> lines = Files.readAllLines(Paths.get(fileName));
    return lines;
}

public static void writeToTextFile(String fileName, String content) throws IOException {
    Files.write(Paths.get(fileName), content.getBytes(), StandardOpenOption.CREATE);
}

public String UpdateFile(String Path,String TCName,String RQMLink) throws IOException {
	
	String filePath=Path+"\\"+TCName+".script";
		
	File file = new File(filePath);
	
	String Match=("Script Name:"+TCName).toLowerCase();
	String Match1=("Script Name : "+TCName).toLowerCase();
	String Match2=("Script Name: "+TCName).toLowerCase();
	String Match3=("Script Name :"+TCName).toLowerCase();
	String Match4=("ScriptName:"+TCName).toLowerCase();
	String Match5=("ScriptName: "+TCName).toLowerCase();
	String Match6=("ScriptName : "+TCName).toLowerCase();
	String Match7=("ScriptName :"+TCName).toLowerCase();
	String Match8="Script Name".toLowerCase();
	String Match9="ScriptName".toLowerCase();
			
	String updateStatus = "No File Found with Given Name: "+TCName;
	
	try {
	    Scanner scanner = new Scanner(file);
	    
	    List<String> lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
	    
	    int lineNum=0;
	    
	    for (int k=0;k<=lines.size();k++) {
	    	
	    	lineNum=k;
	    	
	    	String line = lines.get(lineNum).toLowerCase();
	    		    	
	    	String oldLine = readTextFileByLines(filePath).get(lineNum); //To fetch the line which is matched
	        
	    	if(line.contains(Match)||line.contains(Match1)||line.contains(Match2)||line.contains(Match3)||line.contains(Match4)||line.contains(Match5)
	    			||line.contains(Match6)||line.contains(Match7)||line.contains(Match8)||line.contains(Match9)&&!(lines.contains("RQM".toLowerCase()))) { 
	            
	            System.out.println("ScriptName found at line number: " +lineNum +" as "+oldLine);
	            
	            System.out.println("\n----------------------------------\n");
	  	      	
	            String newLine=oldLine+"\n"+"RQM test case link: "+ RQMLink;
	            
	            String newLine1="#RQM test case link: "+RQMLink+"\n"+oldLine;
	            
	            setVariable(filePath,lineNum,newLine);
	            
	            updateStatus="\n"+TCName+" Updated Successfully with RQM Link";
	  	        
	            for(int i=0;i<=(lineNum+5);i++) {
	  	        
	            	System.out.println(readTextFileByLines(filePath).get(i));
	  	        }

	  	    }
	    	else if (line.contains("RQM".toLowerCase())||line.contains("RQM ".toLowerCase())||line.contains("RQM test case link".toLowerCase())) {
	        	
	    		updateStatus=TCName+" Already contains RQM ID/Link at line number: "+lineNum+" as "+oldLine ;
	    		
	    		System.out.println("\n----------------------------------\n");
	        }
	      }
	    
	    scanner.close();
	    
	} catch(Exception e) { 
		
		updateStatus="Invalid File/No File Found with the Given Name: "+TCName;
		
		System.out.println("\n----------------------------------\n");
		
		e.printStackTrace();
	}
	return updateStatus;
	}

public static void setVariable(String filePath,int lineNumber, String data) throws IOException {
    Path path = Paths.get(filePath);
    List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
    lines.set(lineNumber, data);
    Files.write(path, lines, StandardCharsets.UTF_8);
}

    
public void writeToExcelFile(String filePath, String sheetName, Object DatatoWrite, int row, int col) throws IOException {
	    try {
	      FileInputStream file = new FileInputStream(new File(filePath));
	      
	      XSSFWorkbook workbook = new XSSFWorkbook(file);
	      
	      XSSFSheet sheet = workbook.getSheet(sheetName);
	      
	      XSSFRow sheetrow = sheet.getRow(row);
	      
	      if (sheetrow == null)
	      {
	        sheetrow = sheet.createRow(row);
	      }
	      
	      XSSFCell xSSFCell = sheetrow.getCell(col);
	      
	      if (xSSFCell == null)
	      {
	        xSSFCell = sheetrow.createCell(col);
	      }
	      
	      xSSFCell.setCellValue(DatatoWrite.toString());
	      
	      file.close();
	      
	      FileOutputStream outFile = new FileOutputStream(new File(String.valueOf(filePath)));
	      
	      workbook.write(outFile);
	      
	      outFile.close();
	      
	      workbook.close();
	    
	    }
	    catch (FileNotFoundException e) {
	      
	      e.printStackTrace();
	    
	    }
	    catch (IOException e) {
	      
	      e.printStackTrace();
	    } 
	  }
}
