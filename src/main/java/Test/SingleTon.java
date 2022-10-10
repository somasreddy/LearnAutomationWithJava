package Test;

public class SingleTon {
private SingleTon() {
	
}
static SingleTon getSingleTon(SingleTon s) {
	if(s==null) {
		return new SingleTon();
	}
	else {
		return s;
	}
}
public static void main(String[] args) {
	SingleTon s= null;
	System.out.println(getSingleTon(s));
	SingleTon s1=null;
	System.out.println(getSingleTon(s1));
	
}
}
