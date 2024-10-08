package learn;

import java.util.HashMap;
import java.util.Map;

public class ThirdLargest {
    public static void main(String args[]) {
	int size;
//	int arr[] = { 125, 128, 10, 30, 66, 25, 63, 96, 57, 5 };
	int arr[]={2,5,12,6,21,13,4,1};
	size = arr.length;
	System.out.println("UnSorted Array");
//	for (int i = 0; i < size; i++)
//	    System.out.print(arr[i] + ",");
//	
//	for (int i = 0; i < size; i++) {
//	    for (int j = i + 1; j < size; j++) {
//		if (arr[i] > arr[j]) {
//		    arr[i] = arr[i] + arr[j];
//		    arr[j] = arr[i] - arr[j];
//		    arr[i] = arr[i] - arr[j];
//		}
//	    }
//	}
//	System.out.println();
//
//	System.out.println("Sorted Array");
//	for (int i = 0; i < arr.length; i++)
//	    System.out.print(arr[i] + ",");
//	System.out.println();
//	System.out.println("Third largest number is:: " + arr[size - 2]);
//	
//	System.out.println("Fourth Smallest number is:: " + arr[3]);
	
	
	
	HashMap <Integer,String> hm=new HashMap<>();
	for(int i=0;i<arr.length;i++) {
	hm.put(arr[i],"a");
	}
	System.out.println(hm);
	for(Map.Entry<Integer, String> entry:hm.entrySet()) {
	    System.out.println(entry.getKey());
	}
	
    }
}