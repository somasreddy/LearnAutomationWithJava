package packGrid;


import java.net.URL;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class OpenNodeBrowser 
{
	public WebDriver driver;
	public String url;
	
	@Parameters({"node","browser"})
	@BeforeTest
	public void NodeAndBrowser(String node,String browser) throws Exception
	{
		
		url="http://" + node + "/wd/hub";
		
		URL nodeUrl = new URL(url); //Node IP
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setBrowserName(browser);
		
		driver = new RemoteWebDriver(nodeUrl, capabilities);		
		}
	
	@AfterTest
	public void atAfterTest()
	{
		driver.quit();		
	}
	
	public void openHomePage(String url)
	{
		driver.get(url);  
		System.out.println("Home Page title : " + driver.getTitle());
	}

}
