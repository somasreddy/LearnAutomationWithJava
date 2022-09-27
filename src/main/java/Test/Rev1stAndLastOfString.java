package Test;

public class Rev1stAndLastOfString {
	
	public static void main(String[] args) {
		
		String str="Java is Object Oriented Programming Language";
		String[] sArr=str.split(" ");
		String revStr="";
		for(int i=0;i<sArr.length;i++) {
			char[] swap=sArr[i].toCharArray();
			String swapStr="";
			int lastIndex=swap.length-1;
			char temp=swap[0];
			swap[0]=swap[lastIndex];
			swap[lastIndex]=temp;	
			for(int j=0;j<lastIndex+1;j++) {
				swapStr+=swap[j];
			}
			revStr+=swapStr+" ";
		}
		System.out.println(revStr);
	}

}
