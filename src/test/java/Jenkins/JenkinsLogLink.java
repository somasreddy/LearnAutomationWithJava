package Jenkins;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JenkinsLogLink {
	static String Envi2 = "_S18VX";
	static String Envi1 = "EP_";
	static String Jenkins_Link = "http://ipta02.ip.devcerner.net:8080/computer/S18VX_EP-WA_VB-IVIEW_Node1/";
	static String downloadFilepath = "C:\\Users\\VS065203\\Downloads\\MRD_Passed Plans";
	static String Start_Date = "2021-01-02";
	static String Environment = "EP_";
	static String ExcelName = "Downloaded_Logs_Data_";
	static Workbook wb;
	static Sheet sheet;
	static FileOutputStream fileOut;
	static List<String> jobs;
	static String SolutionName;

	public static void main(String[] args)
			throws InterruptedException, ParseException, InvalidFormatException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.prompt_for_download", "false");
		chromePrefs.put("download.default_directory", downloadFilepath);
		chromePrefs.put("browser.set_download_behavior",
				"{ behavior: 'allow' , downloadPath: '" + downloadFilepath + "'}");
		chromePrefs.put("plugins.plugins_disabled", new String[] { "Adobe Flash Player", "Chrome PDF Viewer" });
		options.setExperimentalOption("prefs", chromePrefs);
		options.addArguments("--disable-gpu");
		options.setExperimentalOption("prefs", chromePrefs);
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		JenkinsLogLink jps = new JenkinsLogLink();
		WebDriver driver = new ChromeDriver(options);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		driver.get(Jenkins_Link);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//tr[1]/th[4]/a")).click();
		driver.findElement(By.xpath("//th[4]/a/span")).isDisplayed();
		List<WebElement> lst = driver.findElements(By.xpath("//tbody/tr[@class=' job-status-blue']/td[4]"));
		jobs = new ArrayList<String>();
		Map<String, String> mapOfJobs = new HashMap<String, String>();
		;
		int count = 1;
		for (int i = 0; i < lst.size(); i++) {
			String Val = lst.get(i).getText();
			String Date = lst.get(i).getAttribute("data");
			String[] DateVal = Date.split("T");
			Date date1 = sdf.parse(DateVal[0]);
			Date date2 = sdf.parse(Start_Date);
			if (date1.compareTo(date2) > 0) {
				String Job = lst.get(i).findElement(By.xpath("..")).getAttribute("id");
				String[] Jobname1 = Job.split(Envi1 + " -");
				String[] Jobname = Jobname1[1].split(Envi2);
				try {
					if (Jobname[1] == null) {
						System.out.println(Jobname[0] + "is not a Valid TestPlan Job");
					} else {
						if (Jobname.length == 1) {
							continue;
						} else {
							jobs.add(Jobname[1]);
							SolutionName = First4Chars(Jobname[1]);
							String mainWindow = driver.getWindowHandle();
							WebElement joblink = lst.get(i).findElement(By.xpath("../td[3]/a"));
							Actions action = new Actions(driver);
							action.keyDown(Keys.SHIFT).click(joblink).keyUp(Keys.SHIFT).build().perform();
							Thread.sleep(3000);
							Set<String> set = driver.getWindowHandles();
							Iterator<String> itr = set.iterator();
							String BuildName = null;
							String url = null;
							while (itr.hasNext()) {
								String childWindow = itr.next();
								if (!mainWindow.equals(childWindow)) {
									driver.switchTo().window(childWindow);
									if (Environment == "EggPlant") {
										driver.findElement(By.xpath("//td/a[.='Last Successful Artifacts']")).click();
//	 		        		BuildName=driver.findElement(By.xpath("//a[contains(text(),'"+Environment+" -')]")).getText();
										driver.findElement(
												By.xpath("//a[@href='./*zip*/archive.zip']/../../../../tr/td/a"))
												.click();
										driver.findElement(By.xpath(
												"//td/img[@class='icon-folder icon-sm']/../following-sibling::td"))
												.click();
										driver.findElement(
												By.xpath("//a[.='RunHistory.csv']/../../preceding-sibling::tr/td/a"))
												.click();
										BuildName = driver.findElement(By.xpath("//a[@href='./']")).getText();
										driver.findElement(By.xpath("//div/a/img[@class='icon-package icon-sm']/.."))
												.click();
										File file = new File(downloadFilepath + "\\" + BuildName + ".zip");
										while (!file.exists()) {
											Thread.sleep(1000);
										}
										driver.close();
										Path path = Paths.get(downloadFilepath + "\\" + BuildName + ".zip");
										if (Files.exists(path)) {
											File newfile = jps.getTheNewestFile(downloadFilepath, "zip");
											newfile.renameTo(new File(downloadFilepath + "\\" + Jobname[1] + ".zip"));
										}
										System.out.println(count + ". " + Jobname[1]);
										count++;
									} else {

										driver.findElement(By.xpath("//td/a[.='Last Successful Artifacts']")).click();
										BuildName = driver
												.findElement(By.xpath(
														"//a[contains(text(),'Touchstone " + Environment + " -')]"))
												.getText();
										String[] currentJobname = BuildName.split(Environment + " -");
										try {
											if (driver.findElement(By.linkText("Touchstone Results"))
													.isDisplayed() == true) {
												url = driver.findElement(By.linkText("Touchstone Results"))
														.getAttribute("href");
												jps.createFile(downloadFilepath + "\\", currentJobname[1], url);
												mapOfJobs.put(url, currentJobname[1]);
												FileOutputStream fos = new FileOutputStream("sample.zip");
												ZipOutputStream zipOS = new ZipOutputStream(fos);
												writeToZipFile(downloadFilepath + "\\" + currentJobname[1] + ".txt",
														downloadFilepath + "\\" + currentJobname[1] + ".zip");
												driver.close();
											}
										} catch (Exception e) {
											System.out.println(" for " + currentJobname[1]);
											mapOfJobs.put("No TouchStone Result Link Found", currentJobname[1]);
											driver.close();
										}
									}
								}
							}
							driver.switchTo().window(mainWindow);
						}
					}
				} catch (Exception e) {
				}
			}
		}

		System.out.println("Total Plans Passed from the last Execution after " + Start_Date + " are:" + jobs.size());
		if (Environment.equalsIgnoreCase("EggPlant")) {
			String[] columns = { "Test Job Name" };
			createExcel(ExcelName + SolutionName, ExcelName + SolutionName, columns);
			for (int m = 0; m < jobs.size(); m++) {
				writeToExcelFile(ExcelName + SolutionName, jobs.get(m).toString(), m + 1);
			}
		} else {
			String[] columns = { "Test Job Name", "Result Link" };
			createExcel(ExcelName + SolutionName, ExcelName + SolutionName, columns);
			for (int n = 0; n < jobs.size(); n++) {
				writeToExcelFile(ExcelName + SolutionName, jobs.get(n).toString(), n + 1, 0);
				writeToExcelFile(ExcelName + SolutionName, getKeyFromValue(mapOfJobs, jobs.get(n).toString()), n + 1,
						1);
			}
		}
		fileOut.close();
		driver.close();
	}

	public static void createExcel(String fileName, String sheetName, String[] columns) throws IOException {
		Workbook wb = new XSSFWorkbook();
		CreationHelper createHelper = wb.getCreationHelper();
		sheet = wb.createSheet(sheetName);
		Font headerFont = wb.createFont();
//      headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.RED.getIndex());
		CellStyle headerCellStyle = wb.createCellStyle();
		headerCellStyle.setFont(headerFont);
		// Create a Row
		Row headerRow = sheet.createRow(0);
		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
			cell.setCellStyle(headerCellStyle);
		}
		fileOut = new FileOutputStream(new File(downloadFilepath + "\\" + fileName + ".xlsx"));
		wb.write(fileOut);
	}

	public static void writeToExcelFile(String fileName, Object JobName, int row, int col) throws IOException {
		try {
			FileInputStream file = new FileInputStream(new File(downloadFilepath + "\\" + fileName + ".xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet(fileName)/* getSheetAt(0) */;
			Cell cell = null;
			// Retrieve the row and check for null
			XSSFRow sheetrow = sheet.getRow(row);
			if (sheetrow == null) {
				sheetrow = sheet.createRow(row);
			}
			// Update the value of cell
			cell = sheetrow.getCell(col);
			if (cell == null) {
				cell = sheetrow.createCell(col);
			}
			cell.setCellValue(JobName.toString());
			file.close();
			FileOutputStream outFile = new FileOutputStream(new File(downloadFilepath + "\\" + fileName + ".xlsx"));
			workbook.write(outFile);
			outFile.close();
//            autoSizeColumns(workbook);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeToExcelFile(String fileName, String JobName, int row) throws IOException {
		try {
			FileInputStream file = new FileInputStream(new File(downloadFilepath + "\\" + fileName + ".xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet(fileName)/* getSheetAt(0) */;
			Cell cell = null;
			// Retrieve the row and check for null
			XSSFRow sheetrow = sheet.getRow(row);
			if (sheetrow == null) {
				sheetrow = sheet.createRow(row);
			}
			// Update the value of cell
			cell = sheetrow.getCell(0);
			if (cell == null) {
				cell = sheetrow.createCell(0);
			}
			cell.setCellValue(JobName);
			file.close();
			FileOutputStream outFile = new FileOutputStream(new File(downloadFilepath + "\\" + fileName + ".xlsx"));
			workbook.write(outFile);
			outFile.close();
//            autoSizeColumns(workbook);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeToZipFile(String path, String zipFile) throws FileNotFoundException, IOException {

		try {
			byte[] buffer = new byte[1024];
			FileOutputStream fos = new FileOutputStream(zipFile);
			ZipOutputStream zos = new ZipOutputStream(fos);
			File srcFile = new File(path);
			FileInputStream fis = new FileInputStream(srcFile);
			zos.putNextEntry(new ZipEntry(srcFile.getName()));
			int length;
			while ((length = fis.read(buffer)) > 0) {
				zos.write(buffer, 0, length);
			}
			zos.closeEntry();
			fis.close();
			zos.close();
		} catch (IOException ioe) {
			System.out.println("Error creating zip file" + ioe);
		}
	}

	public File getTheNewestFile(String filePath, String ext) {
		File theNewestFile = null;
		File dir = new File(filePath);
		FileFilter fileFilter = new WildcardFileFilter("*." + ext);
		File[] files = dir.listFiles(fileFilter);
		if (files.length > 0) {
			Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
			theNewestFile = files[0];
		}
		return theNewestFile;
	}

	public void createFile(String filePath, String fileName, String TouchstoneResultLink) throws IOException {
		File file = new File(filePath + fileName + ".txt");
		// Create the file
		if (file.createNewFile()) {
			System.out.println(fileName + " - " + TouchstoneResultLink);
		} else {
			System.out.println(fileName + " -" + TouchstoneResultLink + " not created");
		}
		// Write Content
		FileWriter writer = new FileWriter(file);
		writer.write(TouchstoneResultLink);
		writer.close();
	}

	public static Object getKeyFromValue(Map<String, String> hm, Object value) {
		for (Object o : hm.keySet()) {
			if (hm.get(o).equals(value)) {
				return o;
			}
		}
		return "Unable To Find related link for " + value + " in the Downloaded list";
	}

	public static String First4Chars(String input) {
		String firstFourChars;
		if (input.length() > 4) {
			firstFourChars = input.substring(0, 4);
		} else {
			firstFourChars = input;
		}
		return firstFourChars.trim();
	}

	public static void autoSizeColumns(Workbook workbook) {
		int numberOfSheets = workbook.getNumberOfSheets();
		for (int i = 0; i < numberOfSheets; i++) {
			Sheet sheet = workbook.getSheetAt(i);
			if (sheet.getPhysicalNumberOfRows() > 0) {
				Row row = sheet.getRow(sheet.getFirstRowNum());
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					int columnIndex = cell.getColumnIndex();
					sheet.autoSizeColumn(columnIndex);
				}
			}
		}
	}
}
