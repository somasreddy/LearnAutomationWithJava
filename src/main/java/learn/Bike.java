package learn;

 class Honda  
 {
	 final int TANK_CAP=100;
	 final void run()
	  {
		 System.out.println("running...");
	  }  
  }  
	class Bike extends Honda
	{  
	   public static void main(String args[])
	   {  
	    Bike b=new Bike();  
	    b.run();
	    System.out.println(b.TANK_CAP);
	   }  
	}
