package packGrid;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class OpenNodeBrowser {
    public WebDriver driver;
    public String url;

    @Parameters({ "node", "browser" })
    @BeforeTest
    public void NodeAndBrowser(String node, String browser) throws Exception {

	/*
	 * url="http://" + node + "/wd/hub"; URI nodeUrl; try { nodeUrl = new URI(url);
	 * // Node IP } catch (MalformedURLException e) {
	 * System.err.println("Invalid URL: " + url); e.printStackTrace(); }
	 * 
	 * DesiredCapabilities capabilities = new DesiredCapabilities();
	 * capabilities.setBrowserName(browser);
	 * 
	 * driver = new RemoteWebDriver(nodeUrl, capabilities); }
	 */
	String url = "http://" + node + "/wd/hub";
	URI nodeUri;
	try {
	    nodeUri = new URI(url); // Node IP
	} catch (URISyntaxException e) {
	    System.err.println("Invalid URI: " + url);
	    e.printStackTrace();
	    return; // Exit if the URI is invalid
	}

	DesiredCapabilities capabilities = new DesiredCapabilities();
	capabilities.setBrowserName(browser);

	try {
	    driver = new RemoteWebDriver(nodeUri.toURL(), capabilities); // Convert URI to URL
	} catch (MalformedURLException e) {
	    System.err.println("Failed to create RemoteWebDriver with URI: " + nodeUri);
	    e.printStackTrace();
	}
    }

    @AfterTest
    public void atAfterTest() {
	driver.quit();
    }

    public void openHomePage(String url) {
	driver.get(url);
	System.out.println("Home Page title : " + driver.getTitle());
    }

}
