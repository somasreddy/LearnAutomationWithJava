package Test;



import java.util.Scanner;
public class ArrayMain {
    public static void main(String[] args) {
        Scanner input =new Scanner(System.in);
        
        System.out.println("ENter number of Elements to be given in a array ");
        int n=input.nextInt();
        int[] arr=new int[n];
        System.out.println("ENter Elements of the array ");
        for(int j=0;j<n;j++){
            arr[j]=input.nextInt();
        }
        System.out.println("Array Before Sorting ");
        for(int i=0;i<arr.length;i++){
         System.out.print(arr[i]+" ");
             
         }
        System.out.println();
        
        for(int i=0;i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++){
                if(arr[i]>arr[j]){
                    arr[i]=arr[i]+arr[j];
                    arr[j]=arr[i]-arr[j];
                    arr[i]=arr[i]-arr[j];
                }
            }
        }
         for(int i=0;i<arr.length;i++){
         System.out.print(arr[i]+" ");
             
         }
         
     input.close();   
    }
}

