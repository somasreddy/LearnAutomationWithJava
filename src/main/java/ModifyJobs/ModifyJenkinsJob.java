package ModifyJobs;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class ModifyJenkinsJob {
	  public static void main(String[] args) throws Exception {
	    Scanner in = new Scanner(System.in);
	    System.out.println("Please Enter the Jenkins Link : ");
	    String Jenkins_Link = in.nextLine();
	    //System.setProperty("webdriver.chrome.driver", "./EXe/chromedriver.exe");
	    ChromeDriver chromeDriver = new ChromeDriver();
	    chromeDriver.navigate().to(Jenkins_Link);
	    chromeDriver.manage().window().maximize();
	    chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    try {
	      if (chromeDriver.findElement(By.xpath("//a[contains(text(),'Log in')]")).isDisplayed()) {
	        chromeDriver.findElement(By.xpath("//a[contains(text(),'Log in')]")).click();
	      }
	    } catch (NoSuchElementException noSuchElementException) {}
	    try {
	      if (chromeDriver.findElement(By.xpath("//input[@id='j_username']")).isDisplayed()) {
	        System.out.println("Please Enter the UserName : ");
	        String userName = in.nextLine();
	        chromeDriver.findElement(By.xpath("//input[@id='j_username']")).sendKeys(new CharSequence[] { userName });
	        System.out.println("Please Enter the Password : ");
	        String password = in.nextLine();
	        chromeDriver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(new CharSequence[] { password });
	        chromeDriver.findElement(By.xpath("//input[@name='Submit']")).click();
	      } 
	    } catch (NoSuchElementException noSuchElementException) {}
	    chromeDriver.findElement(By.xpath("//a[contains(text(),'Name')]")).click();
	    Thread.sleep(1500L);
	    List<WebElement> lst = chromeDriver.findElements(By.xpath("//tr[contains(@id,'job_TS_R-')]/td[3]"));
	    for (int i = 0; i < lst.size(); i++) {
	      
	      String mainWindow = chromeDriver.getWindowHandle();
	      String job = ((WebElement)lst.get(i)).findElement(By.xpath("..")).getAttribute("id");
	      String[] jobName = job.split("job_");
	      System.out.println(String.valueOf(i + 1) + ". " + jobName[1]);
	      WebElement joblink = ((WebElement)lst.get(i)).findElement(By.xpath("a"));
	      Actions action = new Actions(chromeDriver);
	      action.keyDown(Keys.SHIFT).click(joblink).keyUp(Keys.SHIFT).build().perform();
	      Thread.sleep(1500L);
	      Set<String> set = chromeDriver.getWindowHandles();
	      Iterator<String> itr = set.iterator();
	      while (itr.hasNext()) {
	        String childWindow = (String)itr.next();
	        if (!mainWindow.equals(childWindow)) {
	          chromeDriver.switchTo().window(childWindow);
	          chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	          JavascriptExecutor jse = (JavascriptExecutor)chromeDriver;
	          chromeDriver.findElement(By.xpath("//a[.='Configure']")).click();
	          jse.executeScript("window.scrollBy(0,10000)", new Object[0]);
	          Thread.sleep(1500L);
	          boolean textFinder = false;
	          try {
	            textFinder = chromeDriver.findElement(By.xpath("//td/div/b[.='Text Finder']")).isDisplayed();
	          }
	          catch (NoSuchElementException noSuchElementException) {}
	          if (textFinder) {
	            WebElement saveButton = chromeDriver.findElement(By.xpath("//span/button[.='Save']"));
	            saveButton.click();
	            chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	            chromeDriver.close();
	            continue;
	          } 
	          WebElement buttonPBA = chromeDriver.findElement(By.xpath("//span/button[.='Add post-build action']"));
	          buttonPBA.click();
	          Thread.sleep(1500L);
	          chromeDriver.findElement(By.xpath("//li/a[.='Text Finder']/..")).click();
	          chromeDriver.findElement(By.xpath("//td/input[@name='_.alsoCheckConsoleOutput']")).click();
	          chromeDriver.findElement(By.xpath("//td/input[@name='_.regexp']")).sendKeys(new CharSequence[] { "RQMGO" });
	          chromeDriver.findElement(By.xpath("//td/input[@name='_.succeedIfFound']")).click();
	          WebElement applyButton = chromeDriver.findElement(By.xpath("//span/button[.='Apply']"));
	          applyButton.click();
	          WebElement saveButton = chromeDriver.findElement(By.xpath("//span/button[.='Save']"));
	          saveButton.click();
	          
	          chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	          chromeDriver.close();
	        } 
	      } 
	      chromeDriver.switchTo().window(mainWindow);
	      Thread.sleep(3000L);
	      System.out.println("-------------------------------------------------------------------------------");
	    } 
	    in.close();
	    chromeDriver.quit();
}
	  public static void dragTextFinder(WebDriver driver, WebElement slider, int xOffset, int yOffset) throws Exception {
		    Actions moveSlider = new Actions(driver);
		    Action action = moveSlider.clickAndHold(slider)
		      .moveByOffset(xOffset, yOffset)
		      .release()
		      .build();
		    action.perform();
		    Thread.sleep(500L);
		  }
}
