package RQMLinks;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class GetJobsFromGit {

    public static void main(String[] args) throws InterruptedException, IOException {
	ChromeOptions options = new ChromeOptions();
	options.addArguments("headless");
	WebDriver driver = new ChromeDriver(options);
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	String gitLink;
	Scanner input = new Scanner(System.in);
	System.out.print("\nEnter the github Link :");
	gitLink = input.nextLine();
	String DWK = "https://github.cerner.com/EggPlant/IPDev-Millennium-ClinicianList/tree/master/IPDev-Millennium-ClinicianList.suite/Scripts/Workflows";
	String ICU = "https://github.cerner.com/EggPlant/IPDev-Millennium-ClinicalAssessment/tree/master/IPDev-Millennium-Clinical-Assessment.suite/Scripts/Workflow";
	String CMP = "https://github.cerner.com/EggPlant/IPDev-MPages-CareCompass/tree/master/IPDev-MPages-CareCompass.suite/Scripts/Workflows";
	String MRD = "https://github.cerner.com/EggPlant/IPDev-Millennium-MedicationAdministrationRecord/tree/master/IPDev-Millennium-Medication-Record.suite/Scripts/Workflows";
	String PAP_PD = "https://github.cerner.com/EggPlant/IPDev-Millennium-Windows-ProblemsAndDiagnosis/tree/master/IPDev-Millienuim-Windows-Problems-And-Diagnosis.suite/Scripts/WorkFlows";
	String PAP_PH = "https://github.cerner.com/EggPlant/IPDev-Millennium-ProcedureHistories/tree/master/IPDev-Millennium-ProcedureHistories.suite/Scripts/Workflows";
	String PAP_SH = "https://github.cerner.com/EggPlant/IPDev-Millennium-SocialHistory/tree/master/IPDev-Millennium-SocialHistory.suite/Scripts/Workflows";
	String PAP_FH = "https://github.cerner.com/EggPlant/IPDev-Millennium-FamilyHistory/tree/master/IPDev-Millennium-FamilyHistory.suite/Scripts/Workflows";
	String PAP_PMH = "https://github.cerner.com/EggPlant/IPDev-Millennium-PastMedical/tree/master/IPDev-Millennium-PastMedical.suite/Scripts/Workflows";
	String COL = "https://github.cerner.com/EggPlant/IPDEV-Millennium-PowerChartSpecimenCollection/tree/master/IPDev-Millennium-PowerChartSpecimenCollection.suite/Scripts/Workflows";
	String PHA = "https://github.cerner.com/EggPlant/IPDev-Millennium-PharmacyInpatient/tree/master/IPDev-Millennium-PharmacyInpatient.suite/Scripts/WorkFlows";
	String PHA1 = "https://github.cerner.com/EggPlant/IPDev-Millennium-PharmacyInpatient/tree/master/IPDev-Millennium-PharmacyInpatient.suite/Scripts/Workflows";
	String PHR = "https://github.cerner.com/EggPlant/IPDev-Millennium-PharmacyOutpatient/tree/master/IPDev-Millennium-PharmacyOutpatient.suite/Scripts/WorkFlow";
	String CIM = "https://github.cerner.com/EggPlant/IPDev-Millennium-SupplyChain/tree/master/IPDev-Millennium-SupplyChain.suite/Scripts/Horizontal%20Regression";
	String PAR = "https://github.cerner.com/EggPlant/IPDev-Millennium-NexusPharmacy/tree/master/IPDev-Millennium-NexusPharmacy.suite/Scripts";
	List<String> lst = new ArrayList<String>();
	/*
	 * lst.add(ICU); lst.add(DWK); lst.add(CMP); lst.add(MRD);
	 * lst.add(PAP_PD);lst.add(CMP); lst.add(PAP_PH); lst.add(PAP_SH);
	 * lst.add(PAP_FH); lst.add(PAP_PMH); lst.add(COL);lst.add(PHA);
	 * lst.add(PHA1);lst.add(PHR); lst.add(CIM); lst.add(PAR);
	 */

	lst.add(gitLink);
	try {
	    for (int i = 0; i < lst.size(); i++) {
		driver.navigate().to(lst.get(i));
		driver.manage().window().maximize();
		List<WebElement> GitLinks = driver.findElements(By.xpath("//div[@role='rowheader']/span/a"));
		ArrayList<String> arrList = new ArrayList<String>();
		for (int j = 0; j < GitLinks.size(); j++) {
		    String name = GitLinks.get(j).getText();
		    if (name.contains(".gitignore")) {
		    } else {
			String names[] = name.split(".script");
			arrList.add(names[0]);
		    }
		}
		for (int j = 0; j < arrList.size(); j++) {
		    System.out.println("\nTotal Scripts in Git : " + arrList.size() + "\n");
		    for (String scriptName : arrList) {
			System.out.println(scriptName);
		    }
		    arrList.clear();
		    System.out.println();
		    System.out.println(
			    "====================================================================================");
		}

	    }
	} catch (Exception e) {
	    Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");

	    Thread.sleep(2000L);

	    Runtime.getRuntime().exec("taskkill /f /im ruby.exe");

	    Thread.sleep(1000L);

	    System.exit(0);

	    driver.quit();
	}

	Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");

	Thread.sleep(2000L);

	Runtime.getRuntime().exec("taskkill /f /im ruby.exe");

	Thread.sleep(1000L);

	System.exit(0);

	driver.quit();
    }
}