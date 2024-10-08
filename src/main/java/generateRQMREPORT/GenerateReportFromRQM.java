package generateRQMREPORT;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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

public class GenerateReportFromRQM {

    static String downloadFilepath = null;

    static String sheetName = null;

    static String filePath = null;

    static String username = null;

    static String password = null;

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws IOException, InterruptedException, InvalidFormatException {

	filePath = ".\\RQMReportInput.xlsx";

	sheetName = "TestPlans";

	String username = args[0];

	String password = args[1];

	String releaseNum = args[2];

	String downloadFilepath = args[3];

	/*
	 * System.out.print("\nEnter the User Name : ");
	 * 
	 * String username = input.nextLine();
	 * 
	 * // Console console = System.console();
	 * 
	 * // char[] password = console.readPassword("Enter the password :");
	 * 
	 * System.out.print("\nEnter the Password : ");
	 * 
	 * String password= input.nextLine();
	 * 
	 * System.out.print("\nEnter the Release Number : ");
	 * 
	 * String releaseNum = input.nextLine();
	 * 
	 * String downloadFilepath = "C:\\Temp\\";
	 */

	System.out.println();

	Workbook wb = WorkbookFactory.create(new FileInputStream(filePath));

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

	WebDriver driver = new ChromeDriver(options);

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80L));

	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

	String currentWindow = driver.getWindowHandle();

	driver.switchTo().window(currentWindow);

	driver.manage().window().maximize();

	GenerateReportFromRQM gRFR = new GenerateReportFromRQM();

	gRFR.loginToRQM(driver, username, password, wait);

	gRFR.navigateToFilterView(driver, wait);

	for (int i = 1; i <= LastRow; i++) {

	    System.out.println(gRFR.searchAndSelectTestPlan(driver,
		    gRFR.GetTPNameFromExcel(wb, sheetName, i, 0) + releaseNum, wait));

	}

	Thread.sleep(5000);

	gRFR.clickExport(driver, wait);

	File getLatestFile = getLatestFilefromDir(downloadFilepath);

	String fileName = getLatestFile.getName();

	System.out.println("\nFile Downloaded with name '" + fileName + "' at '" + downloadFilepath + "'");

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

	    System.out.println("\nDownloaded File Renamed to '" + newFilename + "' at '" + downloadFilepath + "'");

	} else {

	    System.out.println("\nSorry! the file can't be renamed");
	}

	gRFR.cleanUp(driver, wait);
    }

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

    public String GetTPNameFromExcel(Workbook wb, String Sheet, int Row, int Col) {
	return wb.getSheet(sheetName).getRow(Row).getCell(Col).toString();
    }

    public void clickExport(WebDriver driver, WebDriverWait wait) throws InterruptedException {

	String exportIcon = "//*[contains(@id,'MenuButton_')]/a/img[@alt='Export as Spreadsheet (CSV)'] | //img[@alt='Export as Spreadsheet (CSV)']/../span[contains(@class,'Button-caret')]/../img";

	String exportCSVPath = "//td/div[@title='1 minute ago']/../../td/a[contains(@href,'download')]//div[@title='Test Execution Record.csv']/span";

	List<WebElement> lstExport = (List<WebElement>) wait
		.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(exportIcon)));

	Actions builder = new Actions(driver);

	for (WebElement export : lstExport) {

	    try {

		export.click();

		Thread.sleep(2500);

		WebElement exportAllPages = null;

		try {
		    exportAllPages = (WebElement) wait
			    .until(ExpectedConditions.elementToBeClickable(By.xpath("//td[.='Export All Pages']")));
		} catch (Exception e) {
		}

		if (exportAllPages != null) {

		    exportAllPages.click();

		    Thread.sleep(3000);

		    WebElement exportToCSV = (WebElement) wait
			    .until(ExpectedConditions.elementToBeClickable(By.xpath(exportCSVPath)));

		    exportToCSV.click();

		    Thread.sleep(5000);
		} else {

		    Thread.sleep(2500);

		    builder.sendKeys(new CharSequence[] { "E" }).build().perform();

		    Thread.sleep(2500);

		    builder.sendKeys(new CharSequence[] { Keys.ENTER }).build().perform();

		    Thread.sleep(5000);

		    WebElement exportToCSV = (WebElement) wait
			    .until(ExpectedConditions.elementToBeClickable(By.xpath(exportCSVPath)));

		    exportToCSV.click();

		    Thread.sleep(5000);
		}

	    } catch (Exception e) {
	    }
	}

    }

    String returnType;

    public String searchAndSelectTestPlan(WebDriver driver, String testPlanName, WebDriverWait wait)
	    throws InterruptedException {

	WebElement filterSearchTB = null;

	try {

	    filterSearchTB = driver.findElement(By.xpath(
		    "//span[@class='filter-area filter-area-inline-selector']/input[@aria-label='This is Test Plans table: filter text input']|//span/input[@title='Type Filter Text or ID']"));
	}

	catch (Exception e) {

	    driver.findElement(By.xpath(
		    "//img[@alt='Filter']/../../input[@title='Type filter text or ID']/../button[@title='Clear Filter Text']"))
		    .click();
	}

	if (filterSearchTB != null) {

	    filterSearchTB.clear();

	    filterSearchTB.click();

	    filterSearchTB.sendKeys(testPlanName, Keys.ENTER);

	    Thread.sleep(1500);
	}

	else {

	    driver.findElement(By.xpath(
		    "//img[@alt='Filter']/../../input[@title='Type filter text or ID']/../button[@title='Clear Filter Text']"))
		    .click();

	    filterSearchTB = driver
		    .findElement(By.xpath("//img[@alt='Filter']/../../input[@title='Type filter text or ID']"));

	    filterSearchTB.clear();

	    filterSearchTB.click();

	    filterSearchTB.sendKeys(testPlanName, Keys.ENTER);

	    Thread.sleep(1500);
	}

	WebElement testPlanSearchResult = null;

	String testPlanSearchXpath = "//div[contains(text(),'" + testPlanName + "') and not(contains(text(),'"
		+ testPlanName + "-')) and not(contains(text(),'" + testPlanName + ".')) and not(contains(text(),'"
		+ testPlanName + "_'))]";

	try {

	    testPlanSearchResult = (WebElement) wait
		    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(testPlanSearchXpath)));

	    returnType = "\nGiven TestPlan " + testPlanName + " Found";
	} catch (Exception e) {

	    returnType = "\nUnable to find given testPlan " + testPlanName + " in RQM";
	}

	if (testPlanSearchResult != null) {

	    WebElement tpSearchResult = (WebElement) wait
		    .until(ExpectedConditions.elementToBeClickable(By.xpath(testPlanSearchXpath
			    + "/../../../../preceding-sibling::td/span/div[@class='dijit dijitReset dijitInline dijitCheckBox']/input[@role='checkbox']")));

	    tpSearchResult.click();

	    Thread.sleep(2500);

	}

	return returnType;
    }

    public void navigateToFilterView(WebDriver driver, WebDriverWait wait) {

	WebElement testPoint = (WebElement) wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
		"//div[contains(.,'Total:')]/preceding-sibling::div[contains(.,'Progress')]/following-sibling::div/../..")));

	testPoint.click();

	WebElement selectedTP = (WebElement) wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
		"//div[contains(text(),'In any test plan')]/../../following-sibling::tr/td/span/div[@class='dijit dijitReset dijitInline dijitCheckBox dijitCheckBoxChecked dijitChecked']")));

	selectedTP.click();

	System.out.println("\n===== Navigated to Filter View =====");
    }

    public void loginToRQM(WebDriver driver, String username, String password, WebDriverWait wait)
	    throws IOException, InterruptedException {

	driver.get(
		"https://jazz.cerner.com:9443/qm/web/console/IP#action=com.ibm.rqm.planning.home.actionDispatcher&subAction=viewTestPlans&updateAction=clear-filter-selection");

	WebElement userName = (WebElement) wait.until(
		ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[id*='userId'][name*='username']")));
	userName.click();
	userName.click();
	userName.sendKeys(username);

	WebElement userPwd = driver.findElement(By.cssSelector("input[id*='password'][type='password']"));
	userPwd.sendKeys(password);

	WebElement rememberMeChkBox = driver
		.findElement(By.cssSelector("input[name ='rememberUserId'][type='checkbox']"));
	rememberMeChkBox.click();

	WebElement loginBtn = driver.findElement(By.cssSelector("button[type='submit']"));
	loginBtn.click();

	System.out.println("\n===== Login Method Completed =====");
    }

    public void logOutRQM(WebDriver driver, WebDriverWait wait) throws InterruptedException {

	Actions builder = new Actions(driver);

	System.out.println("\n===== Logging Out From RQM... ======");

	Thread.sleep(5000);

	builder.sendKeys(new CharSequence[] { Keys.PAGE_UP }).build().perform();

	Thread.sleep(1000);

	builder.sendKeys(new CharSequence[] { Keys.PAGE_UP }).build().perform();

	Thread.sleep(3000);

	WebElement profileBtn = wait
		.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='user-name']/../img")));

	profileBtn.click();

	Thread.sleep(3000);

	builder.sendKeys(new CharSequence[] { "L" }).build().perform();
	builder.sendKeys(new CharSequence[] { "L" }).build().perform();

	Thread.sleep(2500);

	builder.sendKeys(new CharSequence[] { Keys.ENTER }).build().perform();

	builder.sendKeys(new CharSequence[] { Keys.ENTER }).build().perform();

	Thread.sleep(5000);

	driver.quit();

    }

    public void cleanUp(WebDriver driver, WebDriverWait wait) throws InterruptedException {

	try {

	    logOutRQM(driver, wait);

	    System.out.println("\n====== Exiting Chrome Driver ======");

	    System.out.println("\n===============================");

	    Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");

	    Thread.sleep(2000L);

	} catch (IOException e) {

	    e.printStackTrace();
	}
    }
}
