package learn;

public class NullPointException {
	
	
	
	public static void main(String[] args) {
		NullPointException t=null;
		t.foo("Hi");
		
	}

	/*private static NullPointException initT() {
		return null;
	}*/

	public void foo(String s) {
		System.out.println(s.toLowerCase());
	}
}