package learn;

import java.util.HashSet;
import java.util.Set;

public class Test {
String name;
Double salary;
	
Test(String name,Double salary){
	this.name=name;
	this.salary=salary;
	System.out.println("Employee Name is:"+this.name);
	System.out.println("Employee Salary is:"+this.salary);
}
	/*public void dolt(int m, int n) {
		int k;
		k=m;
		m=n;
		n=k;
		System.out.println(m);
		return;
	}*/
//	public static int count=0;
public static void main(String[] args) {
	Test t1=new Test("Syam",20000.00);
	Test t2=new Test("ram",30000.00);
	
	Set<String> s=new HashSet<String>();
	s.add("abc");
	s.add("128");
	s.add("qwer");
	
	System.out.println();
	
	/*for(int i=0;i<=150;i++) {
		System.out.print((char)i+"="+i+"  ");
	}
*/
	/*String s="I am in Bangalore";
	
	char[] ch=s.toLowerCase().toCharArray();
	for(int i=0;i<ch.length;i++) {
	char c=s.charAt(i);
		if((int)c==97) {
		count++;
		}
	}

	System.out.println("Number of occurences of the Letter is :" +count);*/
	
	
	
	/*	int i=1,j=10;
//	Test t=new Test();
do {
	if(i++>--j) {
		continue;
	}
}while(i<5);
System.out.println("i="+ i +"and j ="+j);
	try {
		
	}
    
	
	catch(ArithmeticException e) {
		
	}
    catch(Exception e) {
		
	}*/
    
	
	
	
}
}

