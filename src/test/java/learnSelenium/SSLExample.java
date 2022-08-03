package learnSelenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SSLExample {
	
	private WebDriver driver;

	@BeforeClass
	public void setUp() {
		//Creating new Firefox profile
		/*
		 * FirefoxProfile profile = new FirefoxProfile();
		 * profile.setAcceptUntrustedCertificates(true);
		 * profile.setAssumeUntrustedCertificateIssuer(false);
		 */
		FirefoxOptions profile= new FirefoxOptions();
		profile.setAcceptInsecureCerts(true);
		System.setProperty("webdriver.gecko.driver", "D:/eclipse-workspace/Automation/geckodriver.exe");
		driver = new FirefoxDriver(profile); 
		driver.manage().window().maximize();
	}
	
	@Test
	public void openApplication() {
		System.out.println("Navigating application");
		driver.get("https://cacert.org/");
		WebElement headingEle = driver.findElement(By.cssSelector(".story h3"));
		//Validate heading after accepting untrusted connection
		String expectedHeading = "Are you new to CAcert?";
		Assert.assertEquals(headingEle.getText(), expectedHeading);
	}
	
	@AfterClass
	public void tearDown() {
		if(driver!=null) 
			driver.quit();
	}
	
}