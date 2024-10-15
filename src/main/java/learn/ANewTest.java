package learn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ANewTest {
	String filePath = "./abc.txt";

	{
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		int[] arr = { 37, 89, 0, 0, 18, 0, 19 };

		moveZerosFront(arr);
		for (int num : arr) {
			System.out.print(num + " ");
		}
		System.out.println();
		sortIntArr(arr);
		for (int num : arr) {
			System.out.print(num + " ");
		}
		System.out.println();
		String[] words = { "Bangalore", "Adilabad", "Delhi", "Chennail" };
		sortArrayWords(words);
		for (String w : words) {
			System.out.print(w + " ");

		}
		System.out.println();
		System.out.println(revString("String to Reverse"));

	}

	static int[] sortIntArr(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] > arr[j]) {
					arr[i] = arr[i] + arr[j];
					arr[j] = arr[i] - arr[j];
					arr[i] = arr[i] - arr[j];
				}
			}
		}
		return arr;
	}

	static int[] moveZerosFront(int[] arr) {
		// Two pointer approach to move zeros to the front
		int n = arr.length;
		int nonZeroIndex = n - 1; // Index where the next non-zero element should go
		// Traverse the array from the end
		for (int i = n - 1; i >= 0; i--) {
			if (arr[i] != 0) {
				arr[nonZeroIndex--] = arr[i]; // Move non-zero element to the correct position
			}
		}
		// Fill the remaining positions with zeros
		while (nonZeroIndex >= 0) {
			arr[nonZeroIndex--] = 0;
		}
		return arr;
	}

	static String[] sortArrayWords(String[] str) {
		for (int i = 0; i < str.length; i++) {
			for (int j = i + 1; j < str.length; j++) {
				if ((int) (str[i].charAt(0)) > (int) (str[j].charAt(0))) {
					String temp = str[i];
					str[i] = str[j];
					str[j] = temp;
				}
			}
		}
		return str;
	}

	static String revString(String str) {

		String temp = "";
		for (int i = str.length() - 1; i >= 0; i--) {
			temp += str.charAt(i);
		}
		return temp;
	}
}
