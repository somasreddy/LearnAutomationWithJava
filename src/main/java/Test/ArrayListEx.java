package Test;

import java.util.ArrayList;

public class ArrayListEx {
	static int maxValue(int[] arr) {
		int maxVal = arr[0];
		int secMaxVal = arr[0];
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > maxVal) {
				secMaxVal = maxVal;
				maxVal = arr[i];
			}
		}
		System.out.println("Max Value is : " + maxVal);
		System.out.println("Second Max Value is : " + secMaxVal);
		return maxVal;
	}

	static int minValue(int[] arr) {
		int maxVal = arr[0];
		int secMaxVal = arr[0];
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < maxVal) {
				secMaxVal = maxVal;
				maxVal = arr[i];
			}
		}
		System.out.println("Min Value is : " + maxVal);
		System.out.println("Second Min Value is : " + secMaxVal);
		return maxVal;
	}

	public static void main(String[] args) {
		int[] arr = { 12, 13, 19, 100, 120, 100, 10, 5 };

		System.out.println(maxValue(arr));
		System.out.println(minValue(arr));
		
		ArrayList<Integer> alst = new ArrayList<>(5);
		for (int j = 0; j < arr.length; j++) {
			alst.add(arr[j]);
		}

		System.out.println(alst);
		
		System.out.println(alst.clone());

	}
}
