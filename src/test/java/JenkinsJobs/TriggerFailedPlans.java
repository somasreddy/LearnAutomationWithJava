package JenkinsJobs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class TriggerFailedPlans {
	static String Jenkins_Link="http://w1752330.northamerica.cerner.net:8080/view/1501DEPM_MRD/";
	static String LastBuild_Date="2019-06-08"; 
	static String Environment="1501DEPM - ";
public static void main(String[] args) throws ParseException, InterruptedException {
	 ChromeOptions options = new ChromeOptions();
	 options.addArguments("headless");
	 WebDriver driver=new ChromeDriver(options);
	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	 driver.get(Jenkins_Link);
	 driver.manage().window().maximize();
	 driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
	 driver.findElement(By.xpath("//tr[1]/th[5]/a")).click();
	 driver.findElement(By.xpath("//th[5]/a/span")).isDisplayed();
     List<WebElement> lst=driver.findElements(By.xpath("//tbody/tr[@class=' job-status-red']/td/a/img[@class='icon-clock icon-md']"));
     List<String> lst1 = new ArrayList<String>();
     int count=1;
     for(int i=0;i<lst.size(); i++) {
  		  String Val= lst.get(i).getText();
  		  String Date=lst.get(i).getAttribute("data");
  		  String[] DateVal=Date.split("T");
  		  Date date1 = sdf.parse(DateVal[0]);
  		  Date date2 = sdf.parse(LastBuild_Date);
        if(date1.compareTo(date2) > 0) 
        {
        	String Job=lst.get(i).findElement(By.xpath("..")).getAttribute("id");
        	String[] Jobname=Job.split(Environment);
        	lst1.add(Jobname[1]);
        	String mainWindow=driver.getWindowHandle();
				
				  WebElement joblink=lst.get(i).findElement(By.xpath("../td[3]/a")); 
				  Actions action= new Actions(driver);
				  action.keyDown(Keys.SHIFT).click(joblink).keyUp(Keys.SHIFT).build().perform(); 
				  Thread.sleep(1500); 
				  Set<String> set =driver.getWindowHandles();
				  Iterator<String> itr= set.iterator(); 
				  while(itr.hasNext()) { 
					  String childWindow=itr.next(); 
					  if(!mainWindow.equals(childWindow)) {
						  driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
						  driver.switchTo().window(childWindow);
						  driver.findElement(By.xpath("//div/a[.='Build Now']")).click();
						  Thread.sleep(3000); 
						  driver.close(); 
						  } 
					  }				
				  driver.switchTo().window(mainWindow);
				  System.out.println(count+". "+Jobname[1]); count++;
        }
      }
      for(String NameOfJob:lst1)
    	  System.out.println(NameOfJob);
      System.out.println("Total Retriggered Plans are:"+ lst1.size());
      driver.close();
	}
}
