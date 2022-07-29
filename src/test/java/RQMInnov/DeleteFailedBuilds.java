package RQMInnov;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DeleteFailedBuilds {
	static String Jenkins_Link = "http://w1752330.northamerica.cerner.net:8080/view/1501DEPM_PAP/";

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./EXe/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get(Jenkins_Link);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		List<WebElement> allTriggerd = driver
				.findElements(By.xpath("//tr[@class=\" job-status-blue\"]|//tr[@class=\" job-status-red\"]"));
		for (int i = 0; i < allTriggerd.size(); i++) {
			String mainWindow = driver.getWindowHandle();
			String job = allTriggerd.get(i).getAttribute("id");
			WebElement jobLink = allTriggerd.get(i).findElement(By.xpath("/td[3]/a"));
			Actions action = new Actions(driver);
			action.keyDown(Keys.SHIFT).click(jobLink).keyUp(Keys.SHIFT).build().perform();
			Thread.sleep(3000);
			Set<String> set = driver.getWindowHandles();
			Iterator<String> itr = set.iterator();
			String BuildName = null;
			while (itr.hasNext()) {
				String childWindow = itr.next();
				if (!mainWindow.equals(childWindow)) {
					driver.switchTo().window(childWindow);
					driver.findElement(By.xpath("//td/a[.='Last Successful Artifacts']")).click();
					driver.findElement(By.xpath("//td/a[.='touchstone']")).click();
					driver.findElement(By.xpath("//td/a[contains(text(), 'Module')]")).click();
					BuildName = driver.findElement(By.xpath("//div/form/a[contains(.,' Module')]")).getText();
					driver.findElement(By.xpath("//td/div/a[contains(@href,'Module.zip')]")).click();
					;

					driver.close();
				}

			}

		}
		driver.close();
	}

}