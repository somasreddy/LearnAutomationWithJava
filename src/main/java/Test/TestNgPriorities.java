package Test;

import org.testng.annotations.Test;

public class TestNgPriorities {

	@Test
	public void pr() {
		System.out.println("inside no priority");
	}
	
	@Test(priority = -2)
	public void prn2() {
		System.out.println("inside priority -2 ");
	}

	@Test(priority = -1)
	public void prn1() {
		System.out.println("inside priority -1 ");
	}

	@Test(priority = 1)
	public void pr1() {
		System.out.println("inside priority 1");
	}

	@Test(priority = 0)
	public void ar0() {
		System.out.println("inside priority 0");
	}
	

}
