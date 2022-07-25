package JenkinsJobs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GetJobsFromJenkinsLink {

	  static String sheetName = null;
	  
	  static String filePath = null;
	  
	  static String username = null;
	  
	  static String password = null;
	  
	  static Workbook wb = null;
	  
	  static Scanner input = new Scanner(System.in);
	  
	 
	    public static void main(String[] args) throws EncryptedDocumentException, IOException {
	    	
	    	System.out.print("Enter the Excel Path : ");
	 	    
	 	    filePath = input.nextLine();
	 	    
	 	    System.out.println();
	 	    
	 	    System.out.print("Enter the SheetName : ");
	 	    
	 	    sheetName = input.nextLine();
	 	    
	 	    System.out.println();
	 	    
	 	    FileInputStream inputFile = new FileInputStream(filePath);
	 	    
	 	    wb = WorkbookFactory.create(inputFile);
	 	    
	 	    int LastRow = wb.getSheet(sheetName).getLastRowNum();
	 	    
	 	    System.out.print("Total Number of Scripts in excel : " + LastRow + "\n\n");
	 	    
	 	    WebDriverManager.chromedriver().setup();
	        
	 	    ChromeOptions options = new ChromeOptions();
	 	   
	 	    options.addArguments("headless");
	  	  	
	 	    WebDriver driver=new ChromeDriver(options);
	 	    
	 	    String currentWindow = driver.getWindowHandle();
	 	    
	 	    driver.switchTo().window(currentWindow);
	 	    
	 	    driver.manage().window().maximize();
	 	    
	 	    driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
	 	    try {
	 	    	for (int i = 1; i <= LastRow; i++) {
		 	        		 	        
		 	        driver.get(GetLinkFromExcel(wb, sheetName, i));
		 	        
		 	        if(i==1) {
		 	        LoginToJenkins(driver,input);
		 	        }
		 	        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
		 	        
		 	       System.out.print("\n"+GetLinkFromExcel(wb, sheetName, i) + "\n");
		 	        
		 	        System.out.println(driver.findElement(By.xpath("//h1/span")).getText().toString());
		 	        
		 	        List<WebElement> JobList=driver.findElements(By.xpath("//tbody/tr[contains(@id,'job_')]"));
		 	        
		 	        for(WebElement welemnt: JobList) {
		 	        
		 	        	String[] jobs=welemnt.getAttribute("id").split("job_");
		 	        	
		 	        	System.out.println(jobs[1]);
		 	        }	 	        
		 	        	        
		 	        System.out.print("\n=========================================================================================\n");
		 	      } 
		 	      
		 	      input.close();
		 	      
	 	    }
	 	    catch (Exception e) {
	 	       
	 	    	KillChromeDriver();
	 	       e.printStackTrace();
	 	     
	 	     }
	 	    
	 	   KillChromeDriver();
		}
	    
	public static void KillChromeDriver() {
	     try {
	         System.out.println("Kill Chrome Driver");
	          
	          Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
	          
	          Thread.sleep(2000L);
	          
	          Runtime.getRuntime().exec("taskkill /f /im ruby.exe");
	          
	          Thread.sleep(1000L);
	          
	          System.exit(0);
	        }
	        catch (Exception e) {
	          
	          e.printStackTrace();
	        } 
	      }
	public static void LoginToJenkins(WebDriver driver,Scanner in) {
		try {
		      if (driver.findElement(By.xpath("//a[contains(text(),'Log in')]")).isDisplayed()) {
		    	  
		    	  driver.findElement(By.xpath("//a[contains(text(),'Log in')]")).click();
		      }
		    } catch (NoSuchElementException noSuchElementException) {}
		    try {
		      if (driver.findElement(By.xpath("//input[@id='j_username']")).isDisplayed()) {
		        
		    	  System.out.println("Please Enter the UserName : ");
		        
		    	  String userName = in.nextLine();
		    	  
		    	  driver.findElement(By.xpath("//input[@id='j_username']")).sendKeys(new CharSequence[] { userName });
		    	  
		    	  System.out.println("Please Enter the Password : ");
		    	  
		    	  String password = in.nextLine();
		    	  
		    	  driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(new CharSequence[] { password });
		    	  
		    	  driver.findElement(By.xpath("//div[@class='Checkbox-indicator']")).click();
		    	  
		    	  driver.findElement(By.xpath("//input[@name='Submit']")).click();
		      } 
		    } catch (NoSuchElementException noSuchElementException) {}
	}
	 
	public static String GetLinkFromExcel(Workbook wb, String Sheet, int Row) { return wb.getSheet(sheetName).getRow(Row).getCell(0).toString();	 }

	public void writeToExcelFile(String filePath, String sheetName, Object DatatoWrite, int row, int col) throws IOException {
    try {
      FileInputStream file = new FileInputStream(new File(String.valueOf(filePath)));
      
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
    
    }
    catch (FileNotFoundException e) {
      
      e.printStackTrace();
    
    }
    catch (IOException e) {
      
      e.printStackTrace();
    } 
  }
}