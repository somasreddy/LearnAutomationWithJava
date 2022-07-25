package learnSelenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//impsort org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AlertHandles {
	public static void main(String[] args) {
		// System.setProperty("webdriver.chrome.driver", "./Exe/chromedriver.exe ");
		// System.setProperty("webdriver.gecko.driver", "./Exe/geckodriver.exe ");
		// WebDriver driver=new ChromeDriver();
		WebDriver driver = new FirefoxDriver();
		driver.get("https://www.irctc.co.in");
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		driver.findElement(By.id("loginbutton")).click();
		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();
		driver.quit();
	}
}
