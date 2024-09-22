package Test;

class Orange{
	Orange(String s){
		
	}
	
}
class Apple{
	
}

public class Equals {

	public static void main(String[] args) {
		Orange o1=new Orange("fruit");
		Orange o2=new Orange("fruit");
		Orange o3=new Orange(" ");
		Apple a1=new Apple();
		Apple a2=new Apple();
//		System.out.println(a1.equals(a2));
		System.out.println(o1.equals(o2));
		System.out.println(o1==o2);
		System.out.println(a1==a2);
		System.out.println(o3==null);
		
	}
}
