package learn;
public class StarPatterns {
 static	int n=5;
// static int i,j,k;
public static void main(String[] args) {

/*	
 
* 
  * 
    * 
      * 
        * 

*/

for(int i=1;i<=n;i++) {
	for(int j=1;j<=n;j++) {
		if(i==j) {
	System.out.print("* ");
		}
		else {
			System.out.print("  ");
		}
	}
	System.out.println();
}
System.out.println("-------------------");

/*	

        * 
      *   
    *     
  *       
*         

*/

for(int i=1;i<=n;i++) {
	for(int j=1;j<=n;j++) {
		if((i+j)==n+1) {
	System.out.print("* ");
		}
		else {
			System.out.print("  ");
		}
	}
	System.out.println();
}
System.out.println("-------------------");

/*	

*       *
  *   *
    * 
  *   * 
*       * 

*/

for(int i=1;i<=n;i++) {
	for(int j=1;j<=n;j++) {
		if(i==j||(i+j)==n+1) {
	System.out.print("* ");
		}
		else {
			System.out.print("  ");
		}
	}
	System.out.println();
}
	
System.out.println("-------------------");	

/*	

    *     
    *     
* * * * * 
    *     
    *     

*/

for(int i=1;i<=n;i++) {
	for(int j=1;j<=n;j++) {
		if(i==(n/2)+1||j==(n/2)+1) {
	System.out.print("* ");
		}
		else {
			System.out.print("  ");
		}
	}
	System.out.println();
}
	
System.out.println("-------------------");	

/*

* * * * *
*       *
*       *
*       *
* * * * * 

*/	

for(int i=1;i<=n;i++) {
	for(int j=1;j<=n;j++) {
		if(i==1||j==1||j==n||i==n) {
	System.out.print("* ");
		}
		else {
			System.out.print("  ");
		}
	}
	System.out.println();
}
		
System.out.println("-------------------");

/*	

* * * * * 
*   *   * 
* * * * * 
*   *   * 
* * * * * 

*/

for(int i=1;i<=n;i++) {
	for(int j=1;j<=n;j++) {
		if(i==1||j==1||j==n||i==n||i==(n/2)+1||j==(n/2)+1) {
	System.out.print("* ");
		}
		else {
			System.out.print("  ");
		}
	}
	System.out.println();
}
	
System.out.println("-------------------");	

/*

* * * * * 
* *   * * 
*   *   * 
* *   * * 
* * * * * 

*/	

for(int i=1;i<=n;i++) {
	for(int j=1;j<=n;j++) {
		if(i==1||j==1||j==n||i==n||i==j||i+j==n+1) {
	System.out.print("* ");
		}
		else {
			System.out.print("  ");
		}
	}
	System.out.println();
}
		
System.out.println("-------------------");

/*

        * 
      * * 
    * * * 
  * * * * 
* * * * * 

*/
for(int i=1;i<=n;i++){
	for(int j=n-i;j>=1;j--){
	System.out.print("  ");
	}
  for(int k=1;k<=i;k++) {
	System.out.print("* ");
  }
  System.out.println();
}

System.out.println("-------------------");

/*

* * * * * 
  * * * *
    * * *
      * *
    	* 
		
*/

for(int i=n;i>=1;i--) {
	for(int j=n-i;j>=1;j--) {
		System.out.print("  ");
	}
	for(int k=1;k<=i;k++) {
		System.out.print("* ");
	}
	System.out.println();
}

System.out.println("-------------------");

/* 

        * 
      * *
    * * * 
  * * * * 
* * * * * 
  * * * *
    * * *
      * *
        *
		
*/
for(int i=1;i<=n;i++){
	for(int j=n-i;j>=1;j--){
		System.out.print("  ");
	}
	for(int k=1;k<=i;k++) {
		System.out.print("* ");
	}
	System.out.println();
}
for(int i=n-1;i>=1;i--) {
	for(int j=n-i;j>=1;j--) {
		System.out.print("  ");
	}
	for(int k=1;k<=i;k++) {
		System.out.print("* ");
	}
	System.out.println();
}

System.out.println("-------------------");

/* 

* * * * * 
  * * * * 
    * * * 
      * * 
        * 
      * * 
    * * * 
  * * * * 
* * * * * 

*/
for(int i=n;i>=1;i--) {
	for(int j=n-i;j>=1;j--) {
		System.out.print("  ");
	}
	for(int k=1;k<=i;k++) {
		System.out.print("* ");
	}
	System.out.println();
}
for(int i=2;i<=n;i++){
	for(int j=n-i;j>=1;j--){
		System.out.print("  ");
	}
	for(int k=1;k<=i;k++) {
	System.out.print("* ");
	}
  System.out.println();
}
System.out.println("-------------------");

/*
 
* 
* *
* * *
* * * *
* * * * *

*/
for(int i=1;i<=n;i++) {
	for(int j=1;j<=i;j++) {
		System.out.print("* ");
	}
	System.out.println();
}
System.out.println("-------------------");

/*
 
* * * * *
* * * *
* * *
* *
* 
 
*/
for(int i=1;i<=n;i++) {
	for(int j=n;j>=i;j--) {
		System.out.print("* ");
	}
	System.out.println();
}

/*for(int i=0;i<n;i++) {
	for(int j=0;j<n-i;j++) {
		System.out.print("* ");
	}
	System.out.println();
}*/
System.out.println("-------------------");
/*

* * * * * 
* * * * 
* * * 
* * 
* 
* *
* * *
* * * *
* * * * *

*/
for(int i=1;i<=n;i++) {
	for(int j=n;j>=i;j--) {
		System.out.print("* ");
	}
	System.out.println();
}
for(int i=2;i<=n;i++) {
	for(int j=1;j<=i;j++) {
		System.out.print("* ");
	}
	System.out.println();
}
System.out.println("-------------------");
/*

* 
* * 
* * * 
* * * * 
* * * * * 
* * * * 
* * * 
* * 
* 

*/

for(int i=1;i<=n;i++) {
	for(int j=1;j<=i;j++) {
		System.out.print("* ");
	}
	System.out.println();
}
for(int i=2;i<=n;i++) {
	for(int j=n;j>=i;j--) {
		System.out.print("* ");
	}
	System.out.println();
}

System.out.println("-------------------");
/*

	    * 
      * * * 
    * * * * * 
  * * * * * * * 
* * * * * * * * * 

*/

for(int i=1; i<=n; i++) {
	for(int j=i; j<n; j++) {
		System.out.print("  ");
	}
	for(int k=1; k<(2*i); k++) {
		System.out.print("* ");
	}
	System.out.println("");
}

System.out.println("-------------------");

/*

* * * * * * * * * 
  * * * * * * * 
    * * * * * 
      * * * 
        *  

*/

for(int i=n;i>=1;i--) {
	for(int j=n;j>i;j--) {
		System.out.print("  ");
	}
	for(int k=1;k<(i*2);k++) {
		System.out.print("* ");
	}
	System.out.println();
}

System.out.println("-------------------");

/*

        * 
      * * * 
    * * * * * 
  * * * * * * * 
* * * * * * * * *
  * * * * * * * 
    * * * * * 
      * * * 
	    *
		
*/

for(int i=1; i<=n; i++) {
	for(int j=n-1; j>=i; j--) {
		System.out.print("  ");
	}
	for(int k=1; k<=(2*i-1); k++) {
		System.out.print("* ");
	}
	System.out.println("");
}
for(int i=n-1;i>=1;i--) {
	for(int j=n;j>i;j--) {
		System.out.print("  ");
	}
	for(int k=1;k<(i*2);k++) {
		System.out.print("* ");
	}
	System.out.println();
}
System.out.println("-------------------");

/*
 
* * * * * * * * * 
  * * * * * * * 
    * * * * * 
      * * * 
        * 
      * * * 
    * * * * * 
  * * * * * * * 
* * * * * * * * * 

*/

for(int i=n;i>=1;i--) {
	for(int j=n;j>i;j--) {
		System.out.print("  ");
	}
	for(int k=1;k<(i*2);k++) {
		System.out.print("* ");
	}
	System.out.println();
}
for(int i=2; i<=n; i++) {
	for(int j=n-1; j>=i; j--) {
		System.out.print("  ");
	}
	for(int k=1; k<=(2*i-1); k++) {
		System.out.print("* ");
	}
	System.out.println("");
}

System.out.println("-------------------");

/*

    * 
   * * 
  * * * 
 * * * * 
* * * * * 

*/

for(int i=1;i<=n;i++) {
	for(int j=n-1;j>=i;j--) {
		System.out.print(" ");
	}
	for(int k=1;k<=i;k++) {
		System.out.print("* ");
	}
	System.out.println();
}

System.out.println("-------------------");

/*

* * * * * 
 * * * * 
  * * * 
   * * 
    * 

*/

for(int i=n;i>=1;i--) {
	for(int j=n-1;j>=i;j--) {
		System.out.print(" ");
	}
	for(int k=i;k>=1;k--) {
		System.out.print("* ");
	}
	System.out.println();
}

System.out.println("-------------------");

/*

    * 
   * * 
  * * * 
 * * * * 
* * * * * 
 * * * * 
  * * * 
   * * 
    * 

*/
for(int i=1;i<n;i++) {
	for(int j=n-1;j>=i;j--) {
		System.out.print(" ");
	}
	for(int k=1;k<=i;k++) {
		System.out.print("* ");
	}
	System.out.println();
}
for(int i=n;i>=1;i--) {
	for(int j=n-1;j>=i;j--) {
		System.out.print(" ");
	}
	for(int k=i;k>=1;k--) {
		System.out.print("* ");
	}
	System.out.println();
}

System.out.println("-------------------");

/*

* * * * * 
 * * * * 
  * * * 
   * * 
    * 
   * * 
  * * * 
 * * * * 
* * * * * 

*/

for(int i=n;i>=1;i--) {
	for(int j=n-1;j>=i;j--) {
		System.out.print(" ");
	}
	for(int k=i;k>=1;k--) {
		System.out.print("* ");
	}
	System.out.println();
}
for(int i=2;i<=n;i++) {
	for(int j=n-1;j>=i;j--) {
		System.out.print(" ");
	}
	for(int k=1;k<=i;k++) {
		System.out.print("* ");
	}
	System.out.println();
}

System.out.println("-------------------");
}
}
