package learnSelenium;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTest extends GetDataFromExcel {

	@DataProvider
	public Object[][] getExcelData() throws FileNotFoundException, IOException {
		return getDataFromXL("Details");
	}

	@Test(dataProvider = "getExcelData")
	public void getDetails(String name,String mobile, String mailID) {
		System.out.println(name+" "+mobile+" "+mailID);
	}
}
