package learn;


public class ANumPatterns {
    public static String getMethodName() {
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }

    public static void main(String[] args) {
//        ANumPatterns obj = new ANumPatterns();
//        Method[] methods = obj.getClass().getDeclaredMethods();
//
//        // Sort methods based on their names
//        Arrays.sort(methods, (m1, m2) -> m1.getName().compareTo(m2.getName()));
//
//        for(Method method : methods) {
//            try {
//                // Invoke methods that start with "pattern"
//                if(method.getName().startsWith("pattern")) {
//                    method.invoke(obj, 5); // Pass the parameter (in this case, 5)
//                }
//            } catch (InvocationTargetException | IllegalAccessException e) {
//                throw new RuntimeException(e);
//            }
//        }
        pattern0(5);
        pattern1(5);
        pattern2(5);
        pattern3(5);
        pattern4(5);
        pattern5(5);
        pattern6(5);
        pattern7(5);
        pattern8(5);
        pattern9(5);
        pattern10(5);
        pattern11(5);
    }

    static void pattern11(int n) {
        System.out.println(getMethodName());
        for(int row = 1; row<=2*n-1; row++) {
            int c = row>n ? 2*n-row : row;
            for(int col = 1; col<=c; col++) {
                System.out.print(col+" ");
            }
            for(int s = 1; s<=2*(n-c); s++) {
                System.out.print("  ");
            }
            for(int col = 1; col<=c; col++) {
                System.out.print(c-col+1+" ");
            }
            System.out.println();
        }
        System.out.println("==================================");
    }

    static void pattern10(int n) {
        System.out.println(getMethodName());
        for(int row = 1; row<=2*n-1; row++) {
            int c = row>n ? 2*n-row : row;
            for(int s = 1; s<=n-c; s++) {
                System.out.print("  ");
            }
            for(int col = 1; col<=c; col++) {
                System.out.print(col+" ");
            }
            System.out.println();
        }
        System.out.println("==================================");
    }

    static void pattern9(int n) {
        System.out.println(getMethodName());
        for(int row = 1; row<=2*n-1; row++) {
            int c = row>n ? 2*n-row : row;
            for(int col = 1; col<=c; col++) {
                System.out.print(col+" ");
            }
            System.out.println();
        }
        System.out.println("==================================");
    }

    static void pattern8(int n) {
        System.out.println(getMethodName());
        for(int row = 1; row<=n; row++) {
            for(int s = 1; s<=n-row; s++) {
                System.out.print(" ");
            }
            for(int col = 1; col<=row; col++) {
                System.out.print(row+" ");
            }
            System.out.println();
        }
        System.out.println("==================================");
    }

    static void pattern7(int n) {
        System.out.println(getMethodName());
        int num = 1;
        for(int row = 1; row<=n; row++) {
            for(int col = 1; col<=n-row+1; col++) {
                System.out.print(num+" ");
                num++;
            }
            System.out.println();
        }
        System.out.println("==================================");
    }

    static void pattern6(int n) {
        System.out.println(getMethodName());
        int num = 1;
        for(int row = 1; row<=n; row++) {

            for(int col = 1; col<=row; col++) {
                System.out.print(num+" ");
                num++;
            }
            System.out.println();
        }
        System.out.println("==================================");
    }

    static void pattern5(int n) {
        System.out.println(getMethodName());
        for(int row = 1; row<=n; row++) {
            for(int col = 1; col<=n-row+1; col++) {
                System.out.print(col+" ");
            }
            System.out.println();
        }
        System.out.println("==================================");
    }

    static void pattern4(int n) {
        System.out.println(getMethodName());
        for(int row = 1; row<=n; row++) {
            for(int col = 1; col<=row; col++) {
                System.out.print(col+" ");
            }
            System.out.println();
        }
        System.out.println("==================================");
    }

    static void pattern3(int n) {
        System.out.println(getMethodName());
        for(int row = 1; row<=2*n-1; row++) {
            int totalCol = row>n ? 2*n-row : row;
            int space = n-totalCol;
            for(int s = 1; s<=space; s++) {
                System.out.print(" ");
            }
            for(int col = 1; col<=totalCol; col++) {
                System.out.print(col+" ");
            }
            System.out.println();
        }
        System.out.println("==================================");
    }

    static void pattern2(int n) {
        System.out.println(getMethodName());
        for(int row = 1; row<=2*n-1; row++) {
            int c = row>n ? 2*n-row : row;
            for(int s = 0; s<n-c; s++) {
                System.out.print("  ");
            }
            for(int col = c; col>=1; col--) {
                System.out.print(col+" ");
            }
            for(int col = 2; col<=c; col++) {
                System.out.print(col+" ");

            }
            System.out.println();
        }
        System.out.println("==================================");
    }

    static void pattern1(int n) {
        System.out.println(getMethodName());
        for(int row = 1; row<=n; row++) {
            int c = row>n ? n-row : row;
            for(int s = 1; s<=n-c; s++) {
                System.out.print("  ");
            }
            for(int col = c; col>=1; col--) {
                System.out.print(col+" ");
            }
            for(int col = 2; col<=c; col++) {
                System.out.print(col+" ");

            }
            System.out.println();
        }
        System.out.println("==================================");
    }


    static void pattern0(int n) {
        System.out.println(getMethodName());
        for(int row = 1; row<=2*n-1; row++) {
            int totalCol = row>n ? 2*n-row : row;
            for(int col = 1; col<=totalCol; col++) {
                System.out.print(col+" ");
            }
            System.out.println();
        }
        System.out.println("==================================");
    }


}
