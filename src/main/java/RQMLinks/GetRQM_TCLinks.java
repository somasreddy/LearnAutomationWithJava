package RQMLinks;

import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GetRQM_TCLinks {

	static String sheetName = null;
	  
	static String filePath = null;
	  
	static String username = null;
	  
	static String password = null;
	  
	static Workbook wb = null;
	  
	static Scanner input = new Scanner(System.in);
	
	public void LoginToRQM(WebDriver driver) throws IOException, InterruptedException {
		  
	  	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    
	  	driver.get("https://jazz.cerner.com:9443/qm/web/console/IP#action=com.ibm.rqm.planning.home.actionDispatcher&subAction=viewUserHome");
	    
	    WebDriverWait wait = new WebDriverWait(driver, 25000L);
	    
	    System.out.print("\nEnter the User Name : ");
	    
	    String username = input.nextLine();
	    
	    WebElement usernameTxtBx = (WebElement)wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[id*='userId'][name*='username']")));
	    
	    usernameTxtBx.sendKeys(new CharSequence[] { username });
	    
	    Console console = System.console();
	    
	    char[] password = console.readPassword("Enter your password :"); 

	    WebElement pswrdTxtBx = (WebElement)wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='j_password']")));

	    pswrdTxtBx.sendKeys(new String(password));
	    
	    driver.findElement(By.name("rememberUserId")).click();
	    
	    driver.findElement(By.xpath("//button[contains(text(),'Log In')]")).click();
	    
	    driver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
	    
	    System.out.println();
	    
	    Thread.sleep(10000L);
	    
	    System.out.println("SuccessFully Logged in to RQM ");
	    
	    System.out.println();
	  }	
	
	public String getTestCaseLink(WebDriver driver, String TestCaseName) throws InterruptedException, IOException {
		  
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 
	  	WebDriverWait wait = new WebDriverWait(driver, 25000L);
		  
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		
		WebElement nameTxtbox = driver.findElement(By.xpath("//input[@name='name' and @type='text']"));
		  
		executor.executeScript("arguments[0].click();", nameTxtbox);
	  		 
	  	Thread.sleep(1500);
	    
	    nameTxtbox.clear();
	    
	    nameTxtbox.sendKeys(new CharSequence[] { TestCaseName });
	    
	    Thread.sleep(2500);
	    
	    WebElement runButton = (WebElement)wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='run']")));
	    
	    runButton.click();
	    
	    Thread.sleep(2500L);
	    
	    String testCaseLink = null;
	    
	    WebElement TestCase = null;
	    
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
	  
	    try {
	    	
	    	TestCase = driver.findElement(By.xpath("//a/div[.='" + TestCaseName + "']"));
		    
	    }
		catch (Exception nse) {
		      
		   	testCaseLink = "No Match Found in RQM with the given Name : " + TestCaseName;
		      
		    driver.navigate().to("https://jazz.cerner.com:9443/qm/web/console/IP#action=com.ibm.rqm.planning.home.actionDispatcher&subAction=viewTestCases&updateAction=clear-filter-selection");
		 
		} 		    
		 if (TestCase!=null) {
		      
		   	testCaseLink=driver.findElement(By.xpath("//a/div[.='" + TestCaseName + "']/..")).getAttribute("href");
		     
		    Thread.sleep(2500L);
		    
		    nameTxtbox.clear();
		 }
	  return testCaseLink;
  
  }
	
	public static void main(String[] args) throws InvalidFormatException, IOException, InterruptedException {
	    
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
	    
	    GetRQM_TCLinks gtcl = new GetRQM_TCLinks();
	    
	    WebDriverManager.chromedriver().setup();
	        
	    WebDriver chromeDriver = new ChromeDriver();
	    
	    String currentWindow = chromeDriver.getWindowHandle();
	    
	    chromeDriver.switchTo().window(currentWindow);
	    
	    chromeDriver.manage().window().maximize();
	    
	    chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    
	    
	    try {
	    	
	    	gtcl.LoginToRQM(chromeDriver);
	      
	    	System.out.print("Enter the Starting Point : ");
	      
	    	int startPoint = Integer.parseInt(input.nextLine());
	      
	    	System.out.print("\n=========================================================================================\n");
	    	
	    	WebDriverWait wait = new WebDriverWait(chromeDriver, 25000L);
  	      
	    	for (int i = startPoint; i <= LastRow; i++) {

	    		JavascriptExecutor executor = (JavascriptExecutor)chromeDriver;
	    	  
	    		WebElement tcNameTB=null;
	    	  
	    		try {
	    		  
	    			chromeDriver.get("https://jazz.cerner.com:9443/qm/web/console/IP#action=com.ibm.rqm.planning.home.actionDispatcher&subAction=viewTestCases&updateAction=clear-filter-selection");
			      
	    			Thread.sleep(4000);
		    	  
	    			tcNameTB= chromeDriver.findElement(By.xpath("//input[@name='name' and @type='text']"));
	    		  
	    	  }catch(Exception e) { 
	    		  
	    		  if(tcNameTB!=null) {
	    			  
	    			  if (tcNameTB.isDisplayed()) {
	  				  
	    				  executor.executeScript("arguments[0].click();", tcNameTB);
		    		  }
		    		  else {
		    			  
		    			  WebElement expandFilter = (WebElement)wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='Show/Hide Table Inline Filters']")));
				      
		    			  expandFilter.click();
				      
		    			  Thread.sleep(2500); 
		    		  }
	    		  }
	    		  else {
	    			  WebElement expandFilter = (WebElement)wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='Show/Hide Table Inline Filters']")));
				      
	    			  expandFilter.click();
			      
	    			  Thread.sleep(2500);
	    		  }	  
	    		}
	    	  
	    		String testCaseName=gtcl.GetTCFromExcel(wb, sheetName, i);
	    	
	    		String testCaseLink=gtcl.getTestCaseLink(chromeDriver, testCaseName);
	        
	    		System.out.print("\n" + String.valueOf(i) + " . " + testCaseName + " ----> "+testCaseLink +"\n");
	        	        
	    		gtcl.writeToExcelFile(filePath, sheetName, testCaseLink, i, 1);
	        
	    	} 
	      
	      input.close();
	      
	      gtcl.LogOutRQM(chromeDriver);
	      
	      gtcl.KillChromeDriver();
	   
	    }
	    catch (Exception e) {
	      
	      e.printStackTrace();
	      
	      chromeDriver.quit();
	      
	      gtcl.KillChromeDriver();
	    } 
	  }
	
	 public String GetTCFromExcel(Workbook wb, String Sheet, int Row) { return wb.getSheet(sheetName).getRow(Row).getCell(0).toString(); }
	   
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
		      
		      workbook.close();
		    
		    }
		    catch (FileNotFoundException e) {
		      
		      e.printStackTrace();
		    
		    }
		    catch (IOException e) {
		      
		      e.printStackTrace();
		    } 
		  }

	  public void LogOutRQM(WebDriver driver) throws IOException, InterruptedException {
			  
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			  
		    WebDriverWait wait = new WebDriverWait(driver, 25000L);
		    
		    Actions builder = new Actions(driver);
		    
		    System.out.println("Logging Out...");
		    
		    Thread.sleep(5000L);
		    
		    builder.sendKeys(new CharSequence[] { Keys.PAGE_UP }).build().perform();
		    
		    Thread.sleep(5000L);
		    
		    builder.sendKeys(new CharSequence[] { Keys.PAGE_UP }).build().perform();
		    
		    Thread.sleep(5000L);
		    
		    WebElement logoutbtn = (WebElement)wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span[class='caret jazz-ui-MenuPopup-caret']")));
		    
		    logoutbtn.click();
		    
		    Thread.sleep(15000L);
		    
		    WebElement logout = (WebElement)wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@data-dojo-attach-point='containerNode'][contains(@id,'text')][contains(.,'Log Out')]")));
		    
		    logout.click();
		    
		    System.out.println("Logged Out Successfully");
		    
		    System.out.println();
		    
		    driver.quit();
		  }

	  public void KillChromeDriver() {
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
}
