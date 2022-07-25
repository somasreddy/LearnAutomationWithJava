package learn;

import java.util.HashMap;
import java.util.Scanner;

public class NumberToWord {
	static String tons[]= {"Hundred","Thousand","Lakhs","Crores"};
static String tens[]= {" ","ten","Twenty","Thirty","Fourty","Fifty","Sixty","Seventy","Eighty","Ninety"};
static String nums[]= {" ","one","two","three","four","five","six","seven","eight","nine"," ",
		"eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","ninteen"};
  
static String numToWord(int x) {
	 if(x<=0||x>=100) 
	  {
		return "Invalid input";
	  } if(x%10==0) 
	  {
		  x/=10;
		  return tens[x];
	  } else if(x<20)
		 {
			return nums[x];
		 }else 
		 {
			  String lastWord=nums[x%10];
			  int firstLetter=x-(x%10);
			  String firstWord=tens[firstLetter/10];
			  return firstWord+lastWord;
		 }
   }
public static void main(String[] args) {
	/*Scanner sc= new Scanner(System.in);
	  System.out.print("Enter Any Number between 1-99: ");
      System.out.println(numToWord(sc.nextInt()));
      sc.close();*/
  HashMap<Integer,String> hm=new  HashMap<Integer, String>();
  hm.put(1,"Janauary");
  hm.put(2,"February");
  hm.put(3,"March");
  hm.put(4,"April");
  hm.put(5,"May");
  hm.put(6, "June");
  hm.put(7, "July");
  hm.put(8, "August");
  hm.put(9, "September");
  hm.put(10, "October");
  hm.put(11, "November");
  hm.put(12, "December");
System.out.println(numToWord(4)+"th "+hm.get(6));
System.out.println(numToWord(7)+"th "+hm.get(7));
System.out.println(numToWord(8)+"th "+hm.get(10));
}
}
