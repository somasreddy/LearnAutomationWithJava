package learn;

import java.util.Scanner;

public class NumberPatterns {

	    public static void main(String[] args) 
	    {
	        Scanner sc = new Scanner(System.in);
	        //Taking rows value from the user
	        System.out.println("How many rows you want in this pattern?");
	        int r = sc.nextInt();
	        System.out.println("Here is your pattern....!!!");
	        
	      /*1
	    	1 2
	    	1 2 3
	    	1 2 3 4
	    	1 2 3 4 5*/
	       
	        for (int i = 1; i <= r; i++) 
	        {
	            for (int j = 1; j <= i; j++)
	            {
	                System.out.print(j+" ");
	            }
	            System.out.println();
	        }
            System.out.println("5---------------");
	        
	      /*1
	        2 2
	        3 3 3
	        4 4 4 4
	        5 5 5 5 5*/
	        
	        for(int i=1;i<=r;i++) 
	        {
	        	for(int j=1;j<=i;j++) 
	        	{
	        		System.out.print(i+" ");
	        	}
	        	System.out.println();
	        }
	        System.out.println("---------------");
	        
	      /*1
	        1 2
	        1 2 3
	        1 2 3 4
	        1 2 3 4 5
	        1 2 3 4 5 6
	        1 2 3 4 5 
	        1 2 3 4
	        1 2 3
	        1 2
	        1*/
	        
	        for(int i=1;i<=r;i++) {
	        	for(int j=1;j<=i;j++) {
	        		System.out.print(j+" ");
	        	}
	        	System.out.println();
	        }
	        for(int i=r-1;i>=0;i--) {
	        	for(int j=1;j<=i;j++) {
	        		System.out.print(j+" ");
	        	}
	        	System.out.println();
	        }
	        System.out.println("---------------");
	        
	      /*1 2 3 4 5 
	        1 2 3 4 
	        1 2 3 
	        1 2 
	        1 */
	        
	        for(int i=r;i>=1;i--) {
	        	for(int j=1;j<=i;j++) {
	        		System.out.print(j+" ");
	        	}
	        	System.out.println();
	        }
	        System.out.println("---------------");
	        
	      /*6 5 4 3 2 1
	        5 4 3 2 1
	        4 3 2 1
	        3 2 1
	        2 1
	        1*/
	        
	        for (int i = r; i >= 1; i--) 
	        {
	            for (int j = i; j >= 1; j--)
	            {
	                System.out.print(j+" ");
	            }
	             
	            System.out.println();
	        }
	        System.out.println("---------------");
	        
	    /*1 2 3 4 5
		  1 2 3 4
		  1 2 3
		  1 2
 		  1
		  1 2
		  1 2 3
		  1 2 3 4
		  1 2 3 4 5
	     */
	    
	    //Printing upper half of the pattern
	      for(int i=r;i>=1;i--) {
	    	  for(int j=1;j<=i;j++) {
	    		  System.out.print(j+" ");
	    	  }
	    	  System.out.println();
	      }
	    //Printing lower half of the pattern
	      for(int i=2;i<=r;i++) {
	    	  for(int j=1;j<=i;j++) {
	    		  System.out.print(j+" ");
	    	  }
	    	  System.out.println();
	      }
	      System.out.println("---------------");
	      
	/*     1 
	      2 2 
	     3 3 3 
	    4 4 4 4 
	   5 5 5 5 5 */
	      
	      int rc=1;
	      for (int i = r; i > 0; i--)
	        {
	            //Printing i spaces at the beginning of each row
	            for (int j = 1; j <= i; j++){
	                System.out.print(" ");
	            }
	            //Printing 'rowCount' value 'rowCount' times at the end of each row
	            for (int j = 1; j <= rc; j++){
	                System.out.print(rc+" ");
	            }
	            System.out.println();
	            //Incrementing the rowCount
	            rc++;
	        }
	      System.out.println("---------------");
	      
	    /*1
	      1 2 1
	      1 2 3 2 1
	      1 2 3 4 3 2 1
	      1 2 3 4 5 4 3 2 1
	      1 2 3 4 5 6 5 4 3 2 1
	      1 2 3 4 5 6 7 6 5 4 3 2 1*/
	    
	      for (int i = 1; i <= r; i++) 
	        {
	            //Printing first half of the row
	            for (int j = 1; j <= i; j++) 
	            { 
	                System.out.print(j+" "); 
	            }
	            //Printing second half of the row 
	            for (int k = i-1; k >= 1; k--)
	            {
	                System.out.print(k+" ");
	            }
	            System.out.println();
	        }
	      System.out.println("---------------");
	    
	      /*1
	        2 1
	        3 2 1
	        4 3 2 1
	        5 4 3 2 1
	        6 5 4 3 2 1
	        7 6 5 4 3 2 1*/
	        for (int i = 1; i <= r; i++) 
			{
				for (int j = i; j >= 1; j--)
				{
					System.out.print(j+" ");
				}
				
				System.out.println();
			}
	        System.out.println("---------------");
			
	      /*1 2 3
	        4 5
	        6
	        9 8 7*/
	        int h=9;
	        for(int i=1;i<=9;i++){
	        	if(i<=4||i>6) {
	        		if(i==4) {
	        			System.out.println(i);
	        		}
	        		
	        		else if(i>6){
	        		System.out.print(h-- +" ");
	        		}
	        		else {
	        		System.out.print(i+" ");
	        		}
	        	}
	        	else if(i == 5 || i == 6){
		        System.out.println(i);
		        }
	        }
	        System.out.println("\n---------------");
	    
	      /*1
	        2
	        1 2 3 2 1
	        4
	        5*/
	        
	        for(int i=1;i<=5;i++) {
//	        	System.out.println(i);
	        	if(i==3) {
	        		for(int j=1;j<=3;j++) {
	        			System.out.print(j+" ");
	        			if(j==3) {
	        				for(int k=2;k>0;k--) {
	        					System.out.print(k+" ");
	        				}
	        			}
	        		}
	        		System.out.println();
	        	}
	        	System.out.println(i);
	        }
	        System.out.println("---------------");
	     
	     /* 1 * * * * 
		    1 2 * * * 
			1 2 3 * * 
			1 2 3 4 * 
			1 2 3 4 5 */
	     
	     for(int i=1;i<=r;i++)  {
	        for(int j=1;j<=r;j++) {
	        	if(j<=i) {
	        	System.out.print(j+" ");
	        	}
	        	else {
	        	System.out.print("*"+" ");
	        	}
	        }
	        System.out.println();
	     }
	    System.out.println("---------------");
	    
	  /*1
	    2 1
	    3 2 1
	    4 3 2 1
	    5 4 3 2 1*/
	    
	    for (int i = 1; i <= r; i++) 
		{
			for (int j = i; j >= 1; j--)
			{
				System.out.print(j+" ");
			}
			
			System.out.println();
		}
	    System.out.println("---------------");
	    
	 /*12345
 		2345
  		 345
   		  45
    	   5
   		  45
  		 345
 		2345
	   12345*/
	    
	    for (int i = 1; i <= r; i++) 
		{
			//Printing i spaces at the beginning of each row
			for (int j = 1; j < i; j++) {
				System.out.print(" ");
			}
			//Printing i to rows value at the end of each row
			for (int j = i; j <= r; j++) { 
				System.out.print(j); 
			} 
			System.out.println(); 
		} 
		//Printing lower half of the pattern 
		for (int i = r-1; i >= 1; i--) 
		{
			//Printing i spaces at the beginning of each row
			for (int j = 1; j < i; j++) {
				System.out.print(" ");
			}
			//Printing j to rows value at the end of each row
			for (int j = i; j <= r; j++) {
				System.out.print(j);
			}
			System.out.println();
		}
		System.out.println("---------------");
	/*  1 2 3 4 5 
		 2 3 4 5 
		  3 4 5 
		   4 5 
		    5 
		   4 5 
		  3 4 5 
		 2 3 4 5 
		1 2 3 4 5 */    
	    
		for (int i = 1; i <= r; i++) 
		{
			//Printing i spaces at the beginning of each row
			for (int j = 1; j < i; j++) {
				System.out.print(" ");
			}
			//Printing i to rows value at the end of each row
			for (int j = i; j <= r; j++) { 
				System.out.print(j+" "); 
			} 
			System.out.println(); 
		} 
		//Printing lower half of the pattern 
		for (int i = r-1; i >= 1; i--) 
		{
			//Printing i spaces at the beginning of each row
			for (int j = 1; j < i; j++) {
				System.out.print(" ");
			}
			//Printing i to rows value at the end of each row
			for (int j = i; j <= r; j++) {
				System.out.print(j+" ");
			}
			System.out.println();
		}
		System.out.println("---------------");
	    
	  /*1
		10
		101
		1010
		10101*/
		
		for (int i = 1; i <= r; i++) 
		{
			for (int j = 1; j <= i; j++)
			{
				if(j%2 == 0)
				{
					System.out.print(0);
				}
				else
				{
					System.out.print(1);
				}
			}
			
			System.out.println();
		}
		System.out.println("---------------");
		
	  /*11111
		11122
		11333
		14444
		55555*/

		for (int i = 1; i <= r; i++) 
		{
			for (int j = 1; j <= r-i; j++)
			{
				System.out.print(1);
			}
			
			for (int j = 1; j <= i; j++)
			{
				System.out.print(i);
			}
			
			System.out.println();
		}
		System.out.println("---------------");
	    
	  /*10000
		02000
		00300
		00040
		00005*/
		
		for (int i = 1; i <= r; i++) 
		{
			for (int j = 1; j <= r; j++)
			{
				if(i == j)
				{
					System.out.print(i);
				}
				else
				{
					System.out.print(0);
				}
			}
			
			System.out.println();
		}
		System.out.println("---------------");
		
	  /*1 
		2 6 
		3 7 10 
		4 8 11 13 
		5 9 12 14 15*/
		
		for (int i = 1; i <= r; i++) 
		{
			int num = i;
			
			for (int j = 1; j <= i; j++) 
			{
				System.out.print(num+" ");
				
				num = num+r-j;
			}
			
			System.out.println();
		}
		System.out.println("---------------");
		
		
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	        //Close the resources
	        sc.close();
	    }
	}

