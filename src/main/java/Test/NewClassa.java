package Test;

public class NewClassa {

	public static void main(String[] args) {

		int n = 5;

		int l = n;
		for (int i = 1; i <= n; i++) {
//	        for(int j=n;j>=i;j--)
//	        {
//	           System.out.print(n-j+1);
//	        }
//	        if(i>1)
//	        {
//	        for(int p=1;p<=2*(i-1);p++)
//	        {
//	        	System.out.print("*");
//	        }
//	        }
			for (int j = l; j >= 1; j--) {
				System.out.print(j);

			}
			l = l - 1;
			System.out.println();
		}
	}
}
/*
 * String a = "My name is xyz is my identity"; Map<String, Integer> map = new
 * HashMap<>();//create a Map object String[] b = a.split(" ");// [] Integer
 * counter=null;//initalize counter for(int i=0;i<b.length;i++) { //loop the
 * whole array counter=map.get(b[i]);//get element from map
 * System.out.println(map.get(b[i])); if(map.get(b[i]) == null) { //check if it
 * already exists map.put(b[i], 1);//not exist, add with counter as 1
 * System.out.println(map.get(b[i])); } else { counter++;//if already eists,
 * increment the counter & put to Map map.put(b[i], counter);
 * System.out.println(map); } } System.out.println(map);
 */

//		int n=4;
//		for(int k=4;k<=4;k--) {
//		
//			
//		for(int i=1;i<k;i++) {
//			System.out.print(i);
//		}
//		if(k<n) {
//			for(int i=1;i<k-1;i++)
//			System.out.print("*");
//		
//		}
//		for(int j=n-1;j>0;j--) {
//			System.out.print(j);
//		}
//		
//		System.out.println();
//		}

//
//
//  12344321
//  123**321
//  12****21 
//  1******1 