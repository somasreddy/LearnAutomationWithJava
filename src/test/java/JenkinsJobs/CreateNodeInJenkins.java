package JenkinsJobs;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class CreateNodeInJenkins {
	static String sheetName = "Sheet1";

	static String filePath = "C:\\Users\\VS065203\\Downloads\\NodeLink.xlsx";

	static String username = null;

	static String password = null;

	static Workbook wb = null;

	static Scanner input = new Scanner(System.in);

	static String JenkinsLink = "http://ipta02.ip.devcerner.net:8080/script";

	public static void main(String[] args) throws EncryptedDocumentException, IOException {

		if (filePath == null) {
			System.out.print("Enter the Excel Path : ");

			filePath = input.nextLine();

			System.out.println();
		}
		if (sheetName == null) {
			System.out.print("Enter the SheetName : ");

			sheetName = input.nextLine();

			System.out.println();
		}

		if (JenkinsLink == null) {
			System.out.print("Enter the JenkinsLink : ");

			sheetName = input.nextLine();

			System.out.println();
		}

		FileInputStream inputFile = new FileInputStream(filePath);

		wb = WorkbookFactory.create(inputFile);

		int LastRow = wb.getSheet(sheetName).getLastRowNum();

		System.out.print("Total Number of records in excel : " + LastRow + "\n\n");

		/*
		 * WebDriverManager.chromedriver().setup();
		 * 
		 * WebDriver driver = new ChromeDriver();
		 * 
		 * String currentWindow = driver.getWindowHandle();
		 * 
		 * driver.switchTo().window(currentWindow);
		 * 
		 * driver.manage().window().maximize(); driver.get(JenkinsLink);
		 * 
		 * LoginToJenkins(driver,input);
		 * 
		 * WebDriverWait wait = new WebDriverWait(driver, 10000);
		 * 
		 * driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
		 */

		try {
			for (int i = 1; i <= LastRow; i++) {

				String NodeName = GetNodeNameFromExcel(wb, sheetName, i);
				Thread.sleep(2000);
				String Script = "import jenkins.model.*\r\n" + "import hudson.model.*\r\n"
						+ "import jenkins.model.*\r\n" + "import hudson.model.*\r\n" + "import hudson.slaves.*\r\n"
						+ "import hudson.slaves.EnvironmentVariablesNodeProperty.Entry\r\n" + "\r\n"
						+ "List<Entry> env = new ArrayList<Entry>();\r\n"
						+ "env.add(new Entry(\"AssociateID\",\"MP023168\"))\r\n"
						+ "env.add(new Entry(\"VDIP\",\"\"))\r\n" + "env.add(new Entry(\"user\",\".\\\\cerner\"))\r\n"
						+ "env.add(new Entry(\"Password\",\"cerner\"))\r\n"
						+ "EnvironmentVariablesNodeProperty envPro = new EnvironmentVariablesNodeProperty(env);\r\n"
						+ "  \r\n" + "list = new LinkedList()\r\n" + "list.add(envPro)\r\n" + "\r\n"
						+ "Jenkins.instance.addNode(new DumbSlave(\"" + NodeName + "\", \"" + NodeName
						+ "\", \"C:\\\\Jenkins\", \"1\", Node.Mode.NORMAL, \"" + NodeName
						+ "\", new JNLPLauncher(), new RetentionStrategy.Always(), list))";
				/*
				 * Thread.sleep(3000);
				 * 
				 * WebElement scriptConsole=driver.findElement(By.xpath(
				 * "//*[@id=\"main-panel\"]/form/div[1]/div[3]"));
				 * 
				 * Thread.sleep(3000); scriptConsole.sendKeys(Script);
				 * 
				 * driver.findElement(By.xpath("//span/button[.='Run']")).click();
				 * 
				 * WebElement CreatedNode = null; try { CreatedNode=driver.findElement(By.xpath(
				 * "//*[@id=\"executors\"]/div[2]/table/tbody/tr[160]/th/a[contains(@href,'"+
				 * NodeName+"')]")); }catch(Exception e){} if (CreatedNode.isDisplayed()){
				 * System.out.print(NodeName+"-- Created");
				 * System.out.println(CreatedNode.getAttribute("href")); } else {
				 * System.out.print(NodeName+"-- is Not Created"); }
				 */
				 System.out.print("\n=========================================================================================\n");
				System.out.println(Script);
			}

			input.close();

		} catch (Exception e) {
//	 	    	driver.quit();
			KillChromeDriver();
			e.printStackTrace();

		}
//	 	   driver.quit();
		KillChromeDriver();

	}

	public static void KillChromeDriver() {
		try {
			System.out.println("Kill Chrome Driver");

			Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");

			Thread.sleep(2000L);

			Runtime.getRuntime().exec("taskkill /f /im ruby.exe");

			Thread.sleep(1000L);

			System.exit(0);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public static void LoginToJenkins(WebDriver driver, Scanner in) {
		try {
			if (driver.findElement(By.xpath("//a[contains(text(),'Log in')]")).isDisplayed()) {

				driver.findElement(By.xpath("//a[contains(text(),'Log in')]")).click();
			}
		} catch (NoSuchElementException noSuchElementException) {
		}
		try {
			if (driver.findElement(By.xpath("//input[@id='j_username']")).isDisplayed()) {

				System.out.println("Please Enter the UserName : ");

				String userName = in.nextLine();

				driver.findElement(By.xpath("//input[@id='j_username']")).sendKeys(new CharSequence[] { userName });

				System.out.println("Please Enter the Password : ");

				String password = in.nextLine();

				driver.findElement(By.xpath("//input[@placeholder='Password']"))
						.sendKeys(new CharSequence[] { password });

				driver.findElement(By.xpath("//div[@class='Checkbox-indicator']")).click();

				driver.findElement(By.xpath("//input[@name='Submit']")).click();
			}
		} catch (NoSuchElementException noSuchElementException) {
		}
	}

	public static String GetNodeNameFromExcel(Workbook wb, String Sheet, int Row) {
		return wb.getSheet(sheetName).getRow(Row).getCell(0).toString();
	}
}
