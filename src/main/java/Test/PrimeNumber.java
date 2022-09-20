package Test;

import java.util.Scanner;

class PrimeNumber {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("enter the numer:");
		int num = sc.nextInt();
		int count = 0;
		for (int i = 2; i < num; i++) {
			for (int j = i + 1; j < i; j++) {
				if (num % i == 0) {
					count++;
				}
			}
		}
		if (count > 1) {
			System.out.println("given Num is not prime");
		} else {
			System.out.println("given Num is prime");
		}
		sc.close();
	}
}
