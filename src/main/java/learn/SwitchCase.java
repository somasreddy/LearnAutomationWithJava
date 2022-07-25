package learn;

import java.util.Scanner;

public class SwitchCase {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter The Code");
		String ip=sc.nextLine();
		char ip1=ip.toUpperCase().charAt(0);
		switch(ip1) {
		case 'I':
			System.out.println("India");
			break;
		case 'M':
			System.out.println("Mandya");
			break;
			default : System.out.println("Invalid Selection");
		}
	}
	
}
