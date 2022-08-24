package learn;

public class _3rdLargest {
	public static void main(String args[]) {
		int temp, size;
		int arr[] = { 10, 30, 25, 63, 96, 57 ,5};
		size = arr.length;
		for(int i=0;i<arr.length;i++)
			System.out.print(arr[i]+",");
		for (int i = 0; i < size; i++) {
			for (int j = i + 1; j < size; j++) {
				if (arr[i] > arr[j]) {
					arr[i]=arr[i]+arr[j];
					arr[j]=arr[i]-arr[j];
					arr[i]=arr[i]-arr[j];
					}
			}
		}
		System.out.println();
		for(int i=0;i<arr.length;i++)
			System.out.print(arr[i]+",");
		System.out.println("Third largest number is:: " + arr[size - 3]);
	}
}