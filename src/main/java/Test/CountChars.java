package Test;

import java.util.HashMap;
import java.util.Map;

public class CountChars {
	public static void main(String[] args) {

		
		String s1="100";
		int i1=Integer.parseInt(s1);
		System.out.println(i1);
		
		String s = "My name is Somasekhar V";
		Map<Character, Integer> cmap = new HashMap<>();
		Integer count = 0;
		for (int i = 0; i < s.length(); i++) {
			count = cmap.get(s.charAt(i));
			if (count == null) {
				cmap.put(s.charAt(i), 1);
			} else {
				count++;
				cmap.put(s.charAt(i), count);
			}
		}
		System.out.println(cmap);
	}
}
