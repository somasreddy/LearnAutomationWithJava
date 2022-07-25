package learn;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DragAndDrop {
public static void main(String[] args) throws InterruptedException {
	System.setProperty("webdriver.chrome.driver", "./Exe/chromedriver.exe ");
	WebDriver driver=new ChromeDriver();
	driver.get("https://www.google.com/doodles#archive");
	Actions action=new Actions(driver);
	WebElement source=driver.findElement(By.xpath(".//*[@id='archive-list']/li[1]/div[1]/div[1]/a/img"));
	Thread.sleep(5000);
	WebElement target=driver.findElement(By.xpath(".//*[@id='archive-list']/li[51]/div[1]/div[1]/a/img"));
	Thread.sleep(5000);
	action.dragAndDrop(source, target);
	driver.close();
}
}
