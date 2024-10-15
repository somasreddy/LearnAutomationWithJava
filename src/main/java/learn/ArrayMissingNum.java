package learn;

import java.util.Scanner;

public class ArrayMissingNum {
	public static void main(String[] args) {
	     Scanner sc = new Scanner(System.in);
	        
	        // Input the size of the array
	        int size = sc.nextInt();
	        
	        // Input the array elements
	        int[] arr = new int[size];
	        for (int i = 0; i < size; i++) {
	            arr[i] = sc.nextInt();
	        }
	        
	        boolean[] present = new boolean[11];
	        for (int i = 0; i < size; i++) {
	            if (arr[i] >= 1 && arr[i] <= 10){
	              present[arr[i]] = true;
	            }
	        }
	        // Print missing numbers from 1 to 10
	        for (int i = 1; i <= arr[size-1]; i++) {
	            if (!present[i]) {
	                System.out.println(i);
	            }
	        }
	        sc.close();
	  }
}
