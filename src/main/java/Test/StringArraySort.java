package Test;

import java.util.HashMap;
import java.util.Map;

public class StringArraySort {

	public static void main(String[] args) {
		int[] arr = { 4, 8, 3, 2, 1 };
		int sum = 5;
		int size=arr.length;
		Map<Integer,Integer> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			if(arr[i]+arr[i+1]==sum) {
				map.put(arr[i], arr[i+1]);
			}
		}

		for( int i=0;i<size;i++) {
			for(int j=i+1;j<size;j++) {
				if(arr[i]+arr[j]==sum) {
					System.out.println("Sum"+arr[i]+","+arr[j]);
				}

			}
		}


		//		String[] str = { "January", "February", "March", "April", "May", "June" };
		//		Set<String> set = new TreeSet<>();
		//		for (int i = 0; i < str.length; i++) {
		//			set.add(str[i]);
		//		}
		//		System.out.println(set);
	}
}
