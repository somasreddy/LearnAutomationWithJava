package learn;

import java.util.Scanner;

public class Anagram {
    public static String removeSpaces(String str) {
	String result = "";
	for (int i = 0; i < str.length(); i++) {
	    char ch = str.charAt(i);
	    if (ch != ' ') { // Only include non-space characters
		result += ch;
	    }
	}
	return result;
    }

    public static boolean checkAnagrams(String str1, String str2) {
	// Remove spaces from strings
	str1 = removeSpaces(str1);
	str2 = removeSpaces(str2);

	// If lengths are different, they cannot be anagrams
	if (str1.length() != str2.length()) {
	    return false;
	}

	// Count characters in both strings
	int[] charCount1 = new int[26]; // For 'a' to 'z'
	int[] charCount2 = new int[26];

	// Count occurrences of each character in the first string
	for (int i = 0; i < str1.length(); i++) {
	    char ch1 = str1.charAt(i);

	    if (ch1 >= 'a' && ch1 <= 'z') {
		// Only count alphabet characters
		int index = ch1 - 'a';
		charCount1[index]++;
	    } else if (ch1 >= 'A' && ch1 <= 'Z') {
		int index = ch1 - 'A';
		charCount1[index]++;
	    }
	}

	// Count occurrences of each character in the second string
	for (int i = 0; i < str2.length(); i++) {
	    char ch2 = str2.charAt(i);

	    if (ch2 >= 'a' && ch2 <= 'z') {
		int index = ch2 - 'a';
		charCount2[index]++;
	    } else if (ch2 >= 'A' && ch2 <= 'Z') {
		int index = ch2 - 'A';
		charCount2[index]++;
	    }
	}

	// Compare the character counts of both strings
	for (int i = 0; i < 26; i++) {
	    if (charCount1[i] != charCount2[i]) {
		return false; // If any character count doesn't match, return false
	    }
	}

	return true; // If all character counts match, return true
    }

//	// Helper method to remove spaces from a string
//	public static String removeSpaces(String str) {
//	    return str.replaceAll("\\s+", ""); // Replaces all white spaces with no space
//	}

    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	System.out.println("Enter First String:");
	String str1 = scanner.next();
	System.out.println("Enter Second String:");
	String str2 = scanner.next();
	scanner.close();
	System.out.println(checkAnagrams(str1, str2));

    }
}
