package learnSelenium;

import org.testng.annotations.Test;

public class IntoToTestNG {

	/*
	 * @Test(priority = 0,dependsOnMethods = {"bTest"}) public void zTest() {
	 * System.out.println("zTest Method"); }
	 * 
	 * @Test(priority = 1) public void aTest() { System.out.println("aTest Method");
	 * }
	 * 
	 * @Test(priority = 3) public void bTest() { System.out.println("bTest Method");
	 * }
	 */
	
	  @Test
	    public void setup()
	    {
	        System.out.println("Setup");
	    }
	    @Test(priority=1)
	    public void gotopage()
	    {
	        System.out.println("gottopage");
	    }
	    @Test(priority=2, dependsOnMethods="gotopage")
	    public void verifytitle()
	    {
	        System.out.println("verifytitle");
	    }
	    @Test(dependsOnMethods="login")
	    public void verifyimage()
	    {
	        System.out.println("verifyimage");
	    }

	    @Test(dependsOnMethods="verifyhomepage", priority =10)
	    public void login()
	    {
	        System.out.println("login");
	    }
	    @Test(priority=7)
	    public void verifyhomepage()
	    {
	        System.out.println("verifyhomepage");
	    }
	
}
