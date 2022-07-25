package TestPackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OpenUrl {
	private WebDriver driver;		
	@Test				
	public void OpenURL() {	
		driver.get("https://www.amazon.in/");  
		String title = driver.getTitle();				 
		Assert.assertTrue(title.contains("Online Shopping")); 		
	}	
	@BeforeTest
	public void beforeTest() {	
		WebDriverManager.chromedriver().setup();			
		driver=new ChromeDriver(); 
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}		
	@AfterTest
	public void afterTest() {
		driver.quit();			
	}		
}
