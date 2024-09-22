package learn;

public class Constructor extends String_Initialise {
    public Constructor() {
	System.out.println("Derived Class Constructor");
	b = "String from Derived Class";
    }

    public static void main(String... arg) {
	Constructor obj = new Constructor();
	System.out.println("the strings initialised in the constructors of Base and Derived classes are :");
	String s = obj.a;
	// System.out.println(obj.a +" and "+obj.b);
	System.out.println(s);
    }
}

class String_Initialise {
    String a, b;

    public String_Initialise() {
	System.out.println("Base Class Constructor");
	a = "String from Base Class";
    }
}
