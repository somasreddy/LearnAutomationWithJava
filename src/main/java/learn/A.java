package learn;
public class A {  
	public static void main(String[] args) {
		String s="LOTUS";
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