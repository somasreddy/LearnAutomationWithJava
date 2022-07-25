package learn;

import java.util.HashMap;
import java.util.Scanner;

public class Count {
  public static void main(String[] args) {
	 
	Scanner s=new Scanner(System.in);
	System.out.println("Enter the String:");
	String str=s.nextLine().toLowerCase();
	System.out.println("Enter the charcter to count occurances");
	String str1=s.nextLine().toLowerCase();
	char c=str1.charAt(0);
	 int count = 0;
	for(int i=0;i<str.length();i++) {
		if(str.charAt(i)==c) 
			count++;
	}
	if(count>0) {
		System.out.println("The number of Occurances of '"+c+"' in the given String is: "+count);
	}
	else {
		System.out.println("The entered character is not present in the given String");
	}
	
	characterCount("I Love my country");
	
	/*String[] s1=str.split(" ");
	System.out.println("The Number of words in given string is : "+s1.length);*/

	//	System.out.println(count("Rajtharun Garu",'a'));
	
	
	s.close();  
	}
  public static int count(String s, char c)
  {
      int res = 0;

      for (int i=0; i<s.length(); i++)
      {
          // checking character in string
          if (s.charAt(i) == c)
          res++;
      } 
      System.out.print("The number of Occurances of '"+c+"' in the given string is: ");
      return res;
  }
  static void characterCount(String inputString)
  {
      //Creating a HashMap containing char as a key and occurrences as  a value

      HashMap<Character, Integer> charCountMap = new HashMap<Character, Integer>();

      //Converting given string to char array

      char[] strArray = inputString.toCharArray();

      //checking each char of strArray

      for (char c : strArray)
      {
          if(charCountMap.containsKey(c))
          {
              //If char is present in charCountMap, incrementing it's count by 1

              charCountMap.put(c, charCountMap.get(c)+1);
          }
          else
          {
              //If char is not present in charCountMap,
              //putting this char to charCountMap with 1 as it's value

              charCountMap.put(c, 1);
          }
      }

      //Printing the charCountMap

      System.out.println(charCountMap);
  }
}
