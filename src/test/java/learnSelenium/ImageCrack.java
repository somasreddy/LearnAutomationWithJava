package learnSelenium;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class ImageCrack {
	/*public static String crackImage(String filePath) {
        File imageFile = new File(filePath);
        ITesseract instance = new Tesseract();
        try {
            String result = instance.doOCR(imageFile);
            return result;
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
            return "Error while reading image";
        }
	}*/
public static void main(String[] args) {
	/*for(int i=1;i<=10;i=+1) {
		System.out.println(i);
	}*/
	System.setProperty("webdriver.chrome.driver", "./Exe/chromedriver.exe ");
	WebDriver driver=new ChromeDriver();
	/*System.setProperty("webdriver.gecko.driver", "./Exe/geckodriver.exe ");
	WebDriver driver=new FirefoxDriver();*/
	driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	driver.manage().window().maximize();
    driver.get("https://qa1.ricago.com/GstWeb");
    
   String s= driver.findElement(By.xpath("//img[@id='CaptchaImage0']")).getAttribute("src");
   System.out.println(s);
//   System.out.println(crackImage(s));
}
}