package Test;

import java.util.TreeSet;

public class Anagram {

	static boolean isAnagram(String s1, String s2) {
		char[] s1Arr = s1.toCharArray();
		char[] s2Arr = s2.toCharArray();
		TreeSet<Character> set1 = new TreeSet<>();
		TreeSet<Character> set2 = new TreeSet<>();
		for (int i = 0; i < s1Arr.length; i++) {
			set1.add(s1Arr[i]);
		}
		for (int i = 0; i < s2Arr.length; i++) {
			set2.add(s2Arr[i]);
		}
		System.out.println(set1);
		System.out.println(set2);

		if (set1.equals(set2))
			return true;
		else
			return false;
	}

	public static void main(String[] args) {
		System.out.println(isAnagram("mad", "daa m"));
		System.out.println(isAnagram("mad", "daam"));
		System.out.println(isAnagram("mad", "daa1"));
		System.out.println(isAnagram("mad$", "%daa1"));
		System.out.println(isAnagram("mad1%", "%mdaa1"));
		System.out.println(isAnagram("mad1", "mdaa1"));
	}
}
