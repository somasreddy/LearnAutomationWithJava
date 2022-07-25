package learn;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class ArrayListSort{
 public static void main(String[] args) {
	 List<Integer> lst=new ArrayList<Integer>();
	Scanner sc=new Scanner(System.in);
	System.out.print("Enter the total no. of elements in ArrayList :");
	int n=sc.nextInt();
	for(int i=1;i<=n;i++) {
	System.out.print("Enter Element "+ i +": ");
	for(int j=i;j<=i;j++) {
		lst.add(sc.nextInt());
	}
	}
	TreeSet<Integer> ts=new TreeSet<Integer>();
	ts.addAll(lst);
	System.out.println("Array List Elements from Set: "+ts);
	System.out.println("Array List Before Sorting: "+lst);
			for (int i = 0; i<lst.size(); i++) {
			    for (int j=lst.size()-1; j>i; j--) {
			        if (lst.get(i) > lst.get(j)) {
			            int tmp=lst.get(i);
			            lst.set(i, lst.get(j));
			            lst.set(j, tmp);
			        }
			    }
			}
	
	System.out.println("Array List After Sorting: "+lst);
	sc.close();
}
}




















/*class Array1{
	static int cou=0;
	Array1(){
		cou++;
	}
}
public class ArrayListSort extends Array1 {
	static int count=0;
	public ArrayListSort(){
		super();
		count++;
	}
	public static void main(String[] args) {
		
		ArrayListSort als=new ArrayListSort();
		System.out.println("Super Class Object="+cou+"\nSubClass Object="+count);
	}
	
}*/
