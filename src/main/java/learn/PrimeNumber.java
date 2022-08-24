package learn;

import java.util.Scanner;

class Prime {
	static void isPrime(int num) {
		int count = 0;
		for (int i = 1; i <= num; i++) {
			if (num % i == 0) {
				count++;
			}
		}
		if (count == 2)
			System.out.println("prime number ");
		else
			System.out.println("Not a prime number ");
	}
}

public class PrimeNumber {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int i, num;
		// Empty String
		String primes = "";
		System.out.println("Enter the value of n:");
		int n = sc.nextInt();

		for (num = 1; num <= n; num++) {
			int count = 0;
			for (i = 1; i <= num; i++) {
				if (num % i == 0) {
					count++;
				}

			}
			if (count == 2) {
				// Appended the Prime number to the String
				primes += num + " ";
			}
			/*
			 * else if(count>2) { break; }
			 */
		}
		System.out.println("Prime numbers from 1 to " + n + " are :\n" + primes);

		System.out.println("--------------------------");

		for (num = 1; num <= n; num++) {
			int count = 0;
			num = n;
			for (i = 1; i <= num; i++) {
				if (num % i == 0) {
					count++;
				}
			}
			if (count == 2) {
				System.out.println("The Given Number " + num + " is a prime number");
			} else {
				System.out.println("The Given Number " + num + " is not a prime number");
			}
		}

		System.out.println("--------------------------");

		/*
		 * boolean isPrime=true; System.out.println("Enter any number:"); //capture the
		 * input in an integer int ipnum=sc.nextInt(); sc.close(); for(int
		 * j=2;j<=ipnum/2;j++) { if((ipnum%j)==0) { isPrime=false; break; } } //If
		 * isPrime is true then the number is prime else not if(isPrime)
		 * System.out.println(ipnum + " is a Prime Number"); else
		 * System.out.println(ipnum + " is not a Prime Number");
		 */
		sc.close();
	}
}
