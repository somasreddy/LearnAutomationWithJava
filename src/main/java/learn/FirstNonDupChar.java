package learn;

import java.util.Scanner;

public class FirstNonDupChar {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        // Read the input string
        String input = sc.nextLine();

        // Convert the input to uppercase (optional: case-insensitive)
        // input = input.toUpperCase();

        // Traverse the string to find the first non-repeated character
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            // Ignore spaces
            if (ch == ' ') {
                continue;
            }

            // Check if the character is repeated in the string
            int count = 0;
            for (int j = 0; j < input.length(); j++) {
                if (ch == input.charAt(j)) {
                    count++;
                }
            }

            // If character is non-repeated, print it and exit
            if (count == 1) {
                System.out.println(ch);
                return;
            }
        }

        // If no non-repeated character is found
        // System.out.println("No non-repeated character found.");
        sc.close();
	}
}
