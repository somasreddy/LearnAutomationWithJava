package Test;

import java.util.HashMap;
import java.util.Map;

public class CountCharsInSequence {
//	aabcedffghghhaa
//	a=2
//	b=0
//	c=0
//	f=1
//	h=1
	public static void main(String[] args) {
		String s = "aabccddefaaghhg";
		char[] cs = s.toCharArray();
		Map<Character, Integer> map = new HashMap<>();
		Integer count = 0;
		for (int i = 0; i < cs.length; i += 2) {
			char s1 = cs[i];
			char s2 = cs[i + 1];
			count = map.get(cs[i]);
			if (count == null) {
				map.put(cs[i], 0);
				count=0;
			}
			if (s1 == s2) {
				count+=1;
			}
		}
		System.out.println(map);
	}
}
