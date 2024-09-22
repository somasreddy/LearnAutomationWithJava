package learn;

import java.util.Scanner;

public class AllCharCount {
    public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	System.out.println("Enter the String: ");
	String ipString = sc.nextLine();
	char[] c = ipString.toCharArray();
	System.out.println(c);
	for (int i = 0; i < ipString.length(); i++) {

	    int count = 0;
	    for (int j = 1; j < ipString.length(); j++) {
		if (ipString.charAt(i) == ipString.charAt(j)) {
		    count++;
		}
	    }
	    System.out.println("The Occurances of '" + ipString.charAt(i) + "' is: " + count);
	}

	sc.close();
    }
}
