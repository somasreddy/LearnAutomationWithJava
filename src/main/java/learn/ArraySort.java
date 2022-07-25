package learn;



public class ArraySort {
	public static void bubbleSort(int[] arr) {  
        int n = arr.length;  
        int temp = 0;  
        for(int i=0; i < n; i++) {  
           for(int j=1; j < (n-i); j++) {  
           	 if(arr[j-1] > arr[j]){  
           		 //swap elements  
           		 temp = arr[j-1];  
           		 arr[j-1] = arr[j];  
           		 arr[j] = temp;  
            	 }  
             }  
        }  
    }  
	  
	  public static void selectionSort(int[] arr){  
	        for (int i = 0; i < arr.length - 1; i++)  
	        {  
	            int index = i;  
	            for (int j = i + 1; j < arr.length; j++){  
	                if (arr[j] < arr[index]){  
	                    index = j;//searching for lowest index  
	                }  
	            }  
	            int smallerNumber = arr[index];   
	            arr[index] = arr[i];  
	            arr[i] = smallerNumber;  
	        }  
	    }  
	
	  public static void insertionSort(int array[]) {  
	        int n = array.length;  
	        for (int j = 1; j < n; j++) {  
	            int key = array[j];  
	            int i = j-1;  
	            while ( (i > -1) && ( array [i] > key ) ) {  
	                array [i+1] = array [i];  
	                i--;  
	            }  
	            array[i+1] = key;  
	        }  
	    }  
	
	
	public static void main(String[] args) {

	int[] arr= {200,125,1000,35,99,103,10};
	
	bubbleSort(arr);
	System.out.print("{");
	for(int i=0;i<arr.length;i++) {
		System.out.print(arr[i]+" ");
	}
	System.out.println("}");
	
	
	
	
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	} 

