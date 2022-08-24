package learn;

import java.util.Scanner;

public class ASCIICode {
public static void main(String[] args) {
	Scanner input =new Scanner(System.in);
	System.out.println("Enter the Character for which ascii code to be generted: ");
	char c=input.nextLine().charAt(0);
	System.out.println((int)c);
	input.close();
}
}