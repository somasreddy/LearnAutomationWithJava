package learn;

public class ThirdLargest {
    public static void main(String args[]) {
	int size;
	int arr[] = { 125, 128, 10, 30, 66, 25, 63, 96, 57, 5 };
	size = arr.length;
	System.out.println("UnSorted Array");
	for (int i = 0; i < arr.length; i++)
	    System.out.print(arr[i] + ",");
	for (int i = 0; i < size; i++) {
	    for (int j = i + 1; j < size; j++) {
		if (arr[i] > arr[j]) {
		    arr[i] = arr[i] + arr[j];
		    arr[j] = arr[i] - arr[j];
		    arr[i] = arr[i] - arr[j];
		}
	    }
	}
	System.out.println();

	System.out.println("Sorted Array");
	for (int i = 0; i < arr.length; i++)
	    System.out.print(arr[i] + ",");
	System.out.println();
	System.out.println("Third largest number is:: " + arr[size - 3]);
	
	System.out.println("Fourth Smallest number is:: " + arr[3]);
    }
}