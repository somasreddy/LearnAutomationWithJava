package JenkinsJobs;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Jenkins_RQM_Names {
	static int counter=1;
	static String sheetName="1501-List";
	static String inputSheet="PassedPlans";
	static String filePath="C:\\com.RQM\\Exe\\MRD-1501-Plan-List.xls";
	public static void main(String[] args) throws InvalidFormatException, IOException {
	    Map<String, String> map = new HashMap<String, String>();
	    FileInputStream inputFile=new FileInputStream(filePath);
		Workbook wb=WorkbookFactory.create(inputFile);
		int LastRow=wb.getSheet(sheetName).getLastRowNum();
		for(int i=0;i<=LastRow;i++) {
			  for(int j=0;j<1;j++) { 
			  map.put(wb.getSheet(sheetName).getRow(i).getCell(2).toString(),wb.getSheet(sheetName).getRow(i).getCell(0).toString());
			  }
		}
	int LastRow1=wb.getSheet(inputSheet).getLastRowNum();
	for(int i=0;i<=LastRow1;i++) {
	   for(int j=0;j<1;j++) { 
				System.out.println(wb.getSheet(inputSheet).getRow(i).getCell(0).toString()+"    ---->   "+ getKeyFromValue(map,wb.getSheet(inputSheet).getRow(i).getCell(0).toString()));
		  }
	}
   
  }
	public static Object getKeyFromValue(Map<String, String> hm, Object value) 
	{
	    for (Object o : hm.keySet()) 
	    {
	      if (hm.get(o).equals(value)) 
	      {
	        return o;
	      }
	    }
	    return "Unable To Find Related Plan"+counter++;
	}
}


