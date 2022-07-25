package JenkinsJobs;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ModifyJobs {
	 public static void main(String[] args) throws Exception {
		    Scanner in = new Scanner(System.in);
		    System.out.print("Please Enter the Jenkins Link : ");
		    String Jenkins_Link = in.nextLine();
		    WebDriverManager.chromedriver().setup();
		    WebDriver chromeDriver = new ChromeDriver();
		    chromeDriver.navigate().to(Jenkins_Link);
		    chromeDriver.manage().window().maximize();
		    chromeDriver.manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);
		    WebDriverWait wait = new WebDriverWait(chromeDriver, 10000L);
		    try {
		      if (chromeDriver.findElement(By.xpath("//a[contains(text(),'Log in')]")).isDisplayed()) {
		        chromeDriver.findElement(By.xpath("//a[contains(text(),'Log in')]")).click();
		      }
		    } catch (NoSuchElementException noSuchElementException) {}
		    try {
		      if (chromeDriver.findElement(By.xpath("//input[@id='j_username']")).isDisplayed()) {
		        System.out.print("Please Enter the UserName : ");
		        String userName = in.nextLine();
		        chromeDriver.findElement(By.xpath("//input[@id='j_username']")).sendKeys(new CharSequence[] { userName });
		        System.out.print("Please Enter the Password : ");
		        String password = in.nextLine();
		        chromeDriver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(new CharSequence[] { password });
		        chromeDriver.findElement(By.xpath("//input[@name='Submit']")).click();
		      } 
		    } catch (NoSuchElementException noSuchElementException) {}
		    System.out.print("Please Enter the Prefix of the Jobs to be Modified: ");
		    String prefix = in.nextLine();
		    chromeDriver.findElement(By.xpath("//a[contains(text(),'Name')]")).click();
		    Thread.sleep(1500L);
		    List<WebElement> lst = chromeDriver.findElements(By.xpath("//tr[contains(@id,'job_"+prefix+"')]/td[3]"));
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
		          chromeDriver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
		          JavascriptExecutor jse = (JavascriptExecutor)chromeDriver;
		          chromeDriver.findElement(By.xpath("//a[.='Configure']")).click();
		          jse.executeScript("window.scrollBy(0,2000)", new Object[0]);
		          Thread.sleep(1500L);
		          boolean textFinder = false;
		          try {
		            textFinder = chromeDriver.findElement(By.xpath("//b[contains(text(),'PowerShell')]")).isDisplayed();
		          }
		          catch (NoSuchElementException noSuchElementException) {}
		          if (textFinder) {
		            WebElement saveButton = chromeDriver.findElement(By.xpath("//span/button[.='Save']"));
		            saveButton.click();
		            chromeDriver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
		            chromeDriver.close();
		            continue;
		          } 
		          WebElement buttonPBA = chromeDriver.findElement(By.xpath("//b[contains(text(),'Touchstone Executor')]/../../../../../../following-sibling::div/span/span/button"));
		          buttonPBA.click();
		          Thread.sleep(1500L);
		          chromeDriver.findElement(By.xpath("//div/ul/li/a[contains(text(),'PowerShell')]")).click();
//		          WebElement PSCommandTxtBox = (WebElement)wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//b[contains(text(),'PowerShell')]/../../../following-sibling::tr/td/div[@class='CodeMirror']/div/textarea")));
		          Thread.sleep(4000L);
		          WebElement PSCommandTxtBox=chromeDriver.findElement(By.xpath("//div[@class='CodeMirror-scroll cm-s-default']"));
//		          PSCommandTxtBox.click();
		          PSCommandTxtBox.sendKeys(new CharSequence[] { "Get-DisplayResolution\r\n"
		          		+ "\r\n"
		          		+ "Set-DisplayResolution -Width 1920 -Height 1080 -Force\r\n"
		          		+ "\r\n"
		          		+ "Get-DisplayResolution" });
		          Actions builder = new Actions(chromeDriver);
		          WebElement from = chromeDriver.findElement(By.xpath("//b[contains(text(),'PowerShell')]/.."));
		          WebElement to = chromeDriver.findElement(By.xpath("//b[contains(text(),'Touchstone Executor')]/../.."));
		          builder.dragAndDrop(from, to).perform();
		          WebElement applyButton = chromeDriver.findElement(By.xpath("//span/button[.='Apply']"));
		          applyButton.click();
		          WebElement saveButton = chromeDriver.findElement(By.xpath("//span/button[.='Save']"));
		          saveButton.click();
		          chromeDriver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
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
	 
}
