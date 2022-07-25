package JenkinsJobs;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LastQuarterPlans {

	public static void main(String[] args) {
		  System.setProperty("webdriver.chrome.driver", "./EXe/chromedriver.exe");
	  	  WebDriver driver=new ChromeDriver(); 
	  	  driver.get("https://github.cerner.com/EggPlant/IPDev-Millennium-ClinicalAssessment/tree/master/IPDev-Millennium-Clinical-Assessment.suite/Scripts/Workflow");
	  	  driver.manage().window().maximize();
	  	  List<WebElement> lst=driver.findElements(By.xpath("//tbody/tr[@class=' job-status-red']/td[4] | //tbody/tr[@class=' job-status-nobuilt']/td[4]"));
	  	  for(int i=0;i<lst.size(); i++) {
	  		  String s= lst.get(i).getText();
	  		  System.out.println(s);
	  	  }
	}
}

