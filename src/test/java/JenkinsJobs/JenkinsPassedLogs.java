package JenkinsJobs;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
public class JenkinsPassedLogs {
	static String Jenkins_Link="http://w1752330.northamerica.cerner.net:8080/view/1501DEPM_DWK/";
	static String downloadFilepath = "C:\\Users\\VS065203\\Downloads\\PAP_Passed";
	static String Start_Date="2019-07-09"; 
	static String Environment="1501DEPM - ";
	
	public static void main(String[] args) throws ParseException, InterruptedException, InvalidFormatException, IOException 
	{
		  System.setProperty("webdriver.chrome.driver", "./EXe/chromedriver.exe");
		  ChromeOptions options = new ChromeOptions();
		  HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		  chromePrefs.put("profile.default_content_settings.popups", 0);
		  chromePrefs.put("download.prompt_for_download", "false");
		  chromePrefs.put("download.default_directory", downloadFilepath);
		  chromePrefs.put("plugins.plugins_disabled", new String[]{"Adobe Flash Player", "Chrome PDF Viewer"});
		  options.setExperimentalOption("prefs", chromePrefs);
		  options.setAcceptInsecureCerts(true);
			/*
			 * DesiredCapabilities cap = DesiredCapabilities.chrome();
			 * cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			 * cap.setCapability(ChromeOptions.CAPABILITY, options);
			 */
		  JenkinsPassedLogs jps= new JenkinsPassedLogs();
		  WebDriver driver = new ChromeDriver(options);
//		  sendCommandForDownloadChromeHeadLess((HttpCommandExecutor)((RemoteWebDriver) driver).getCommandExecutor(),((RemoteWebDriver) driver).getSessionId(),downloadFilepath);
//          WebDriver driver = new ChromeDriver(cap);
	  	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	  	  Map<String, String> map = new HashMap<String, String>();
	  	  driver.get(Jenkins_Link);
	  	  driver.manage().window().maximize();
	  	  driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		  driver.findElement(By.xpath("//tr[1]/th[4]/a")).click();
		  driver.findElement(By.xpath("//th[4]/a/span")).isDisplayed();
	      List<WebElement> lst=driver.findElements(By.xpath("//tbody/tr[@class=' job-status-blue']/td[4]"));
	  	  List<String> lst1 = new ArrayList<String>();
	  	  int count=1;
	  	  for(int i=0;i<lst.size(); i++) {
	  		  String Val= lst.get(i).getText();
	  		  String Date=lst.get(i).getAttribute("data");
	  		  String[] DateVal=Date.split("T");
	  		  Date date1 = sdf.parse(DateVal[0]);
	  		  Date date2 = sdf.parse(Start_Date);
	        if(date1.compareTo(date2) > 0) 
	        {
	        	String Job=lst.get(i).findElement(By.xpath("..")).getAttribute("id");
	        	String[] Jobname=Job.split(Environment);
	        	lst1.add(Jobname[1]);
	        	String mainWindow=driver.getWindowHandle();
	        	WebElement joblink=lst.get(i).findElement(By.xpath("../td[3]/a"));
	        	Actions action= new Actions(driver);
	        	action.keyDown(Keys.SHIFT).click(joblink).keyUp(Keys.SHIFT).build().perform();
	        	Thread.sleep(3000);
	        	Set<String> set =driver.getWindowHandles();
	        	Iterator<String> itr= set.iterator();
	        	String BuildName= null;
	        	while(itr.hasNext())
	        	{
	        		String childWindow=itr.next();
	        	if(!mainWindow.equals(childWindow))
	        	{
	        	    driver.switchTo().window(childWindow);
	        	    driver.findElement(By.xpath("//td/a[.='Last Successful Artifacts']")).click();
		            driver.findElement(By.xpath("//td/a[.='touchstone']")).click();
		        	driver.findElement(By.xpath("//td/a[contains(text(), 'Module')]")).click();
		        	BuildName=driver.findElement(By.xpath("//div/form/a[contains(.,' Module')]")).getText();
		        	driver.findElement(By.xpath("//td/div/a[contains(@href,'Module.zip')]")).click();;
		        	File file = new File(downloadFilepath+"\\"+BuildName+".zip");
		        	while (!file.exists()) {
		        	    Thread.sleep(1000);
		        	}
		        	driver.close();
	        	 }
	        }
	        driver.switchTo().window(mainWindow);
	        Path path = Paths.get(downloadFilepath+"\\"+BuildName+".zip");
	        if (Files.exists(path)) {
	        	File newfile = jps.getTheNewestFile(downloadFilepath, "zip");
		        newfile.renameTo(new File(downloadFilepath+"\\"+Jobname[1]+".zip"));
	        }	        
				System.out.println(count+". " + Jobname[1]);
	        count++;
	        }
	  	  }
	  	System.out.println("Total Plans Passed from the last Execution after "+ Start_Date+" are:"+ lst1.size());
	  	driver.close();

	}
	
	public File getTheNewestFile(String filePath, String ext) 
	{
	     File theNewestFile = null;
	     File dir = new File(filePath);
	     FileFilter fileFilter = new WildcardFileFilter("*." + ext);
	     File[] files = dir.listFiles(fileFilter);
	     if (files.length > 0) {
	         Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
	         theNewestFile = files[0];
	     }
	     return theNewestFile;
     }

}
