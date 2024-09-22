package learn;

public class RevArray {

    public static void main(String[] args) {
//	int temp;
//	String[] arr= {"Birth","Survive","Death","Rebirth"};
//	String temp;
	int[] arr = { 1, 2, 3, 4, 5 };
	int n = arr.length;
	System.out.print("Array Before Reverse: {");
	for (int i = 0; i < n; i++) {
	    System.out.print(arr[i] + ",");
	}
	System.out.println("}");

	// using reverse iteration
	System.out.print("Array After Reverse: {");
	for (int i = n - 1; i >= 0; i--) {
	    System.out.print(arr[i] + ",");
	}
	System.out.println("}");

	// using swap
	for (int i = 0; i < n / 2; i++) {
	    arr[i] = arr[i] + arr[n - 1 - i];
	    arr[n - 1 - i] = arr[i] - arr[n - 1 - i];
	    arr[i] = arr[i] - arr[n - 1 - i];
	    /*
	     * temp = arr[i]; arr[i] = arr[n-1-i]; arr[n-1-i] = temp;
	     */
	}
	System.out.print("Array After Reverse: {");
	for (int i = 0; i < n; i++) {
	    System.out.print(arr[i] + ",");
	}
	System.out.println("}");

    }
}
