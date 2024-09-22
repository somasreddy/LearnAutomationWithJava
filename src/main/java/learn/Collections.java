package learn;

import java.util.ArrayDeque;
import java.util.LinkedList;

public class Collections {
    public static void main(String[] args) {
	ArrayDeque<String> ad = new ArrayDeque<String>();
	LinkedList<String> lst = new LinkedList<String>();
	lst.add("asji");
	ad.add("12");
	System.out.println(ad + " " + lst);

    }

}
