package learnSelenium;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Acuwinreg {

	public static void main(String[] args) throws InterruptedException {
		int count=0;
		while(count<=40) {
			for(int i=1;i<=20;i++){
			System.setProperty("webdriver.chrome.driver", "./Exe/chromedriver.exe ");
	    WebDriver driver=new ChromeDriver();
//	    driver.manage().deleteAllCookies();
	    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    driver.manage().window().maximize();
//	    driver.navigate().to("http://acumoney.win/1771200663952/");
	    driver.get("http://acumoney.win/1771200663952/");
	    driver.findElement(By.xpath(".//*[@id='memberlogin']/div[1]/a[1]/u")).click();
		driver.findElement(By.xpath(".//*[@id='username']")).sendKeys("prnavk"+i);
		driver.findElement(By.id("email")).sendKeys("pranav"+i+"@grr.la");
		driver.findElement(By.id("password1")).sendKeys("pranav321");
		driver.findElement(By.id("password2")).sendKeys("pranav321");
//		driver.findElement(By.xpath(".//*[@id='secpic']")).sendKeys("");
		Thread.sleep(5000);
		driver.findElement(By.xpath(".//*[@id='main']/form/table/tbody/tr[12]/td/div/a/img")).click();
		Thread.sleep(2000);
		Alert alert = driver.switchTo().alert();
		alert.accept();
		driver.findElement(By.xpath(".//*[@id='memberlogin']/div[1]/table/tbody/tr[14]/td[1]/a/b/font/u")).click();
		driver.quit();
		count++;
		}
			continue;
		}
	}

}
