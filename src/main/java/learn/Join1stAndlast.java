package learn;

import java.util.Scanner;

public class Join1stAndlast {
    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	String str = scanner.next();
	scanner.close();
	// To Join First and Last Char
	System.out.println(str.charAt(0) + "" + (str.charAt(str.toCharArray().length - 1)));

//	#ToCHeck if the given string is AlphaNumeric
	// Variables to track presence of letters and digits
	boolean hasLetter = false;
	boolean hasDigit = false;

	// Loop through each character to check
	for (char ch : str.toCharArray()) {
	    if (Character.isLetter(ch)) {
		hasLetter = true;
	    }
	    if (Character.isDigit(ch)) {
		hasDigit = true;
	    }
	    // Break early if both conditions are met
	    if (hasLetter && hasDigit) {
		break;
	    }
	}
	System.out.println(hasLetter && hasDigit == true ? "true" : "false");
	
    }
}
