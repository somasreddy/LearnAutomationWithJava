package learn;

class Herit {
	String s="Parent";
	static String s1="Static Parent ";
	/*Inherit(int a){

}*/

}
class Inherit extends Herit{

	public static void main(String[] args) {
		Herit h=new Herit();
		String s1="Jai";
		System.out.print(h.s+"\n"+ s1);
	}
}

