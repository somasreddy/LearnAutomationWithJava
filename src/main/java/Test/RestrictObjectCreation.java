package Test;

import java.util.Scanner;

@SuppressWarnings("serial")
class ObjectLimitExceededException extends Throwable {
}

public class RestrictObjectCreation {
	static int objCount = 0;
	static int num;

	RestrictObjectCreation() throws ObjectLimitExceededException {
		objCount++;
		if (objCount > num)
			throw new ObjectLimitExceededException();
	}

	public static void main(String[] args) throws ObjectLimitExceededException {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the number of objects to be allowed :");
		num = sc.nextInt();

		for (int i = 0; i < num; i++) {
			RestrictObjectCreation instance = new RestrictObjectCreation();
			System.out.println(i + 1 + " . " + instance.hashCode());
		}

		sc.close();

	}
}
