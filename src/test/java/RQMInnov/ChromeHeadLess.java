package RQMInnov;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeHeadLess {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "./EXe/chromedriver.exe");
		// Set Chrome Headless mode as TRUE
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		// Instantiate Web Driver
		WebDriver driver = new ChromeDriver(options);
		driver.get("http://www.fb.com");
		System.out.println("Page title is - " + driver.getTitle());
		// Search on Google
		driver.findElement(By.name("q")).sendKeys("selenium webdriver");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Display number of results on Console
		System.out.println("Total Results - " + driver.findElement(By.id("resultStats")).getText());

		driver.close();
	}

}