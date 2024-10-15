package learn;

import java.util.HashMap;
import java.util.Scanner;

public class AllCharCount {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the String: ");

		String ipString = sc.nextLine();

		char[] c = ipString.toCharArray();

		int size = c.length;

		boolean[] counted = new boolean[size]; // Array to track if a character has already been counted

		for (int i = 0; i < size; i++) {
			if (!counted[i]) { // Check if the character is already counted
				int count = 1; // Start counting the current character
				for (int j = i + 1; j < size; j++) {
					if (c[i] == c[j]) {
						count++;
						counted[j] = true; // Mark the character as counted
					}
				}
				System.out.println("The Occurrence of '" + c[i] + "' is: " + count);
			}
		}

		System.out.println("=====Using HashMap========");

		HashMap<Character, Integer> hm = new HashMap<Character, Integer>();

		for (int i = 0; i < size; i++) {
			int count = 1;
			if (hm.containsKey(c[i])) {
				hm.put(c[i], hm.get(c[i]) + 1);
			} else {
				hm.put(c[i], count);
			}
		}

		System.out.println(hm);

		sc.close();
	}
}
