package learn;

class A1
{  
	A1 get()
	{
		return this;
	}  
}  
class B1 extends A1
{  
	B1 get()
	{
		return this;
	}  
	void message()
	{
		System.out.println("welcome to covariant return type");
	}
}
public class CovariantReturn 
{

	public static void main(String args[])
	{  
			
		new B1().get().message();
		
		new B1().message(); 
		
	}  
}  

