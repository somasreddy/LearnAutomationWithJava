package Test;

class OverloadTest {
	void add(int a, int b) {
		System.out.println("inside int argument method");
//		return a + b;
	}
}

public class TestOverload extends OverloadTest {
	void add(long a1, long b1) {
		System.out.println("inside long argument method");
//		return (int)(a1 + b1);
	}

	public static void main(String[] args) {

		TestOverload ol = new TestOverload();
		try {
			ol.add(20l, 30l);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
