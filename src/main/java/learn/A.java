package learn;

import java.sql.SQLOutput;
import java.util.Scanner;

public class A {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.nextLine();
//		String s="LOTUS"
		for(char c:s.toCharArray()){
			if(!(c>'a'&&c<'z'|| c>'A'&&c<'Z')){
				try {
                    throw new InvalidInputException("Only String of Alphabets Allowed as Input");
                } catch (InvalidInputException e) {
                    e.printStackTrace();
                }
            }
		}
		for(int i=0;i<s.length();i++) {
			for(int j=0;j<=i;j++) {
//				System.out.print(s.charAt(j)+" ");
				if(i==j) {
				System.out.println(s.charAt(i));
				}
				else {
				System.out.print(" ");
				}
			}
//			System.out.println();
		}
	}
}

class InvalidInputException extends Exception{
	InvalidInputException(String s){
		System.out.println(s);
	}
}
		
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	/*A(){  
		super();  
		System.out.println("child class constructor invoked");  
		}  
	{System.out.println("instance initializer block is invoked");} 
		  
		public static void main(String args[]){  
		
			A a=new A();
		
		}  
		  
}
		class B {
			
			B(){  
			
			System.out.println("parent class constructor invoked");  
			}  
			}  */