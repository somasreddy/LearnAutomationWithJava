package learn;
class A2{
	void test(int a) {
		System.out.println("Iam from super class " +a+" "+this.getClass());
	}
	 
}
public class MethodOVL extends A2 {
	int test(String a) {
		System.out.println("Iam from subclass "+a+" "+this.getClass());
		    	
		return 0;
		    }
 public static void main(String[] args) {
    MethodOVL mvl= new MethodOVL();
	mvl.test("Somu");
	mvl.test(7);
}

}
