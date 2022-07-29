package RQMInnov;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class EggPlantJob_DWK {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./EXe/chromedriver.exe ");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.navigate().to(
				"https://github.cerner.com/EggPlant/IPDev-Millennium-ClinicianList/tree/master/IPDev-Millennium-ClinicianList.suite/Scripts/Workflows");
		driver.manage().window().maximize();
		List<WebElement> touchstoneLinks = driver.findElements(By.xpath("//td[@class='content']/span/a"));
		ArrayList<String> arrList = new ArrayList<String>();
		System.out.println(touchstoneLinks.size());
		for (int i = 0; i < touchstoneLinks.size(); i++) {
			String option = touchstoneLinks.get(i).getText();
			System.out.println(option);
			arrList.add(option);

		}
		driver.manage().window().maximize();
		driver.get("http://w1752283.northamerica.cerner.net:8080/");
		Thread.sleep(3000);
		driver.findElement(By.className("addTab")).click();
		driver.findElement(By.xpath(".//*[@id='name']")).sendKeys("EggPlant_DWK");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//td/input[@value='hudson.model.ListView']")).click();
		driver.findElement(By.id("ok-button")).click();
		driver.findElement(By.xpath("//button[.='OK']")).click();
		for (int i = 0; i < arrList.size(); i++) {
			String Pref = "EggPlant - ";
			String temp = arrList.get(i);
			String[] parts = temp.split(".script");
			for (String p : parts) {
				String p1 = p.replace("&", "_");
				System.out.println(p1);
				driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//a[.='New Item']")).click();
				driver.findElement(By.xpath(".//*[@id='name']")).sendKeys(Pref + p1);
				driver.findElement(By.xpath("//input[@placeholder='Type to autocomplete']"))
						.sendKeys("EggPlant - Dummy");
				driver.findElement(By.xpath("//label[.='Copy from']")).click();
				driver.findElement(By.xpath(".//*[@id='ok-button']")).click();
				driver.findElement(By.xpath("//tr[2]/td/textarea")).clear();
				driver.findElement(By.xpath("//tr[2]/td/textarea")).sendKeys(Pref + p1);
				driver.findElement(By.xpath("//*[@id=\"main-panel\"]/div/div/div/form/table/tbody/tr[63]/td[3]/input"))
						.clear();
				driver.findElement(By.xpath("//*[@id=\"main-panel\"]/div/div/div/form/table/tbody/tr[63]/td[3]/input"))
						.sendKeys("Eggplantnode_DWK");
				driver.findElement(By.xpath("//div/table/tbody/tr/td/textarea[@name='command']")).clear();
				String Job_Pref = "\"C:\\Program Files\\Eggplant\\runscript.bat\" \"C:\\Git\\IPDev-Millennium-ClinicianList\\IPDev-Millennium-ClinicianList.suite\\Scripts\\Workflows\\";
				String Job_Suf = ".script\" -param \"%Domain%\" -LicenserHost reqeggprlm01.northamerica.cerner.net -host %Environment% -port 3389 -username %AssociateID% -password cerner -type RDP -DefaultHeight 1080 -DefaultWidth 1920 -GlobalResultsFolder \"C:\\jenkins\\workspace\\%Job_Name%\\%BUILD_ID%\"";
				driver.findElement(By.xpath("//div/table/tbody/tr/td/textarea[@name='command']"))
						.sendKeys(Job_Pref + p + Job_Suf);
				Thread.sleep(1000);
				driver.findElement(By.xpath("//input[@name='_.recipients']")).clear();
				driver.findElement(By.xpath("//input[@name='_.recipients']"))
						.sendKeys("Kartheek.Donekal@cerner.com,Mamatha.Balla@cerner.com");
				Thread.sleep(1000);
				driver.findElement(By.xpath("//button[.='Save']")).click();
				Thread.sleep(1000);
			}
			driver.navigate().to("http://w1752283.northamerica.cerner.net:8080/view/EggPlant_DWK/");
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		}
	}
}
