package Test;

import java.util.Scanner;

public class NonrepeatingChars {

    public static void main(String[] args) {
	try (Scanner cs = new Scanner(System.in)) {
	    String str;
	    System.out.println("Enter your String:");
	    str = cs.nextLine();

	    int i;
	    int arr[] = new int[256];
	    for (i = 0; i < str.length(); i++) {
		if (str.charAt(i) != ' ')
		    arr[str.charAt(i)]++;
	    }
	    char ch = ' ';
	    System.out.print("All Non-repeating character in a given string is:");
	    for (i = 0; i < str.length(); i++) {
		if (arr[str.charAt(i)] == 1) {
		    ch = str.charAt(i);
		    System.out.print(ch + " ");
		}
	    }
	    cs.close();
	}
    }

}
