package learn;


public class EncapsulateTest1 extends EncapsulateTest{
	
	  String print(int i) {
	  System.out.println("method with one arg and value: "+i); return "Success=10";
	  }
	 
	public static final void main (String[] args) 
	{
		EncapsulateTest1 t=new EncapsulateTest1();
		t.setStudentName("Simran");
		System.out.println(t.getStudentName());
		t.setStudentAge(33);
		System.out.println(t.getStudentAge());
		t.setStudentRoll("Actor");
		System.out.println(t.getStudentRoll());
	}
}
