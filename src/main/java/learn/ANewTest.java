package learn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ANewTest {
    String filePath = "./abc.txt";

    {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int[] arr = {37, 89, 0, 0, 18, 0, 19};

        moveZerosFront(arr);
        for(int num : arr) {
            System.out.print(num+" ");
        }
        System.out.println();
        sortIntArr(arr);
        for(int num : arr) {
            System.out.print(num+" ");
        }
        System.out.println();
        String[] words = {"Bangalore", "Adilabad", "Delhi", "Chennail"};
        sortArrayWords(words);
        for(String w : words) {
            System.out.print(w+" ");

        }
        System.out.println();
        System.out.println(revString("String to Reverse"));
//        System.out.println();
        System.out.println(parseString("somasekhar.v@escriba.de"));

        System.out.println(countVowels("String to Reversify"));

        printVowels("String to Reversify");
        System.out.println();
        pritnInvertTriangle(5);

        System.out.println(revNum(1092831));
        printRohmbus(5);
        printTraingle(5);
    }

    static void printTraingle(int n){
        for(int row = 1; row<=n; row++) {
            for(int s = 1; s<=n-row; s++) {
                System.out.print(" ");
            }
            for(int col = 1; col<=row; col++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    static void printRohmbus(int n) {
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
    }

    static void pritnInvertTriangle(int n) {
        for(int row = 1; row<=n; row++) {
            for(int s = 1; s<=row; s++) {
                System.out.print(" ");
            }
            for(int col = 1; col<=(n-row+1); col++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    static void printVowels(String input) {
        Set<Character> printedVowels = new HashSet<>();
        String vowels = "aeiouAEIOU";

        // Convert input to a char array and iterate over it
        for(char ch : input.toCharArray()) {
            if(vowels.indexOf(ch) != -1&&!printedVowels.contains(ch)) {
                System.out.print(ch);
                printedVowels.add(ch);  // Add the vowel to the set to avoid duplication
            }
        }
    }

    static int countVowels(String input) {
        String vowels = "aeiouAEIOU";

        // Loop through each character and print if it's a vowel
        int count = 0;
        for(char ch : input.toCharArray()) {
            if(vowels.indexOf(ch) != -1) {
                count++;
            }
        }
        return count;
    }

    static String parseString(String s) {
        String name = s.split("@")[0];
        String domain = (s.split("@")[1]).split("\\.")[0];
        // Capitalizing the first letter of name and domain
        String cName = name.substring(0, 1).toUpperCase()+name.substring(1).toLowerCase();
        String cOrg = domain.substring(0, 1).toUpperCase()+domain.substring(1).toLowerCase();

        return "Hi "+cName+", Welcome to "+cOrg;
    }

    static int[] sortIntArr(int[] arr) {
        for(int i = 0; i<arr.length; i++) {
            for(int j = i+1; j<arr.length; j++) {
                if(arr[i]>arr[j]) {
                    arr[i] = arr[i]+arr[j];
                    arr[j] = arr[i]-arr[j];
                    arr[i] = arr[i]-arr[j];
                }
            }
        }
        return arr;
    }

    static int[] moveZerosFront(int[] arr) {
        // Two pointer approach to move zeros to the front
        int n = arr.length;
        int nonZeroIndex = n-1; // Index where the next non-zero element should go
        // Traverse the array from the end
        for(int i = n-1; i>=0; i--) {
            if(arr[i] != 0) {
                arr[nonZeroIndex--] = arr[i]; // Move non-zero element to the correct position
            }
        }
        // Fill the remaining positions with zeros
        while(nonZeroIndex>=0) {
            arr[nonZeroIndex--] = 0;
        }
        return arr;
    }

    static String[] sortArrayWords(String[] str) {
        for(int i = 0; i<str.length; i++) {
            for(int j = i+1; j<str.length; j++) {
                if((int) (str[i].charAt(0))>(int) (str[j].charAt(0))) {
                    String temp = str[i];
                    str[i] = str[j];
                    str[j] = temp;
                }
            }
        }
        return str;
    }

    static String revString(String str) {
        String temp = "";
        for(int i = str.length()-1; i>=0; i--) {
            temp += str.charAt(i);
        }
        return temp;
    }

    static int revNum(int n) {
        int rem, rev = 0;
        while(n != 0) {
            rem = n%10;
            rev = rev*10+rem;
            n = n/10;
        }
        return rev;
    }
}

