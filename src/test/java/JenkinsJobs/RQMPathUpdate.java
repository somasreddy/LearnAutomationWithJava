package JenkinsJobs;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RQMPathUpdate
{
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
    
    RQMPathUpdate rtsp = new RQMPathUpdate();

        
    WebDriver chromeDriver = new ChromeDriver();
    
    String currentWindow = chromeDriver.getWindowHandle();
    
    chromeDriver.switchTo().window(currentWindow);
    
    chromeDriver.manage().window().maximize();
    
    chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    try {
    	
      rtsp.LoginToRQM(chromeDriver);
      
      System.out.print("Enter the Starting Point : ");
      
      int startPoint = Integer.parseInt(input.nextLine());
      
      System.out.print("\n=========================================================================================\n");
      
      WebDriverWait wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10000));
      
      chromeDriver.navigate().to("https://jazz.cerner.com:9443/qm/web/console/IP#action=com.ibm.rqm.planning.home.actionDispatcher&subAction=viewTestScripts&updateAction=clear-filter-selection");
      
      WebElement expandFilter = (WebElement)wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='Show/Hide Table Inline Filters']")));
      
      expandFilter.click();
      
      Thread.sleep(5000L);
      
      for (int i = startPoint; i <= LastRow; i++) {
        
        System.out.print("\n" + String.valueOf(i) + " . " + rtsp.GetTScriptFromExcel(wb, sheetName, i) + "\n");
        
        rtsp.writeToExcelFile(filePath, sheetName, rtsp.UpdateTestScript(chromeDriver, rtsp.GetTScriptFromExcel(wb, sheetName, i)), i, 1);
        
        System.out.print("\n=========================================================================================\n");
      } 
      
      input.close();
      
      rtsp.LogOutRQM(chromeDriver);
      
      rtsp.KillChromeDriver();
   
    }
    catch (Exception e) {
      
      e.printStackTrace();
      
      chromeDriver.quit();
      
      rtsp.KillChromeDriver();
    } 
  }
  
  public void LoginToRQM(WebDriver driver) throws IOException, InterruptedException {
    driver.get("https://jazz.cerner.com:9443/qm/web/console/IP#action=com.ibm.rqm.planning.home.actionDispatcher&subAction=viewUserHome");
    
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25000L));
    
    System.out.print("\nEnter the User Name : ");
    String username = input.nextLine();
    WebElement usernameTxtBx = (WebElement)wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[id*='userId'][name*='username']")));
    usernameTxtBx.sendKeys(new CharSequence[] { username });
    
    System.out.print("\nEnter the Password : ");
    String password = input.nextLine();
    WebElement pswrdTxtBx = (WebElement)wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='j_password']")));
    pswrdTxtBx.sendKeys(new CharSequence[] { password });
    
    driver.findElement(By.name("rememberUserId")).click();
    
    driver.findElement(By.xpath("//button[contains(text(),'Log In')]")).click();
    
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30L));
    
    System.out.println();
    
    Thread.sleep(10000L);
    
    System.out.println("SuccessFully Logged in to RQM ");
    
    System.out.println();
  }

  
  public static void handleAlert(WebDriver driver) throws IOException, InterruptedException {
    if (isAlertPresent(driver)) {
      Alert alert = driver.switchTo().alert();
      
      alert.accept();
    } 
  }
  public static boolean isAlertPresent(WebDriver driver) {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException ex) {
      return false;
    } 
  }

  
  public String GetTDataFromExcel(Workbook wb, String Sheet, int Row, int Col) { return wb.getSheet(sheetName).getRow(Row).getCell(Col).toString(); }
  
  public String GetTScriptFromExcel(Workbook wb, String Sheet, int Row) { return wb.getSheet(sheetName).getRow(Row).getCell(0).toString(); }
  
  public String browseTestScript(WebDriver driver, String TestScriptName) throws InterruptedException, IOException {
    String browseTScript = null;
    
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10000L));
    
    driver.navigate().to("https://jazz.cerner.com:9443/qm/web/console/IP#action=com.ibm.rqm.planning.home.actionDispatcher&subAction=viewTestScripts&updateAction=clear-filter-selection");
    
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    
    Thread.sleep(2000L);
    
    try {
      driver.findElement(By.xpath("//input[@name='name' and @type='text']")).isDisplayed();
    }
    catch (Exception nse) {
      
      WebElement expandFilter = (WebElement)wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='Show/Hide Table Inline Filters']")));
      
      expandFilter.click();
      
      Thread.sleep(2000L);
    } 
    
    WebElement nameTxtBox = (WebElement)wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='name' and @type='text']")));
    
    nameTxtBox.click();
    
    Thread.sleep(1500L);
    
    nameTxtBox.clear();
    
    nameTxtBox.sendKeys(new CharSequence[] { TestScriptName });
    
    Thread.sleep(5000L);
    
    WebElement runButton = (WebElement)wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='run']")));
    
    runButton.click();
    
    Thread.sleep(5000L);
    
    boolean testScriptNameStatus = false;
    
    try {
      testScriptNameStatus = driver.findElement(By.xpath("//div[@title='TestPlant-Eggplant']/../../td/div/div/a/div[.='" + TestScriptName + "']")).isDisplayed();
    }
    catch (Exception nse) {
      
      browseTScript = "No Script of type TestPlant-Eggplant is Found with the given Name : " + TestScriptName;
      
      driver.navigate().to("https://jazz.cerner.com:9443/qm/web/console/IP#action=com.ibm.rqm.planning.home.actionDispatcher&subAction=viewTestScripts&updateAction=clear-filter-selection");
    } 
    
    if (testScriptNameStatus) {
      
      driver.findElement(By.xpath("//div[@title='TestPlant-Eggplant']/../../td/div/div/a/div[.='" + TestScriptName + "']")).click();
      
      Thread.sleep(5000L);
      
      browseTScript = "Navigated to the TestScript";
    } 
    return browseTScript;
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
  
  public String UpdateTestScript(WebDriver driver, String TSName) throws InterruptedException, IOException, HeadlessException, UnsupportedFlavorException {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25000L));
    
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    
    String UpdateStatus = null;
    boolean DraftTScript = false;
    
    if (browseTestScript(driver, TSName) == "Navigated to the TestScript") {
      
      HandleConstraintAlert(driver);
      
      wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Change State')]")));
      
      WebElement epCmnd = (WebElement)wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='command']")));
      
      epCmnd.click();
      
      epCmnd.sendKeys(new CharSequence[] { Keys.chord(new CharSequence[] { Keys.CONTROL, "a" }) });
      
      epCmnd.sendKeys(new CharSequence[] { Keys.chord(new CharSequence[] { Keys.CONTROL, "c" }) });
      
      String Command = (String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
      
      String existCmnd = "C:\\Git";
      String existCmnd1 = "c:\\git";
      String existCmnd2 = "c:\\Git";
      String existCmnd3 = "C:\\git";
      String existCmnd4 = "C:\\GIT";
      String existCmnd5 = "c:\\GIT";
      
      String Cmdtoreplace = "C:\\EggPlantSuites";
      
      if (Command.toLowerCase().contains(existCmnd.toLowerCase())) {
        
        try {
          DraftTScript = driver.findElement(By.xpath("//img[@src='/qm/web/com/ibm/asq/common/web/ui/internal/view/templates/common/images/draft_obj.gif']")).isDisplayed();
          
          System.out.println("\nTest Script is not approved yet\n");
        }
        catch (Exception exception) {}
        
        String actionXpath = "//span[contains(text(),'Change State')]";
        
        Actions builder = new Actions(driver);
        
        if (!DraftTScript) {
          
          builder.click(driver.findElement(By.xpath(actionXpath))).perform();
          
          builder.moveToElement(driver.findElement(By.xpath("//span[contains(text(),'Reopen')]"))).click().build().perform();
          
          wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='artifact-node']//div//button[@class='right-action primary-button'][contains(text(),'Save')]")));
          
          Thread.sleep(1500L);
          
          driver.findElement(By.xpath("//div[@class='artifact-node']//div//button[@class='right-action primary-button'][contains(text(),'Save')]")).click();
          
          Thread.sleep(1500L);
        } 

        
        String newCmd = null;
        if (Command.contains(existCmnd)) {
          newCmd = Command.replace(existCmnd, Cmdtoreplace);
        } else if (Command.contains(existCmnd1)) {
          newCmd = Command.replace(existCmnd1, Cmdtoreplace);
        } else if (Command.contains(existCmnd2)) {
          newCmd = Command.replace(existCmnd2, Cmdtoreplace);
        } else if (Command.contains(existCmnd3)) {
          newCmd = Command.replace(existCmnd3, Cmdtoreplace);
        } else if (Command.contains(existCmnd4)) {
          newCmd = Command.replace(existCmnd4, Cmdtoreplace);
        } else if (Command.contains(existCmnd5)) {
          newCmd = Command.replace(existCmnd5, Cmdtoreplace);
        } 
        System.out.println("\nCurrent Path of the Test Script " + Command);
        
        HandleConstraintAlert(driver);
        
        WebElement epCmnd1 = driver.findElement(By.xpath("//input[@id='command']"));
        
        epCmnd1.click();
        
        epCmnd1.sendKeys(new CharSequence[] { Keys.chord(new CharSequence[] { Keys.CONTROL, "a", Keys.DELETE }) });
        
        epCmnd1.sendKeys(new CharSequence[] { newCmd });
        
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='artifact-node']//div//button[@class='right-action primary-button'][contains(text(),'Save')]")));
        
        driver.findElement(By.xpath("//div[@class='artifact-node']//div//button[@class='right-action primary-button'][contains(text(),'Save')]")).click();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']/div[@class='messageSummary'][contains(text(),'Saved successfully')]")));
        
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(actionXpath)));
        
        builder.click(driver.findElement(By.xpath(actionXpath))).perform();
        
        builder.moveToElement(driver.findElement(By.xpath("//span[contains(text(),'Approve')]"))).click().build().perform();
        
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='artifact-node']//div//button[@class='right-action primary-button'][contains(text(),'Save')]")));
        
        driver.findElement(By.xpath("//div[@class='artifact-node']//div//button[@class='right-action primary-button'][contains(text(),'Save')]")).click();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']/div[@class='messageSummary'][contains(text(),'Saved successfully')]")));
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='/qm/web/com/ibm/asq/common/web/ui/internal/view/templates/common/images/approve_obj.gif']")));
        
        System.out.println("\nUpdated Test Script path is -> " + newCmd);
        
        UpdateStatus = "Path is replaced from" + existCmnd + " to " + Cmdtoreplace;
      } else {
        
        System.out.println("\nScript already contains the desired Path -> " + Command);
        
        UpdateStatus = "Script already contains the desired Path";
      }
    
    }
    else {
      
      System.out.println("\n\"No Script of type TestPlant-Eggplant is Found with the given Name : " + TSName + "\n");
      
      UpdateStatus = "No Script of type TestPlant-Eggplant is Found with the given Name : " + TSName;
    } 

    
    return UpdateStatus;
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
    
    }
    catch (FileNotFoundException e) {
      
      e.printStackTrace();
    
    }
    catch (IOException e) {
      
      e.printStackTrace();
    } 
  }


  
  public void LogOutRQM(WebDriver driver) throws IOException, InterruptedException {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25000L));
    
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
