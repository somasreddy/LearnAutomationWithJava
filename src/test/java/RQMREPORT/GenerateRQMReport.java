package RQMREPORT;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GenerateRQMReport {
	
	static String sheetName = null;

	static String filePath = null;

	static String username = null;

	static String password = null;

	static Workbook wb = null;

	static Scanner input = new Scanner(System.in);

	String returnType;

	public String GetTPNameFromExcel(Workbook wb, String Sheet, int Row, int Col) {
		return wb.getSheet(sheetName).getRow(Row).getCell(Col).toString();
	}

	public String GetTPFromExcel(Workbook wb, String Sheet, int Row) {
		return wb.getSheet(sheetName).getRow(Row).getCell(0).toString();
	}

	public File getTheNewestFile(String filePath, String ext) {
		File theNewestFile = null;
		File dir = new File(filePath);
		WildcardFileFilter wildcardFileFilter = new WildcardFileFilter("*." + ext);
		File[] files = dir.listFiles((FileFilter)wildcardFileFilter);
		if (files.length > 0) {
			Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
			theNewestFile = files[0];
		} 
		return theNewestFile;
	}

	public void navigateToFilterView(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30000L));
		WebElement testPoint = (WebElement)wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(.,'Total:')]/preceding-sibling::div[contains(.,'Progress')]/following-sibling::div/../..")));
		testPoint.click();
		WebElement selectedTP = (WebElement)wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'In any test plan')]/../../following-sibling::tr/td/span/div[@class='dijit dijitReset dijitInline dijitCheckBox dijitCheckBoxChecked dijitChecked']")));
		selectedTP.click();
	}

	public String searchAndSelectTestPlan(WebDriver driver, String testPlanName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30000L));
		WebElement filterSearchTB = null;
		try {
			filterSearchTB = driver.findElement(By.xpath("//span[@class='filter-area filter-area-inline-selector']/input[@aria-label='This is Test Plans table: filter text input']|//span/input[@title='Type Filter Text or ID']"));
		} catch (Exception e) {
			driver.findElement(By.xpath("//span/button[@name='This is Test Plans table Clear Filter Text']/img[@alt='Clear Filter Text']")).click();
		} 
		if (filterSearchTB != null) {
			filterSearchTB.clear();
			filterSearchTB.click();
			filterSearchTB.sendKeys(new CharSequence[] { testPlanName, (CharSequence)Keys.ENTER });
		} else {
			driver.findElement(By.xpath("//span/button[@name='This is Test Plans table Clear Filter Text']/img[@alt='Clear Filter Text']")).click();
			filterSearchTB = driver.findElement(By.xpath("//span/input[@title='Type Filter Text or ID']"));
			filterSearchTB.clear();
			filterSearchTB.click();
			filterSearchTB.sendKeys(new CharSequence[] { testPlanName, (CharSequence)Keys.ENTER });
		} 
		WebElement testPlanSearchResult = null;
		try {
			testPlanSearchResult = driver.findElement(By.xpath("//div[(text()='" + testPlanName + "')]"));
			this.returnType = "Given TestPlan " + testPlanName + " Found";
		} catch (Exception e) {
			this.returnType = "Unable to find given testPlan " + testPlanName + " in RQM";
		} 
		if (testPlanSearchResult != null) {
			WebElement tpSearchResult = (WebElement)wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'" + testPlanName + "')]/../../../../preceding-sibling::td/span/div[@class='dijit dijitReset dijitInline dijitCheckBox']")));
			tpSearchResult.click();
		} 
		return this.returnType;
	}

	public void loginToRQM(WebDriver driver, String username, String password) throws IOException, InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10L));
		
		driver.get("https://jazz.cerner.com:9443/qm/web/console/IP#action=com.ibm.rqm.planning.home.actionDispatcher&subAction=viewTestPlans&updateAction=clear-filter-selection");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25000L));
		
		WebElement usernameTxtBx = (WebElement)wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("//input[@name='j_username']")));
		
		usernameTxtBx.sendKeys(new CharSequence[] { username });
		
		WebElement pswrdTxtBx = (WebElement)wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='j_password']")));
		
		pswrdTxtBx.sendKeys(new CharSequence[] { new String(password) });
		
		driver.findElement(By.name("rememberUserId")).click();
		
		driver.findElement(By.xpath("//button[contains(text(),'Log In')]")).click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30L));
		
		System.out.println();
		
		Thread.sleep(10000L);
		
		System.out.println("SuccessFully Logged in to RQM ");
		
		System.out.println();
	}

	public void LogOutRQM(WebDriver driver) throws IOException, InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10L));
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25000L));
		
		Actions builder = new Actions(driver);
		
		System.out.println("Logging Out...");
		
		Thread.sleep(5000L);
		
		builder.sendKeys(new CharSequence[] { (CharSequence)Keys.PAGE_UP }).build().perform();
		
		Thread.sleep(5000L);
		
		builder.sendKeys(new CharSequence[] { (CharSequence)Keys.PAGE_UP }).build().perform();
		
		Thread.sleep(5000L);
		
		WebElement logoutbtn = (WebElement)wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("//img[@id='userPhoto']")));
		
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
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}




















}
