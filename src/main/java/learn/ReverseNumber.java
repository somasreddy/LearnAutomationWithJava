package learn;

import java.util.Scanner;

public class ReverseNumber {
    public static void main(String args[]) {
	int num, rev = 0, rem;
	try (Scanner scan = new Scanner(System.in)) {
	    System.out.print("Enter a Number : ");
	    num = scan.nextInt();
	    scan.close();
	}

	while (num != 0) {
	    rem = num % 10;
	    rev = rev * 10 + rem;
	    num = num / 10;
	}

	System.out.print("Reverse = " + rev);

    }

}
