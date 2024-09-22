package learn;

public class Alphabets_PrintASCII {
    public static void main(String[] args) {
	char ch = 0;
	while (ch < 150) {
	    System.out.print(ch + "=" + (int) ch + " ");
	    ch++;
	}
	
	int i=0;
	while (i<200) {
	    System.out.print(i + "=" + (char) i + " ");
	    i++;
	}
    }
}
