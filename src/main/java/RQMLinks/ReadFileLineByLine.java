package RQMLinks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFileLineByLine {
	int lineNum;
	List<String> fileCont=new ArrayList<String>();
	int RQMLine;
	
	public static void main(String[] args) throws IOException {
		
		ReadFileLineByLine rfl= new ReadFileLineByLine();
		
		//String file = "file.txt";
		String file="C:\\EggplantSuites\\IPDev-Millennium-ClinicalAssessment\\IPDev-Millennium-Clinical-Assessment.suite\\Scripts\\Workflow\\ICU-WF-Advanced_Graphing_IO_Graph.script";
		int lineToBeEdited = 1;
		String oldLine = rfl.readTextFileByLines(file).get(lineToBeEdited-1);
	    String newLineContent = "#RQMTestCaseLink:  \n"+oldLine;
	    ChangeLineInFile changeFile = new ChangeLineInFile();
	    
		try  
		{  
			
		//the file to be opened for reading  
		FileInputStream fis=new FileInputStream(file);       
		Scanner sc=new Scanner(fis);    //file to be scanned  
		//returns true if there is another line to read 
		boolean flag = false;
			while(sc.hasNextLine())  
			{  	
				rfl.fileCont.add(sc.nextLine());
				rfl.lineNum++;
				if(rfl.fileCont.get(rfl.lineNum-1).contains("RQM")) {
					rfl.RQMLine=rfl.lineNum;
					flag=true;
				}
			}
			System.out.println("Number of lines in the file is : "+rfl.lineNum);
			
			if(flag) {
				System.out.println(rfl.fileCont.get(rfl.RQMLine-1));
			}
			else {
				changeFile.changeALineInATextFile(file, newLineContent, lineToBeEdited);
				//rfl.WriteAtLine(file, 0, "#RQMTestCaseLink :- ");
			}
				
			
			sc.close();     //closes the scanner  
		}  
		catch(IOException e)  
		{  
			e.printStackTrace();  
		}
	}
	
	
	
	
	public List<String> readTextFileByLines(String fileName) throws IOException {
	    List<String> lines = Files.readAllLines(Paths.get(fileName));
	    return lines;
	}
	
	public void WriteAtLine(String filePath,int lineNumber, String data) throws IOException {

        	try {
            	File f=new File(filePath);
               FileWriter fw = new FileWriter(f,true);
                BufferedWriter bw = new BufferedWriter(fw);
                LineNumberReader  lnr = new LineNumberReader(new FileReader(f));
                //lnr.setLineNumber(lineNumber);
               
                for(int i=1;i<=lnr.getLineNumber();i++){
                	while(i==lineNumber){
                    	bw.newLine();
                    	bw.write(data);
                    }
                 }
                               
                bw.close();
                lnr.close();
                } catch (IOException e) {
                e.printStackTrace();
                }
	    
	    
	}
	 
}
