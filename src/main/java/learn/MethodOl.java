package learn;
	class Base {

		public int f(int i){

		System.out.print("f(int): ");

		return i+3;

		}

		}

		class Derived extends Base{

		public double f(double i) {

		System.out.print("f(double) : ");

		return i + 3.3;

		}

		}

		public class MethodOl {

		public static void main(String args[]) {

		Derived obj = new Derived();

		System.out.println(obj.f(7));

		System.out.println(obj.f(7.7));
		

		}

		}
		
		
