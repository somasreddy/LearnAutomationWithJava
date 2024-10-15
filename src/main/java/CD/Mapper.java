package CD;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.ParseException;
import java.time.Duration;
import java.util.List;

public class Mapper {
    @Test
    public static void main(String[] args) throws ParseException, InterruptedException {


        ChromeOptions options = new ChromeOptions();

        options.addArguments("headless");

        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get(args[2]);

            driver.manage().window().maximize();

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            boolean InvalidCreds = false;

            try {
                LoginToJenkins(driver, args[0], args[1]);

                InvalidCreds = driver.findElement(By.xpath("//div[@class='alert alert-danger'][.='Invalid username or password']")).isDisplayed();
            } catch (NoSuchElementException nse) {
            }

            if (!InvalidCreds) {

                boolean JobsToBuildFound = false;

                try {

                    JobsToBuildFound = driver.findElement(By.xpath("//tbody/tr[@class=' job-status-red']/td/a/img[@class='icon-clock icon-md']")).isDisplayed();
                } catch (NoSuchElementException nse) {
                }

                if (JobsToBuildFound) {

                    List<WebElement> failedbuilds = driver.findElements(By.xpath("//tbody/tr[@class=' job-status-red']/td/a/img[@class='icon-clock icon-md']"));

                    for (int i = 0; i < failedbuilds.size(); i++) {

                        failedbuilds.get(i).click();

                        Thread.sleep(1500);

                        String Job = failedbuilds.get(i).findElement(By.xpath("../../..")).getAttribute("id");

                        System.out.println("Retriggering - " + Job);

                    }

                } else {
                    throw new Exception("Invalid Node Link Entered");
                }
            } else {

                throw new Exception("Invalid Credentials Passed");
            }
        } catch (Exception e) {

            e.printStackTrace();

            KillChromeDriver(driver);
        }

        System.out.println("Triggering Completed");

        KillChromeDriver(driver);

    }

    public static void KillChromeDriver(WebDriver driver) {

        try {

            driver.quit();

            System.out.println("\nKilling Chrome Driver");

            try {

                new ProcessBuilder("taskkill", "/f", "/im", "chromedriver.exe").start().waitFor();
                new ProcessBuilder("taskkill /f /im ruby.exe").start().waitFor();
                System.out.println("Chromedriver process killed successfully.");

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            System.exit(0);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static void LoginToJenkins(WebDriver driver, String UN, String PWD) {
        try {
            if (driver.findElement(By.xpath("//a[contains(text(),'Log in')]")).isDisplayed()) {

                driver.findElement(By.xpath("//a[contains(text(),'Log in')]")).click();
            }
        } catch (NoSuchElementException noSuchElementException) {
        }

        try {
            if (driver.findElement(By.xpath("//input[@id='j_username']")).isDisplayed()) {

                String userName = UN;

                driver.findElement(By.xpath("//input[@id='j_username']")).sendKeys(userName);

                String password = PWD;

                driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(password);

                driver.findElement(By.xpath("//div[@class='Checkbox-indicator']")).click();

                driver.findElement(By.xpath("//input[@name='Submit']")).click();
            }
        } catch (NoSuchElementException noSuchElementException) {
        }
    }

    /*
     * public static void main(final String[] args) {
     *
     * int numberOfSimultaneousExecutions = 100;
     *
     * java.util.concurrent.Executor executor =
     * java.util.concurrent.Executors.newFixedThreadPool(
     * numberOfSimultaneousExecutions);
     *
     * for (int i = 0; i < numberOfSimultaneousExecutions; i++) {
     *
     * executor.execute(new Runnable() {
     *
     * @Override public void run() { Mapper.main(args); } }); } }
     */
}
