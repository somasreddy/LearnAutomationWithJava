package Test;

import java.sql.SQLException;
import java.util.Scanner;

public class SecondSmallest {
	/*
	 * Aim is to find second smallest element in an array in an efficient manner.
	 */
	public static void main(String[] args) throws SQLException {

//		for (int i = 1; i < 5; i++) {
//			for (int j = 1; j < 3; j++) {
//				System.out.println("i = " + i + "; j = " + j);
//				if (i == 2)
//					break;
//			}
//		}
		int[] arr2 = { 10,30,120,11,8,1,6,2,40,2,0};
		int secondSmallest = get2ndSmallest(arr2);
		// Below prints: 2.
		if (secondSmallest == Integer.MAX_VALUE) {	
			System.out.println("Size should be greater than 2.");
		} else {
			System.out.println(secondSmallest);
		}
	}

	// Supporting method for finding 2nd
	// smallest value in array.
	private static int get2ndSmallest(int[] arr) throws SQLException {
		int smallest = Integer.MAX_VALUE;
		int secondSmallest = Integer.MAX_VALUE;
		for (int num : arr) {
			// Update second smallest if a new running min is found
			if (num < smallest) {
				secondSmallest = smallest;
				smallest = num;
			} else if (num < secondSmallest && num != smallest) {
				secondSmallest = num;
			}
		}
				
		try(Scanner sc= new Scanner(System.in)){
			
		}
		return secondSmallest;
	}
}