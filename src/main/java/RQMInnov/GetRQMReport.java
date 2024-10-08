package RQMInnov;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GetRQMReport {

    static String sheetName = null;

    static String filePath = null;

    static String username = null;

    static String password = null;

    // static Workbook wb = null;

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

	filePath = "C:\\Temp\\RQMReportInput.xlsx";

	sheetName = "TestPlans";

	System.out.println();

	String username = args[0];// input.nextLine();

	String password = args[1];

	String releaseNum = args[2];// input.nextLine();

	String downloadFilepath = args[3];

	System.out.println();

	FileInputStream inputFile = new FileInputStream(filePath);

	Workbook wb = WorkbookFactory.create(inputFile);

	int LastRow = wb.getSheet(sheetName).getLastRowNum();

	System.out.print("Total Number of TestPlans in excel : " + LastRow + "\n\n");
	ChromeOptions options = new ChromeOptions();

	HashMap<String, Object> chromePrefs = new HashMap<String, Object>();

	chromePrefs.put("profile.default_content_settings.popups", 0);

	chromePrefs.put("download.prompt_for_download", "false");

	chromePrefs.put("download.default_directory", downloadFilepath);

	chromePrefs.put("browser.set_download_behavior",
		"{ behavior: 'allow' , downloadPath: '" + downloadFilepath + "'}");

	chromePrefs.put("plugins.plugins_disabled", new String[] { "Adobe Flash Player", "Chrome PDF Viewer" });

	options.setExperimentalOption("prefs", chromePrefs);

	options.addArguments("--headless");

	options.addArguments("--disable-gpu");

	options.setExperimentalOption("prefs", chromePrefs);

	ChromeOptions cap = new ChromeOptions();

	cap.setAcceptInsecureCerts(true);// (ChromeOptions.Accept, true);

	cap.setCapability(ChromeOptions.CAPABILITY, options);

	WebDriver chromeDriver = new ChromeDriver(options);

	String currentWindow = chromeDriver.getWindowHandle();

	chromeDriver.switchTo().window(currentWindow);

	chromeDriver.manage().window().maximize();

	WebDriverWait wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(60000));

	GetRQMReport gRR = new GetRQMReport();

	gRR.loginToRQM(chromeDriver, username, password);

	gRR.navigateToFilterView(chromeDriver);

	for (int i = 1; i <= LastRow; i++) {

	    System.out.println(gRR.searchAndSelectTestPlan(chromeDriver,
		    gRR.GetTPNameFromExcel(wb, sheetName, i, 0) + releaseNum));
	}

	List<WebElement> lstExport = chromeDriver
		.findElements(By.xpath("//img[@alt='Download as spreadsheet (.csv)']"));

	for (WebElement export : lstExport) {
	    try {
		export.click();
	    } catch (Exception e) {

	    }
	}
	WebElement exportToCSV = (WebElement) wait.until(ExpectedConditions.elementToBeClickable(
		By.xpath("//td/div[@title='1 minute ago']/../../td/a[contains(@href,'IPrintService')]")));

	exportToCSV.click();

	Thread.sleep(5000);

	File getLatestFile = getLatestFilefromDir(downloadFilepath);

	String fileName = getLatestFile.getName();

	System.out.println("File Downloaded with name '" + fileName + "' at '" + downloadFilepath + "'");

	String fileExt = ".csv";

	String date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());

	date = date.replaceAll("/", "_");

	date = date.replaceAll(" ", "_");

	date = date.replaceAll(":", "_");

	String oldFilePath = downloadFilepath + "\\" + fileName;

	File oldfile = new File(oldFilePath);

	String newFilename = releaseNum + "_RQMReport_" + date + fileExt;

	String newFilePath = downloadFilepath + "\\" + newFilename;

	File newfile = new File(newFilePath);

	if (oldfile.renameTo(newfile)) {

	    System.out.println("Downloaded File Renamed to '" + newFilename + "' at '" + downloadFilepath + "'");

	} else {

	    System.out.println("Sorry! the file can't be renamed");
	}

	gRR.LogOutRQM(chromeDriver);

	gRR.KillChromeDriver();

    }

    public String OpenFile(String path) {

	String status = "File Not Found '" + path + "'";

	try {

	    // constructor of file class having file as argument
	    File file = new File(path);

	    Desktop desktop = Desktop.getDesktop();

	    if (file.exists()) {// checks file exists or not

		desktop.open(file); // opens the specified file

		status = "File Found and  Opened '" + path + "'";
	    } else {
		status = "File Not Found at '" + path + "'";
	    }
	}

	catch (Exception e) {
	    e.printStackTrace();
	}
	return status;
    }

    public String GetTPNameFromExcel(Workbook wb, String Sheet, int Row, int Col) {
	return wb.getSheet(Sheet).getRow(Row).getCell(Col).toString();
    }

    // public String GetTPFromExcel(Workbook wb, String Sheet, int Row) { return
    // wb.getSheet(sheetName).getRow(Row).getCell(0).toString(); }

    private static File getLatestFilefromDir(String dirPath) {
	File dir = new File(dirPath);
	File[] files = dir.listFiles();
	if (files == null || files.length == 0) {
	    return null;
	}

	File lastModifiedFile = files[0];
	for (int i = 1; i < files.length; i++) {
	    if (lastModifiedFile.lastModified() < files[i].lastModified()) {
		lastModifiedFile = files[i];
	    }
	}
	return lastModifiedFile;
    }

    public void navigateToFilterView(WebDriver driver) {

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30000));

	WebElement testPoint = (WebElement) wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
		"//div[contains(.,'Total:')]/preceding-sibling::div[contains(.,'Progress')]/following-sibling::div/../..")));

	testPoint.click();

	WebElement selectedTP = (WebElement) wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
		"//div[contains(text(),'In any test plan')]/../../following-sibling::tr/td/span/div[@class='dijit dijitReset dijitInline dijitCheckBox dijitCheckBoxChecked dijitChecked']")));

	selectedTP.click();
    }

    String returnType;

    public String searchAndSelectTestPlan(WebDriver driver, String testPlanName) {

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30000));

	WebElement filterSearchTB = null;

	try {
	    filterSearchTB = driver.findElement(By.xpath(
		    "//span[@class='filter-area filter-area-inline-selector']/input[@aria-label='This is Test Plans table: filter text input']|//span/input[@title='Type Filter Text or ID']"));
	} catch (Exception e) {
	    driver.findElement(By.xpath(
		    "//span/button[@name='This is Test Plans table Clear Filter Text']/img[@alt='Clear Filter Text']"))
		    .click();
	}
	if (filterSearchTB != null) {

	    filterSearchTB.clear();

	    filterSearchTB.click();

	    filterSearchTB.sendKeys(testPlanName, Keys.ENTER);
	} else {
	    driver.findElement(By.xpath(
		    "//span/button[@name='This is Test Plans table Clear Filter Text']/img[@alt='Clear Filter Text']"))
		    .click();

	    filterSearchTB = driver.findElement(By.xpath("//span/input[@title='Type Filter Text or ID']"));

	    filterSearchTB.clear();

	    filterSearchTB.click();

	    filterSearchTB.sendKeys(testPlanName, Keys.ENTER);
	}

	WebElement testPlanSearchResult = null;

	try {

	    testPlanSearchResult = driver.findElement(By.xpath("//div[text(),'" + testPlanName + "']"));

	    returnType = "Given TestPlan " + testPlanName + " Found";
	} catch (Exception e) {

	    returnType = "Unable to find given testPlan " + testPlanName + " in RQM";
	}

	if (testPlanSearchResult != null) {

	    WebElement tpSearchResult = (WebElement) wait
		    .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'" + testPlanName
			    + "')]/../../../../preceding-sibling::td/span/div[@class='dijit dijitReset dijitInline dijitCheckBox']")));

	    tpSearchResult.click();

	}

	return returnType;
    }

    public void loginToRQM(WebDriver driver, String username, String password)
	    throws IOException, InterruptedException {

	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	driver.get(
		"https://jazz.cerner.com:9443/qm/web/console/IP#action=com.ibm.rqm.planning.home.actionDispatcher&subAction=viewTestPlans");

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25000L));

	WebElement usernameTxtBx = (WebElement) wait.until(
		ExpectedConditions.elementToBeClickable(By.cssSelector("input[id*='userId'][name*='username']")));

	usernameTxtBx.sendKeys(new CharSequence[] { username });

	WebElement pswrdTxtBx = (WebElement) wait
		.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='j_password']")));

	pswrdTxtBx.sendKeys(new String(password));

	driver.findElement(By.name("rememberUserId")).click();

	driver.findElement(By.xpath("//button[contains(text(),'Log In')]")).click();

	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

	System.out.println();

	Thread.sleep(10000L);

	System.out.println("SuccessFully Logged in to RQM ");

	System.out.println();
    }

    public void LogOutRQM(WebDriver driver) throws IOException, InterruptedException {

	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25000L));

	Actions builder = new Actions(driver);

	System.out.println("Logging Out...");

	Thread.sleep(5000L);

	builder.sendKeys(new CharSequence[] { Keys.PAGE_UP }).build().perform();

	Thread.sleep(5000L);

	builder.sendKeys(new CharSequence[] { Keys.PAGE_UP }).build().perform();

	Thread.sleep(5000L);

	WebElement logoutbtn = (WebElement) wait.until(
		ExpectedConditions.elementToBeClickable(By.cssSelector("span[class='caret jazz-ui-MenuPopup-caret']")));

	logoutbtn.click();

	Thread.sleep(15000L);

	WebElement logout = (WebElement) wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
		"//span[@data-dojo-attach-point='containerNode'][contains(@id,'text')][contains(.,'Log Out')]")));

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
	} catch (Exception e) {

	    e.printStackTrace();
	}
    }

}
