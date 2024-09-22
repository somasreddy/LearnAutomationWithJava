package learn;

import java.util.HashSet;

public class Duplicates {
    public static void main(String[] args) {
	String[] arr = { "12", "345", "234", "12", "345","121","124" };
	String s = "Somasekhar";
	char[] arr1 = s.toCharArray();

	// Using Hash Set

	HashSet<String> set = new HashSet<String>();
	for (String str : arr) {
	    if (!set.add(str)) {
		System.out.println("Duplicate Element is : " + str);
	    }
	}
	System.out.println("Using For Loop");

	// Using comparison

	for (int i = 0; i < arr.length - 1; i++) {
	    for (int j = i + 1; j < arr.length; j++) {
		if ((arr[i].equals(arr[j])) && (i != j))
		    System.out.println("Duplicate Element is : " + arr[j]);
	    }
	}
	for (int i = 0; i < arr.length; i++)
	    System.out.println(arr1[i]);

    }
}
