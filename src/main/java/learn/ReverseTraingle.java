package learn;

import java.util.Scanner;

public class ReverseTraingle {
	
	    public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);
	       System.out.println("Enter height of the triangle :");
	       int h = sc.nextInt();
//	        System.out.println("Which character you want to use : ");
//	        c = sc.next().charAt(0);
	        int i, j, k;
	        for (i=h;i>0;i--) {
	            for (j=h;j>i;j--) {
	            	System.out.print(" ");
	            }
	            for (k=0;k<(2*i-1);k++) {
	            	System.out.print(" "+"*");
	            	}
	            System.out.println();
	        }
	    }
	}