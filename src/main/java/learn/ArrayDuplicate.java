package learn;

import java.util.Scanner;

public class ArrayDuplicate {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// Input: Size of the array
		int size = sc.nextInt();

		// Input: Elements of the array
		int[] arr = new int[size];
		for (int i = 0; i < size; i++) {
			arr[i] = sc.nextInt();
		}

		System.out.println("Duplicates in the array:");

		// Find and print all duplicate elements
		boolean hasDuplicates = false; // To track if any duplicates were found

		for (int i = 0; i < size; i++) {
			// Skip the element if it has already been marked as processed
			if (arr[i] == -1)
				continue;

			for (int j = i + 1; j < size; j++) {
				if (arr[i] == arr[j]) {
					// Print the duplicate and mark it to avoid repetition
					System.out.println(arr[i]);
					hasDuplicates = true;

					// Mark the duplicate element in the inner loop
					arr[j] = -1;
				}
			}
		}

		// If no duplicates are found, print a message
		if (!hasDuplicates) {
			System.out.println("No duplicates found.");
		}

		sc.close();
	}
}
