package packGrid;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestGrid01 extends OpenNodeBrowser
{
	
	@Test
	public void GridTC01() throws Exception
	{
		url="http://amazon.in";
		openHomePage(url);
	}
		
	
	

}
