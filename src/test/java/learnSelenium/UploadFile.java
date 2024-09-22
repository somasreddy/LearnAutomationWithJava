package learnSelenium;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class UploadFile {
	public static WebElement source;
	public static WebElement target;
	public static void main(String[] args) throws InterruptedException {
	
		System.setProperty("webdriver.chrome.driver", "./Exe/chromedriver.exe ");
//		System.setProperty("webdriver.gecko.driver", "./Exe/geckodriver.exe ");
		RemoteWebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		try {
	   //1.Without sendKeys
		driver.get("https://www.naukri.com");
		 String mw=driver.getWindowHandle();
		Set<String> allwinH=driver.getWindowHandles();
		System.out.println(allwinH);
		/*Iterator<String> itr=allwinH.iterator();
			while(itr.hasNext()) {
		      String cw=itr.next();*/		
		      for(String cw:allwinH) {
	            if(!mw.equalsIgnoreCase(cw))			
	            {    		
	                    // Switching to Child window
	                    driver.switchTo().window(cw);	                                                                                                           
	                  	// Closing the Child Window.
	                        driver.close();		
	            }		
	        }		
	    // Switching to Parent window i.e Main Window.
	    driver.switchTo().window(mw);
	    driver.findElement(By.xpath(".//*[@id='login_Layer']/div")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//input[@id='eLoginNew'] | //input[@id='eLogin']")).sendKeys("somasekhar.r@outlook.com");;
		driver.findElement(By.xpath("//input[@id='pLogin'] | //input[@name='PASSWORD']")).sendKeys("naniki143");
		driver.findElement(By.xpath("//button[@value='Login'] |//button[@value='Login']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	    driver.findElement(By.xpath("//a[.='UPDATE PROFILE']")).click();
	    Thread.sleep(3000);
	    /* ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,2000)");
	    WebElement e=driver.findElement(By.xpath("//li/span/following-sibling::a[contains(.,'Update')]"));
	    e.click();	   */
	    WebElement e1 =driver.findElement(By.xpath("//div/input[@id='attachCV']"));
	    ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",e1);
        e1.sendKeys("D:\\Work\\Backup\\Somasekhar\\Personal\\Resumes\\Somasekhar_Software_Test_Engineer_1.7 Yrs.docx");
	    	   
        /*WebElement e2=driver.findElement(By.xpath("//a[.='Update']"));
	    e2.click();*/
        Thread.sleep(10000);
        String s=driver.findElement(By.xpath("//span[@class='updateOn']")).getText();
	    System.out.println(s);
	    
		}
		
	    finally {
	    driver.close();
	    }
	    }
	}
