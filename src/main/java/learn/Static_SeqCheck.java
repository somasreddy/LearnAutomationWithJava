package learn;

/**
 * @author VS065203
 *
 */
class AB{
	AB(){
		System.out.println("Inside Constructor of AB");
	}
	static {
		System.out.println("Inside static block of AB");
	}
	{
		System.out.println("Inside Non Static block of AB");
	}
}
public class Static_SeqCheck {
	static {
		System.out.println("Inside static block of Check");
	}
	Static_SeqCheck(int a){
		System.out.println("Inside Constructor of Check");
	}
	{
		System.out.println("Inside Non Static block of Check");
	}
	public static void main(String[] args) {
		System.out.println("Main Starts");
		AB a=new AB();
		System.out.println(a);
		new Static_SeqCheck(1);
		System.out.println("Main Ends");
		

	}
}
