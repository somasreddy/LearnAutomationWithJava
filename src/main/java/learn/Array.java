package learn;

import java.util.Arrays;

public class Array {
	//	static int a[] = { 12, 15, 21, 19, 30, 10, 112, 136 };

	public static void main(String[] args) {

		/*
		 * for (int i = 0; i < a.length; i++) { 
		 * if (a[i] % 2 != 0) {
		 * System.out.println(a[i]); 
		 * } 
		 * }
		 */
		int arr[] = { 0, 1, 1, 0, 1, 0 };
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] + arr[j] == 0) {
					arr[i + 1] = arr[i + 1] + arr[j];
					arr[j] = arr[i + 1] - arr[j];
					arr[i + 1] = arr[i + 1] - arr[j];
				}
			}
		}
		System.out.println(Arrays.toString(arr));
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}
}
