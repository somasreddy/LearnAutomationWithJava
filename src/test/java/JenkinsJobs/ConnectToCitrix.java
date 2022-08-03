package JenkinsJobs;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ConnectToCitrix {
	
	static WebDriver driver;
	static WebDriverWait wait;

	public static void main(String[] args) throws IOException, InterruptedException {
	
		ConnectToCitrix ctc= new ConnectToCitrix();
	
		String url="https://devenv.cernerworks.com/Citrix/CERNGOTEMPWeb/";		

		Map<String, Object> prefs = new HashMap<String, Object>();
		
		prefs.put("download.default_directory", "/directory/path");
		
		ChromeOptions options = new ChromeOptions();
		
		options.addArguments("start-maximized");
		
		options.setExperimentalOption("excludeSwitches",Arrays.asList("disable-popup-blocking"));
			
		options.setExperimentalOption("prefs", prefs);
		
		//DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		    
		//capabilities.setCapability("chromeOptions", options);
		
		WebDriverManager.chromedriver().setup();
		WebDriverManager.firefoxdriver().setup();
		    
		driver= new ChromeDriver(options);
//		driver = new FirefoxDriver();
		
		wait = new WebDriverWait(driver,Duration.ofSeconds(15));
		
		String currentWindow = driver.getWindowHandle();
	    
	    driver.switchTo().window(currentWindow);
	    
	    driver.manage().window().maximize();
	    
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
		try {
			ctc.ConnectCitrix(driver, url, "sp046767", "Cerner*5");	
	
		}
		catch (Exception e) {
		
			e.printStackTrace();
			
			//driver.quit();
		      
		    ctc.KillChromeDriver();
	}
		//driver.quit();
	      
	    ctc.KillChromeDriver();
}
public void ConnectCitrix(WebDriver driver, String url,String ctxUserName, String ctxPassword) throws IOException, InterruptedException {
	
	driver.navigate().to(url);
	
	WebElement DetectButn=(WebElement)wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[.='Detect Receiver']")));
	
	DetectButn.click();
	
	Thread.sleep(5000);
	
	Actions act= new Actions(driver);
	
	act.sendKeys(Keys.ENTER);
	
	Thread.sleep(5000);
	
	 WebElement alreadyInstalled=(WebElement)wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='protocolhandler-detect-alreadyInstalledLink']")));
	 alreadyInstalled.click();
		
	 WebElement userNameField = (WebElement)wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@id='username']"))));
	 userNameField.sendKeys(ctxUserName);
	 
	 WebElement passWordField = (WebElement)wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@id='password']"))));
	 passWordField.sendKeys(ctxPassword);
	 
	 WebElement logOnBtn = (WebElement)wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='loginBtn']")));
	 logOnBtn.click();
		
}
public void handleAlert(WebDriver driver) throws IOException, InterruptedException {
    if (isAlertPresent(driver)) {
   
    	Alert alert = driver.switchTo().alert();
      
    	System.out.println(alert.getText());
      
    	alert.dismiss();
    } 
  }
public boolean isAlertPresent(WebDriver driver) throws InterruptedException {
    try {
    
      Thread.sleep(5000);
      
      driver.switchTo().alert();
      
      return true;
    } 
    catch (NoAlertPresentException ex) {
    
    	return false;
    } 
  }
public void KillChromeDriver() {
    try {
            
      Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
      
      Thread.sleep(2000L);
      
      Runtime.getRuntime().exec("taskkill /f /im ruby.exe");
      
      Thread.sleep(1000L);
     
      System.exit(0);
      
      System.out.println("ChromeDriver has been Killed");
    }
    catch (Exception e) {
      
      e.printStackTrace();
    } 
  }
}
