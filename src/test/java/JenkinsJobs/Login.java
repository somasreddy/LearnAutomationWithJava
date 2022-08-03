package JenkinsJobs;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./Exe/chromedriver.exe ");
		WebDriver driver=new ChromeDriver();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(100));
		driver.manage().window().maximize();
		Actions actions= new Actions(driver);
		driver.get("https://jazz.cerner.com:9443/qm/web/console/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.findElement(By.xpath("//input[@name='j_username']")).sendKeys("VS065203");
		driver.findElement(By.xpath("//input[@name='j_password']")).sendKeys("Cerner1290");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='jazz_ui_MenuPopup_6']")));
		driver.findElement(By.xpath("//a[@id='jazz_ui_MenuPopup_6']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@id='jazz_ui_menu_MenuItem_0_text']")));
		actions.sendKeys(Keys.ENTER).perform();
//		driver.findElement(By.xpath("//span[@id='jazz_ui_menu_MenuItem_0_text']")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title='Show Inline Filters']")));
		driver.findElement(By.xpath("//div[@title='Show Inline Filters']")).click();
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Dummy Test Plan");
		driver.findElement(By.xpath("//button[@name='run']")).click();
		String TestPlanWind=driver.getWindowHandle();
		List<WebElement> TestPlanList=driver.findElements(By.xpath("//div[contains(text(),'Dummy Test Plan')]"));
		for(int i=0;i<TestPlanList.size();i++) {
			if(TestPlanList.get(i).getText()=="Dummy Test Plan") {
				actions.keyDown(Keys.SHIFT).click(TestPlanList.get(i)).keyUp(Keys.SHIFT).build().perform();
			}
		}
	}
}
