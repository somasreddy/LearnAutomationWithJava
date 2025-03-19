package Test;

import java.util.Scanner;

class PrimeNumber {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the number:");
		int num = sc.nextInt();

		// Prime numbers are greater than 1
		if (num <= 1) {
			System.out.println("Number is not prime");
		} else {
			boolean isPrime = true;
			// Check divisibility from 2 to sqrt(num)
			for (int i = 2; i <= Math.sqrt(num); i++) {
				if (num % i == 0) {
					isPrime = false;
					break;  // No need to check further if a divisor is found
				}
			}
			if (isPrime) {
				System.out.println("The number is prime");
			} else {
				System.out.println("The number is not prime");
			}
		}
		System.out.println("Num of Primes until "+num+" is :"+printPrimes(num));
		sc.close();
	}

	static int printPrimes(int limNum){
		int count=0;
		for (int num = 2; num <= limNum; num++) {
			boolean isPrime = true;
			// Check if the number is prime
			for (int i = 2; i <= Math.sqrt(num); i++) {
				if (num % i == 0) {
					isPrime = false; // Not a prime number
					break; // No need to check further
				}
			}
			// If the number is prime, print it
			if (isPrime) {
				System.out.print(num + " ");
				count++;
			}
		}
		System.out.println(); // Print a new line at the end
		return count;
	}
}
