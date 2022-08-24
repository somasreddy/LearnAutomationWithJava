package learnSelenium;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.base.CharMatcher;

public class Acuwin {
	static int count=0;
public static void main(String[] args) throws InterruptedException {
	String str,str1,str2,str3,value,value1,value2,value3;
	System.setProperty("webdriver.chrome.driver", "./Exe/chromedriver.exe ");
    WebDriver driver=new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    driver.manage().window().maximize();
    driver.get("http://acumoney.win");
    driver.findElement(By.id("username")).sendKeys("somasreddy");;
    driver.findElement(By.id("password")).sendKeys("naniki143");;
    driver.findElement(By.name("button")).click();;
    driver.findElement(By.xpath("//*[@value='START WATCHING PAYED ADS']")).click();
    while(count<=1523) {
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    str =driver.findElement(By.xpath(".//*[@id='cimg1']/img")).getAttribute("src");
    str1=driver.findElement(By.xpath(".//*[@id='cimg2']/img")).getAttribute("src");
    str2=driver.findElement(By.xpath(".//*[@id='cimg3']/img")).getAttribute("src");
    str3=driver.findElement(By.xpath(".//*[@id='cimg4']/img")).getAttribute("src");
    value  = CharMatcher.ascii().retainFrom(str);
    value1 = CharMatcher.ascii().retainFrom(str1);
    value2 = CharMatcher.ascii().retainFrom(str2);
    value3 = CharMatcher.ascii().retainFrom(str3); 
    WebElement e=driver.findElement(By.xpath("//input[@type='text']"));
    e.sendKeys(value+value1+value2+value3);
    Thread.sleep(0);
    driver.findElement(By.xpath("//input[@value='CONTINUE']")).click();
    System.out.println("count="+count+","+ value+value1+value2+value3);
    count++;
    }    
    driver.findElement(By.xpath(".//*[@id='memberlogin']/div[1]/table/tbody/tr[14]/td[1]/a/b/font/u")).click();
    Thread.sleep(1000);
    driver.close();
}
}
