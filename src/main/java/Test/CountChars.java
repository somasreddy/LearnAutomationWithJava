package Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CountChars {
	public static void main(String[] args) {

		String s = "My name is Somasekhar V";
		char[] c = s.toCharArray();

		Map<Character, Integer> cmap = new HashMap<>();
		Integer count = 0;
		for (int i = 0; i < c.length; i++) {
			count = cmap.get(c[i]);
			if (count == null) {
				cmap.put(c[i], 1);
			} else {
				count++;
				cmap.put(c[i], count);
			}
		}
		System.out.println(cmap);
	}
}
