package learnSelenium;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Program {
	
public static void main(String[] args) {
	/*for(int i=1;i<=10;i=+1) {
		System.out.println(i);
	}*/
	System.setProperty("webdriver.chrome.driver", "./Exe/chromedriver.exe ");
	WebDriver driver=new ChromeDriver();
	/*System.setProperty("webdriver.gecko.driver", "./Exe/geckodriver.exe ");
	WebDriver driver=new FirefoxDriver();*/
	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.manage().window().maximize();
    driver.get("www.axisbank.com");
    
   String s= driver.findElement(By.xpath("//img[@id='CaptchaImage0']")).getAttribute("src");
   System.out.println(s);
   
   
   /* driver.findElement(By.className("gsfi")).sendKeys("pnb Share price"+Keys.ENTER);
    String price = driver.findElement(By.xpath(".//*[@id='knowledge-finance-wholepage__entity-summary']"
    		+ "/div/g-card-section/div/g-card-section/div[2]/span[1]/span/span[1]")).getText();
    System.out.println("The Current Share Price of PNB is: "+ price);*/
    driver.quit();
}
}
