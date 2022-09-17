package Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestSubstring {

	public static void main(String[] args) {
		
		String s="pwwkew";
		char[] sc=s.toCharArray();//[a,b,c,a,b,c,b,b]
		List<Character> ls=new ArrayList<>();
		List<Character> ls1=new ArrayList<>();
		
		Character[] c= {'a','b','c','b','b'};
		List<Character> ls2=new ArrayList<>(Arrays.asList(c));
		
		Character[] c1= {'a','b','c','a','b','b','b'};
		List<Character> ls3=new ArrayList<>(Arrays.asList(c1));
//		ls2.retainAll(ls3);
//		System.out.println(ls2);
		ls2.removeAll(ls3);
		System.out.println(ls2);
		System.out.println(ls3);
		
//		System.out.println(ls2.lastIndexOf('b'));
		
		
//		ls2.addAll(Arrays.asList(c));
//		Collections.addAll(ls2, c);
//		ls2.forEach(e -> System.out.println(e));
//		System.out.println("ls2:"+ls2);
		for (int i = 0; i < sc.length; i++) {
			ls.add(sc[i]);
		}
		for(int i=0;i<ls.size();i++){
			for(int j=i+1;j<ls.size();j++) {
				if((ls.get(i)!=ls.get(j))&&!(ls1.contains(ls.get(i)))) {
					ls1.add(ls.get(i));
				}
			}
		}
		System.out.println(ls1);
	}
}
