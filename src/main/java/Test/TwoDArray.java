package Test;

import java.util.Arrays;

public class TwoDArray {
	public static void main(String[] args) {
		
		int[][] arr = {{1, 1, 2, 2, 3, 4, 5},{1, 1, 1, 1, 1, 1, 1},{1, 2, 3, 4, 5, 6, 7},{1, 2, 1, 1, 1, 1, 1}};;

		int a=arr.length;
		for(int i = 0; i < a; i++){
		    for(int j = 0; j < arr[i].length; j++){
		    	for(int k=j+1;k<arr[i].length; k++) {
		    		if(arr[i][j]==arr[i][k]) {
		    			arr[i][k]=0;
		    		}
		    	}
//		        System.out.println(oddNumbers[i][j]);
		    }  
		}
		System.out.println(Arrays.toString(arr));  
		for(int i = 0; i < a; i++){
		    for(int j = 0; j < arr[i].length; j++){
		    	System.out.println(arr[i][j]);
		    }
		}
	}
	
}
