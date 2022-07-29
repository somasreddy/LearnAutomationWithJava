package RQMInnov;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Difference {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "./EXe/chromedriver.exe ");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.get("http://w1752283.northamerica.cerner.net:8080/");
		List<WebElement> JobsInAll = driver.findElements(By.xpath("//tr[starts-with(@id,'job_EggPlant - ICU-WF-')]"));
		System.out.println(JobsInAll.size());
		for (int i = 0; i < JobsInAll.size(); i++) {
			String option = JobsInAll.get(i).getText();
			System.out.println(option);
			// arrList.add(option);

		}
		System.out.println("............................");
		/*
		 * driver.navigate().to(
		 * "http://w1752283.northamerica.cerner.net:8080/view/EggPlant_ICU/");
		 * List<WebElement> JobsInICU=driver.findElements(By.
		 * xpath("//tr[starts-with(@id,'job_EggPlant - ICU-WF-')]"));
		 * System.out.println(JobsInICU.size()); for(int i=0;i<JobsInICU.size();i++) {
		 * String option = JobsInICU.get(i).getText(); System.out.println(option);
		 * //arrList.add(option);
		 * 
		 * } System.out.println(JobsInAll.equals(JobsInICU)); List<WebElement> uncommon
		 * = new ArrayList<WebElement>(); for (WebElement S : JobsInAll) { if
		 * (!JobsInICU.contains(S)) uncommon.add(S); } for (WebElement S : JobsInICU) {
		 * if (!JobsInAll.contains(S)) uncommon.add(S); } for(int
		 * i=0;i<uncommon.size();i++) { WebElement option = uncommon.get(i);
		 * System.out.println(option); //arrList.add(option);
		 * 
		 * }
		 */
	}
}