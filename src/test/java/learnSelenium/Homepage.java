package learnSelenium;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class Homepage {
public static void main(String[] args) {
//	System.setProperty("webdriver.chrome.driver", "./Exe/chromedriver.exe ");
	System.setProperty("webdriver.gecko.driver", "./Exe/geckodriver.exe ");
//	WebDriver driver=new ChromeDriver();
	WebDriver driver=new FirefoxDriver();
	driver.manage().window().maximize();
//	WebElement e = null;
//	Select s=new Select(e);
//	Actions a=new Actions(driver);
//	driver.switchTo().frame(index)
//			 Alert a;
//			Set<String> al=driver.getWindowHandles();
	driver.get("http://qa.gststar.com/gstweb");
	System.out.println("Title is "+driver.getTitle());
    driver.quit();
   }
}
