package learnSelenium;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrokenLinks {

    private static WebDriver driver = null;
    static int count = 0;
    static int count1 = 0;

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
	System.setProperty("webdriver.chrome.driver", "./Exe/chromedriver.exe ");
	String homePage = "http://www.zlti.com";
	String url = "";
	HttpURLConnection huc = null;
	int respCode = 200;

	driver = new ChromeDriver();

	driver.manage().window().maximize();

	driver.get(homePage);

	List<WebElement> links = driver.findElements(By.tagName("a"));

	Iterator<WebElement> it = links.iterator();

	while (it.hasNext()) {

	    url = it.next().getAttribute("href");

//            System.out.println(url);

	    if (url == null || url.isEmpty()) {
		System.out.println("URL is either not configured for anchor tag or it is empty");

		continue;
	    }

	    if (!url.startsWith(homePage)) {
		System.out.println("URL belongs to another domain, skipping it.");
		System.out.println(url);
		continue;
	    }

	    try {
		huc = (HttpURLConnection) (new URL(url).openConnection());

		huc.setRequestMethod("HEAD");

		huc.connect();

		respCode = huc.getResponseCode();

		if (respCode >= 400) {
		    System.out.println(url + " is a broken link");
		    count++;
		} else {
		    System.out.println(url + " is a valid link");
		    count1++;
		}

	    } catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
	System.out.println("No.of Invalid links =" + count++);
	System.out.println("No.of Valid links =" + count1++);
	driver.quit();

    }
}
