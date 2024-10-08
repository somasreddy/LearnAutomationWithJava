package learnSelenium;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrokenLinksChecker {

    public static void main(String[] args) {
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	Scanner sc = new Scanner(System.in);
	System.out.println("Enter the url to find broken Links: ");
	String urlToCheck = sc.nextLine();
	driver.get(urlToCheck);
	sc.close();
	// Find all <a> elements (links)
	List<WebElement> links = driver.findElements(By.tagName("a"));
	// Loop through each link and verify if it is broken
	for (WebElement link : links) {
	    String url = link.getAttribute("href");
	    if (url != null && !url.isEmpty()) {
		try {
		    verifyLink(url);
		} catch (IOException | URISyntaxException e) {
		    System.out.println("Error checking link: " + url);
		}
	    } else {
		System.out.println("Empty or null URL found.");
	    }
	}

	// Close the browser
	driver.quit();
    }

    // Method to verify if the link is broken
    public static void verifyLink(String urlString) throws IOException, URISyntaxException {
	try {
	    // Use URI to handle URL creation properly
	    URI uri = new URI(urlString);
	    URL url = uri.toURL();
	    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
	    httpURLConnection.setConnectTimeout(3000); // Set a timeout
	    httpURLConnection.connect();
	    // Check the response code
	    if (httpURLConnection.getResponseCode() == 200) {
//		System.out.println(urlString + " - " + httpURLConnection.getResponseMessage());
	    } else {
		System.out.println(urlString + " - Broken link: " + httpURLConnection.getResponseMessage());
	    }
	} catch (Exception e) {
	    System.out.println(urlString + " - Error: " + e.getMessage());
	}
    }
}
