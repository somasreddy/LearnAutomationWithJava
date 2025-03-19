package Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UniqueElementsFromArrays {

    public static void main(String[] args) {
        Integer[] arr1 = {1, 2, 3, 4, 5};
        Integer[] arr2 = {2, 3, 6};
        Integer[] arr3 = {3, 7, 8};

        // Call the method to get unique elements across arrays
        Set<Integer> uniqueElements = findUniqueElements(arr1, arr2, arr3);

        // Print the result
        System.out.println("Unique elements: " + uniqueElements);
    }

    public static Set<Integer> findUniqueElements(Integer[] arr1, Integer[] arr2, Integer[] arr3) {
        // Convert arrays to sets
        Set<Integer> set1 = new HashSet<>(Arrays.asList(arr1));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(arr2));
        Set<Integer> set3 = new HashSet<>(Arrays.asList(arr3));

        // Combine all elements from three sets
        Set<Integer> allElements = new HashSet<>(set1);
        allElements.addAll(set2);
        allElements.addAll(set3);

        // Find common elements in all sets
        Set<Integer> commonElements = new HashSet<>(set1);
        commonElements.retainAll(set2);
        commonElements.retainAll(set3);

        // Remove common elements to keep only unique ones
        allElements.removeAll(commonElements);

        return allElements;
    }
}
