package learn;

import java.util.Scanner;

public class ArrangeArray {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Input the size of the array
        int size = sc.nextInt();

        // Input the array elements
        int[] arr = new int[size];

        for (int i = 0; i < size; i++) {
            arr[i] = sc.nextInt();
        }

        // Sorting the array using bubble sort
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (arr[i] > arr[j]) {
                    arr[i] = arr[i] + arr[j];
                    arr[j] = arr[i] - arr[j];
                    arr[i] = arr[i] - arr[j];
                }
            }
        }
        //Print Sorted Array
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i]);
        }

        int y = (size + 1) / 2;
        for (int i = 0; i < y; i++) {
            System.out.println(arr[i]);// Print smallest
            int x = size - i - 1;
            if (i != x) {// Check if there is a different element for the largest
                System.out.println(arr[x]); // Print largest
            }
        }
        sc.close();
    }
}
