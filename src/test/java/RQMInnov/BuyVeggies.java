package RQMInnov;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BuyVeggies {
	
	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
				
		driver.navigate().to("https://rahulshettyacademy.com/seleniumPractise/#/");
		
		driver.manage().window().maximize();
		
		Thread.sleep(10000);//
		WebElement Apple_addToCart=driver.findElement(By.xpath("//div/h4[contains(text(),'Apple')]/..//div/button"));
		Apple_addToCart.click();
		Thread.sleep(5000);
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//a/img[@alt='Cart']")).click();
		
		Thread.sleep(5000);
		
		String InCart=driver.findElement(By.xpath("//*[@id=\"root\"]/div/header/div/div[3]/div[2]/div[1]/div[1]/ul/li/div[1]/p[1]")).getText();
		
		System.out.println(InCart);
		
		driver.findElement(By.xpath("//div/button[text()='PROCEED TO CHECKOUT']")).click();
		
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("//button[text()='Place Order']")).click();
		
		Thread.sleep(5000);
		
		WebElement s=driver.findElement(By.xpath("//div/select"));
		
		Select selectCont= new Select(s);
		
		selectCont.selectByVisibleText("India");
		
		driver.findElement(By.className("chkAgree")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//button[text()='Proceed']")).click();
		
		
		Thread.sleep(5000);
		
		driver.close();
		
	}

}
