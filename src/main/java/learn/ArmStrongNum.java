package learn;

import java.util.Scanner;

public class ArmStrongNum {
	
public static void main(String[] args) {
	Scanner sc= new Scanner(System.in);
	System.out.println("Enter the Num: ");
	int n=sc.nextInt();
	int num,sum=0,temp;
	temp=n;
	while(temp>0) {
		num=temp%10;
		temp=temp/10;
		sum=sum+(num*num*num);
	}
	if(sum==n) {
		System.out.println("The Given Number "+n+" is an ArmStrongNumber");
	}
	else {
		System.out.println("The Given Number "+n+" is not an ArmStrongNumber");
	}
	System.out.println("------------");
	
	//ArmStrongNumbers in the given range 
	int num1,sum1,temp1;
	String armStrongs="";
	for(int i=1;i<=n;i++) {
		temp1=i;
		sum1=0;
		while(temp1>0) {
			num1=temp1%10;
			temp1=temp1/10;
			sum1=sum1+(num1*num1*num1);
		}
		if(sum1==i) {
			armStrongs=armStrongs+sum1+" ";
		}
	}
	System.out.println("The ArmStrongNumbers between 1 and "+n+" are:\n"+armStrongs);
	

	sc.close();
}
}
