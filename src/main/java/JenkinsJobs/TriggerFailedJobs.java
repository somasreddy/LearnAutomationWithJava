package JenkinsJobs;

import java.text.ParseException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TriggerFailedJobs {

    public static void main(String[] args) throws ParseException, InterruptedException {
	ChromeOptions options = new ChromeOptions();

	options.addArguments("headless");

	WebDriver driver = new ChromeDriver(options);

	try {
	    driver.get(args[2]);

	    driver.manage().window().maximize();

	    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	    boolean InvalidCreds = false;

	    try {
		LoginToJenkins(driver, args[0], args[1]);

		InvalidCreds = driver
			.findElement(By.xpath("//div[@class='alert alert-danger'][.='Invalid username or password']"))
			.isDisplayed();
	    } catch (NoSuchElementException nse) {
	    }

	    if (InvalidCreds == false) {

		boolean JobsToBuildFound = false;

		try {

		    JobsToBuildFound = driver
			    .findElement(By.xpath(
				    "//tbody/tr[@class=' job-status-red']/td/a/img[@class='icon-clock icon-md']"))
			    .isDisplayed();
		}

		catch (NoSuchElementException nse) {
		}

		if (JobsToBuildFound == true) {

		    List<WebElement> failedbuilds = driver.findElements(
			    By.xpath("//tbody/tr[@class=' job-status-red']/td/a/img[@class='icon-clock icon-md']"));

		    for (int i = 0; i < failedbuilds.size(); i++) {

			failedbuilds.get(i).click();

			Thread.sleep(1500);

			String Job = failedbuilds.get(i).findElement(By.xpath("../../..")).getAttribute("id");

			System.out.println("Retriggering - " + Job);

		    }

		} else {
		    throw new Exception("Invalid Node Link Entered");
		}
	    } else {

		throw new Exception("Invalid Credentials Passed");
	    }
	} catch (Exception e) {

	    e.printStackTrace();

	    KillChromeDriver(driver);
	}

	KillChromeDriver(driver);

	System.out.println("TriggerGO");

    }

    public static void KillChromeDriver(WebDriver driver) {

	try {

	    driver.quit();

	    System.out.println("\nKilling Chrome Driver");

	    Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");

	    Thread.sleep(2000L);

	    Runtime.getRuntime().exec("taskkill /f /im ruby.exe");

	    Thread.sleep(1000L);

	    System.exit(0);
	} catch (Exception e) {

	    e.printStackTrace();
	}
    }

    public static void LoginToJenkins(WebDriver driver, String UN, String PWD) {
	try {
	    if (driver.findElement(By.xpath("//a[contains(text(),'Log in')]")).isDisplayed()) {

		driver.findElement(By.xpath("//a[contains(text(),'Log in')]")).click();
	    }
	} catch (NoSuchElementException noSuchElementException) {
	}

	try {
	    if (driver.findElement(By.xpath("//input[@id='j_username']")).isDisplayed()) {

		String userName = UN;

		driver.findElement(By.xpath("//input[@id='j_username']")).sendKeys(new CharSequence[] { userName });

		String password = PWD;

		driver.findElement(By.xpath("//input[@placeholder='Password']"))
			.sendKeys(new CharSequence[] { password });

		driver.findElement(By.xpath("//div[@class='Checkbox-indicator']")).click();

		driver.findElement(By.xpath("//input[@name='Submit']")).click();
	    }
	} catch (NoSuchElementException noSuchElementException) {
	}
    }
}
