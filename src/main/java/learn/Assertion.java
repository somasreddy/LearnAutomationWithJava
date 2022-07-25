package learn;

import java.util.Scanner;

public class Assertion {

	    public static void main(String[] args) 
	    {
	        System.out.print("Enter your marks: ");
	         
	        Scanner sc = new Scanner(System.in);
	         
	        int marks = sc.nextInt();
	        
	        assert marks >= 35 : "Fail";
	         
	        System.out.println("Marks = "+marks);
	     }
	}

