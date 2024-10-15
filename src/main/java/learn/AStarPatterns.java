package learn;

public class AStarPatterns {
    public static String getMethodName() {
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }
    public static void main(String[] args) {
        pattern0(5);    pattern1(5);    pattern2(5);    pattern3(5);
        pattern4(5);    pattern5(5);    pattern6(5);    pattern7(5);
        pattern8(5);    pattern9(5);    pattern10(5);   pattern11(5);
//        pattern12(5);      pattern13(5);      pattern14(5);      pattern15(5);
//        pattern16(5);      pattern17(5);      pattern18(5);
    }
    static void pattern11(int n) {
        System.out.println(getMethodName());
        for(int row = 1; row<=2*n-1; row++) {
            int c = row>n ? 2*n-row : row;
            for(int s = 1; s<=c; s++) {
                System.out.print(" ");
            }
            for(int col = 1; col<=c; col++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println("==================================");
    }

    static void pattern10(int n) {
        System.out.println(getMethodName());
        for(int row = 1; row<=2*n-1; row++) {
            int c = row>n ? 2*n-row : row;
            for(int s = 1; s<=c; s++) {
                System.out.print(" ");
            }
            for(int col = 1; col<=c; col++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println("==================================");
    }

    static void pattern9(int n) {
        System.out.println(getMethodName());
        for(int row = 1; row<=2*n-1; row++) {
            int c = row>n ? 2*n-row : row;
            for(int s = 1; s<=n-c; s++) {
                System.out.print("  ");
            }
            for(int col = 1; col<=2*c-1; col++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println("==================================");
    }

    static void pattern8(int n) {
        System.out.println(getMethodName());
        for(int row = 1; row<=2*n-1; row++) {
            int c = row>n ? 2*n-row : row;
            for(int s = 1; s<=n-c; s++) {
                System.out.print("  ");
            }
            for(int col = 1; col<=c; col++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println("==================================");
    }

    static void pattern7(int n) {
        System.out.println(getMethodName());
        for(int row = n; row>=1; row--) {
            for(int s = 1; s<=n-row; s++) {
                System.out.print("  ");
            }
            for(int col = 1; col<=2*row-1; col++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println("==================================");
    }

    static void pattern6(int n) {
        System.out.println(getMethodName());
        for(int row = 1; row<=n; row++) {
            for(int s = 1; s<=n-row; s++) {
                System.out.print("  ");
            }
            for(int col = 1; col<=2*row-1; col++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println("==================================");
    }

    static void pattern5(int n) {
        System.out.println(getMethodName());
        for(int row = n; row>=1; row--) {

            for(int s = 1; s<=n-row; s++) {
                System.out.print(" ");
            }
            for(int col = 1; col<=row; col++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println("==================================");
    }

    static void pattern4(int n) {
        System.out.println(getMethodName());
        for(int row = 1; row<=n; row++) {
            for(int s = 1; s<=n-row; s++) {
                System.out.print(" ");
            }
            for(int col = 1; col<=row; col++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println("==================================");
    }

    static void pattern3(int n) {
        System.out.println(getMethodName());
        for(int row = 1; row<=2*n-1; row++) {
            int c = row>n ? 2*n-row : row;
            int space = n-c;
            for(int s = 1; s<=space; s++) {
                System.out.print(" ");
            }
            for(int col = 1; col<=c; col++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println("==================================");
    }

    static void pattern2(int n) {
        System.out.println(getMethodName());
        for(int row = 1; row<=2*n-1; row++) {
            int c = row>n ? 2*n-row : row;
            for(int col = 1; col<=c; col++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println("==================================");
    }

    static void pattern1(int n) {
        System.out.println(getMethodName());
        for(int row = 1; row<=n; row++) {
            for(int col = 1; col<=n-row+1; col++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println("==================================");
    }

    static void pattern0(int n) {
        System.out.println(getMethodName());
        for(int row = 1; row<=n; row++) {
            for(int col = 1; col<=row; col++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println("==================================");
    }
}
