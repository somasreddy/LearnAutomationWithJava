package Test;
/*
 1. *****
    *****
    *****
    *****
    *****


2.  *
    **
    ***
    ****
    *****


3.  *****
    ****
    ***
    **
    *


4.  1
    1 2
    1 2 3
    1 2 3 4
    1 2 3 4 5


5.  *
    **
    ***
    ****
    *****
    ****
    ***
    **
    *


6.       *
        **
       ***
      ****
     *****


7.   *****
      ****
       ***
        **
         *


8.      *
       ***
      *****
     *******
    *********


9.  *********
     *******
      *****
       ***
        *


10.      *
        * *
       * * *
      * * * *
     * * * * *


11.  * * * * *
      * * * *
       * * *
        * *
         *


12.  * * * * *
      * * * *
       * * *
        * *
         *
         *
        * *
       * * *
      * * * *
     * * * * *


13.      *
        * *
       *   *
      *     *
     *********


14.  *********
      *     *
       *   *
        * *
         *


15.      *
        * *
       *   *
      *     *
     *       *
      *     *
       *   *
        * *
         *


16.           1
            1   1
          1   2   1
        1   3   3   1
      1   4   6   4   1


17.      1
        212
       32123
      4321234
       32123
        212
         1


18.   **********
      ****  ****
      ***    ***
      **      **
      *        *
      *        *
      **      **
      ***    ***
      ****  ****
      **********


19.    *        *
       **      **
       ***    ***
       ****  ****
       **********
       ****  ****
       ***    ***
       **      **
       *        *


20.    ****
       *  *
       *  *
       *  *
       ****

21.    1
       2  3
       4  5  6
       7  8  9  10
       11 12 13 14 15

22.    1
       0 1
       1 0 1
       0 1 0 1
       1 0 1 0 1

23.        *      *
         *   *  *   *
       *      *      *

24.    *        *
       **      **
       * *    * *
       *  *  *  *
       *   **   *
       *   **   *
       *  *  *  *
       * *    * *
       **      **
       *        *

25.       *****
         *   *
        *   *
       *   *
      *****

26.   1 1 1 1 1 1
      2 2 2 2 2
      3 3 3 3
      4 4 4
      5 5
      6

27.   1 2 3 4  17 18 19 20
        5 6 7  14 15 16
          8 9  12 13
            10 11

28.      *
        * *
       * * *
      * * * *
     * * * * *
      * * * *
       * * *
        * *
         *

29.      
       *        *
       **      **
       ***    ***
       ****  ****
       **********
       ****  ****
       ***    ***
       **      **
       *        *

30.         1
          2 1 2
        3 2 1 2 3
      4 3 2 1 2 3 4
    5 4 3 2 1 2 3 4 5


31.      4 4 4 4 4 4 4  
         4 3 3 3 3 3 4   
         4 3 2 2 2 3 4   
         4 3 2 1 2 3 4   
         4 3 2 2 2 3 4   
         4 3 3 3 3 3 4   
         4 4 4 4 4 4 4   

32.    E
       D E
       C D E
       B C D E
       A B C D E

33.    a
       B c
       D e F
       g H i J
       k L m N o
     
34.    E D C B A
       D C B A
       C B A
       B A
       A
       
35.    1      1
       12    21
       123  321
       12344321
 */

public class Patterns {
	public static void main(String[] args) {
		pattern17(4);
	}

	static void pattern31(int n) {
		int originalN = n;
		n = 2 * n;
		for (int row = 0; row <= n; row++) {
			for (int col = 0; col <= n; col++) {
				int atEveryIndex = originalN - Math.min(Math.min(row, col), Math.min(n - row, n - col));
				System.out.print(atEveryIndex + " ");
			}
			System.out.println();
		}
	}

	static void pattern30(int n) {
		for (int row = 1; row <= n; row++) {

			for (int space = 0; space < n - row; space++) {
				System.out.print("  ");
			}

			for (int col = row; col >= 1; col--) {
				System.out.print(col + " ");
			}
			for (int col = 2; col <= row; col++) {
				System.out.print(col + " ");
			}

			System.out.println();
		}
	}

	static void pattern17(int n) {
		for (int row = 1; row <= 2 * n; row++) {

			int c = row > n ? 2 * n - row : row;

			for (int space = 0; space < n - c; space++) {
				System.out.print("  ");
			}

			for (int col = c; col >= 1; col--) {
				System.out.print(col + " ");
			}
			for (int col = 2; col <= c; col++) {
				System.out.print(col + " ");
			}

			System.out.println();
		}
	}

	static void pattern28(int n) {
		for (int row = 0; row < 2 * n; row++) {
			int totalColsInRow = row > n ? 2 * n - row : row;

			int noOfSpaces = n - totalColsInRow;
			for (int s = 0; s < noOfSpaces; s++) {
				System.out.print(" ");
			}

			for (int col = 0; col < totalColsInRow; col++) {
				System.out.print("* ");
			}
			System.out.println();
		}
	}

	static void pattern5(int n) {
		for (int row = 0; row < 2 * n; row++) {
			int totalColsInRow = row > n ? 2 * n - row : row;
			for (int col = 0; col < totalColsInRow; col++) {
				System.out.print("* ");
			}
			System.out.println();
		}
	}

	static void pattern4(int n) {
		for (int row = 1; row <= n; row++) {
			// for every row, run the col
			for (int col = 1; col <= row; col++) {
				System.out.print(col + " ");
			}
			// when one row is printed, we need to add a newline
			System.out.println();
		}
	}

	static void pattern3(int n) {
		for (int row = 1; row <= n; row++) {
			// for every row, run the col
			for (int col = 1; col <= n - row + 1; col++) {
				System.out.print("* ");
			}
			// when one row is printed, we need to add a newline
			System.out.println();
		}
	}

	static void pattern1(int n) {
		for (int row = 1; row <= n; row++) {
			// for every row, run the col
			for (int col = 1; col <= n; col++) {
				System.out.print("* ");
			}
			// when one row is printed, we need to add a newline
			System.out.println();
		}
	}

	static void pattern2(int n) {
		for (int row = 1; row <= n; row++) {
			// for every row, run the col
			for (int col = 1; col <= row; col++) {
				System.out.print("* ");
			}
			// when one row is printed, we need to add a newline
			System.out.println();
		}
	}

}
