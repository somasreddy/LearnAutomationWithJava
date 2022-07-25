package RQMLinks;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.Console;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RQMTestCaseLinkUpdate {
	  static String sheetName = null;
	  
	  static String filePath = null;
	  
	  static String username = null;
	  
	  static String password = null;
	  
	  static Workbook wb = null;
	  
	  static Scanner input = new Scanner(System.in);
	 
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
	    
	    RQMTestCaseLinkUpdate rtcl = new RQMTestCaseLinkUpdate();
	    
	    WebDriverManager.chromedriver().setup();
	        
	    WebDriver chromeDriver = new ChromeDriver();
	    
	    String currentWindow = chromeDriver.getWindowHandle();
	    
	    chromeDriver.switchTo().window(currentWindow);
	    
	    chromeDriver.manage().window().maximize();
	    
	    chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    
	    try {
	    	
	      rtcl.LoginToRQM(chromeDriver);
	      
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
	    	  
	    	String testCaseName=rtcl.GetTCFromExcel(wb, sheetName, i);
	    	  
	    	String testCaseLink=rtcl.getTestCaseLink(chromeDriver, testCaseName);
	        
	        System.out.print("\n" + String.valueOf(i) + " . " + testCaseName + " ----> "+testCaseLink +"\n");
	        	        
	        rtcl.writeToExcelFile(filePath, sheetName, testCaseLink, i, 1);
	        
	        String updateStatus=rtcl.updateScriptStatus(chromeDriver,testCaseName,testCaseLink,0);
	        
	        System.out.println("Status: "+updateStatus);
	        
	        System.out.print("\n=========================================================================================\n");
	        
	        if(updateStatus.contains("already contains RQM")){
	        	
	        	rtcl.writeToExcelFile(filePath, sheetName, updateStatus, i, 2);
	        	
	        	chromeDriver.get("https://jazz.cerner.com:9443/qm/web/console/IP#action=com.ibm.rqm.planning.home.actionDispatcher&subAction=viewTestCases&updateAction=clear-filter-selection");
	        	
	        	continue;
	        }
	        
	        rtcl.writeToExcelFile(filePath, sheetName, updateStatus, i, 2);
	        
	        Thread.sleep(2000);
	      } 
	      
	      input.close();
	      
	      rtcl.LogOutRQM(chromeDriver);
	      
	      rtcl.KillChromeDriver();
	   
	    }
	    catch (Exception e) {
	      
	      e.printStackTrace();
	      
	      chromeDriver.quit();
	      
	      rtcl.KillChromeDriver();
	    } 
	  }
	  
	public String updateScriptStatus(WebDriver driver,String tcName,String tcLink, int lineNum) throws HeadlessException, UnsupportedFlavorException, IOException, InterruptedException {
		  
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  
		  String scriptPath=getTestScriptPath(driver,tcName,tcLink);
		  
		  String updateStatus = "Invalid File Path or File Not Found at "+ scriptPath;
	        
	        if(scriptPath.toLowerCase().contains ("c:\\eggplantsuites\\")) {
	        	
	        	File tempFile = new File(scriptPath);
	        	boolean fileExists=false;
	        	try {
	        		fileExists = tempFile.exists();
	        		
	        		System.out.println("\n\n"+scriptPath+" is a Valid File");
	        		
	        		System.out.println("\n----------------------------------\n");
	        	}
	        	catch(Exception e) {
	        		updateStatus="Invalid File Path or File Not Found at "+ scriptPath;
	        	}
	        	if(fileExists) {
        		
	    	    String fileCont= readTextFile(scriptPath);
	        	
	    	    if(fileCont.contains("RQM")) {
	    	    	
	    	    	String line =null;
	    	    	int k=0;
	    	    	
	    	    	List<String> lines=readTextFileByLines(scriptPath);

	    	    	for (k=0;k<lines.size();k++) {
	    	    	
	    	    		line= lines.get(k).toLowerCase();

	    	    		if (line.contains("RQM")) {
	    	    			
	    	    			line=lines.get(k);
	    	    		}
	    	    		break;
	    	    	}
	    	    	
	    	    	updateStatus=tcName+" Script already contains RQM ID/Link";/*" Script already contains RQM ID/Link; at line number: "+(k+1)+" as "+line*/;	    	    	
	    	    }
	    	    else {
	        	String oldLine = readTextFileByLines(scriptPath).get(lineNum); //To fetch the line which is matched
	        	String newLine="#RQM test case link: "+tcLink+"\n"+oldLine;
	        	setVariable(scriptPath,lineNum,newLine);
	            
	        	updateStatus="\n"+tcName+" Updated Successfully with RQM Link";
	  	        
	            for(int i=0;i<=(lineNum+3);i++) {
	  	        
	            	System.out.println(readTextFileByLines(scriptPath).get(i));
	            	}
	            System.out.println("\n----------------------------------\n");
	    	    }
        	  }
	        }
	        else if (scriptPath.toLowerCase().contains((tcName+".script").toLowerCase())||scriptPath.toLowerCase().contains((tcName+"..script").toLowerCase()))
	        	updateStatus="RQM TestScript Path is not in 'C:\\EggplantSuites\\' instead it is in :"+scriptPath;
	        else
	        	updateStatus=scriptPath;
	        
	    return updateStatus;
	  }
	  public void HandleConstraintAlert(WebDriver driver) throws IOException, InterruptedException {
		    List<WebElement> lst = new ArrayList<WebElement>();
		   
		    try {
		      lst.addAll(driver.findElements(By.xpath("//span/div/div/div[@class='messageSummary'][.='Constraints Warning']")));
		    } catch (Exception exception) {}
		    if (!lst.isEmpty() && lst.size() >= 1) {
		      
		      driver.navigate().refresh();
		      
		      Thread.sleep(1500L);
		      
		      handleAlert(driver);
		      
		      Thread.sleep(8000L);
		    } 
		  }
	  
	  public void handleAlert(WebDriver driver) throws IOException, InterruptedException {
		    if (isAlertPresent(driver)) {
		      Alert alert = driver.switchTo().alert();
		      
		      alert.accept();
		    } 
		  }
		  public boolean isAlertPresent(WebDriver driver) {
		    try {
		      driver.switchTo().alert();
		      return true;
		    } catch (NoAlertPresentException ex) {
		      return false;
		    } 
		  }
	  
	  public String getTestScriptPath(WebDriver driver, String TCName,String testCaseLink) throws HeadlessException, UnsupportedFlavorException, IOException, InterruptedException {
		  
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  
		  WebDriverWait wait = new WebDriverWait(driver, 25000);
		  
		  String testScriptPath="Script of Type TestPlant-Eggplant is not found with the given testcase name: "+TCName;
		  
		  driver.get(testCaseLink);
		  
		  Thread.sleep(5000);		  

		  WebElement testCaseScript = driver.findElement(By.partialLinkText("Test Scrip"));
		  
		  testCaseScript.click();
		  
		  WebElement testScript=null;
		  
		  try {
			  testScript=driver.findElement(By.xpath("//div[@title='TestPlant-Eggplant']/../../td/div/a/div[.='"+TCName+"']"));
		  }
		  catch(Exception e) {
			  
			  testScriptPath="No Script of Type TestPlant-Eggplant found with the given testcase name: "+TCName;
		  }
		  if (testScript!=null) {
			  
			  testScript.click();
			  
			  Thread.sleep(2500);
			  
			  HandleConstraintAlert(driver);
			  
			  Thread.sleep(2500);
		      
		      WebElement epCmnd = (WebElement)wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='command']")));
		      
		      epCmnd.click();
		      
		      epCmnd.sendKeys(new CharSequence[] { Keys.chord(new CharSequence[] { Keys.CONTROL, "a" }) });
		      
		      epCmnd.sendKeys(new CharSequence[] { Keys.chord(new CharSequence[] { Keys.CONTROL, "c" }) });
		      
		      String Command = (String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
		      
		      testScriptPath =Command;
			  
		  }  
		  
		  return testScriptPath;
	  }
	    
	  public void setVariable(String filePath,int lineNumber, String data) throws IOException {
		    Path path = Paths.get(filePath);
		    List<String> lines = readTextFileByLines(filePath);
		    lines.set(lineNumber, data);
		    Files.write(path, lines, StandardCharsets.UTF_8);
		    
		}
	  
	  public String GetTDataFromExcel(Workbook wb, String Sheet, int Row, int Col) { return wb.getSheet(sheetName).getRow(Row).getCell(Col).toString(); }
	  
	  public String GetTCFromExcel(Workbook wb, String Sheet, int Row) { return wb.getSheet(sheetName).getRow(Row).getCell(0).toString(); }
	  
	  public String readTextFile(String fileName) throws IOException {
		    String content = new String(Files.readAllBytes(Paths.get(fileName)));
		    return content;
		}

	  public List<String> readTextFileByLines(String fileName) throws IOException {
		    List<String> lines = Files.readAllLines(Paths.get(fileName));
		    return lines;
		}

	  public static void writeToTextFile(String fileName, String content) throws IOException {
		    Files.write(Paths.get(fileName), content.getBytes(), StandardOpenOption.CREATE);
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
			      
			   	testCaseLink = "No TestCase Found with the given Name : " + TestCaseName;
			      
			    driver.navigate().to("https://jazz.cerner.com:9443/qm/web/console/IP#action=com.ibm.rqm.planning.home.actionDispatcher&subAction=viewTestCases&updateAction=clear-filter-selection");
			 
			} 		    
			 if (TestCase!=null) {
			      
			   	testCaseLink=driver.findElement(By.xpath("//a/div[.='" + TestCaseName + "']/..")).getAttribute("href");
			     
			    Thread.sleep(2500L);
			 }
		  return testCaseLink;
	  
	  }
	   
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
	  
	  public void LoginToRQM(WebDriver driver) throws IOException, InterruptedException {
		  
		  	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		    
		  	driver.get("https://jazz.cerner.com:9443/qm/web/console/IP#action=com.ibm.rqm.planning.home.actionDispatcher&subAction=viewUserHome");
		    
		    WebDriverWait wait = new WebDriverWait(driver, 25000L);
		    
		    System.out.print("\nEnter the User Name : ");
		    
		    String username = input.nextLine();
		    
		    WebElement usernameTxtBx = (WebElement)wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[id*='userId'][name*='username']")));
		    
		    usernameTxtBx.sendKeys(new CharSequence[] { username });
		    
		    Console console = System.console();
		    
		    String password="Soma@cer!";
		    
//		    char[] password = console.readPassword("Enter your password :"); 

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