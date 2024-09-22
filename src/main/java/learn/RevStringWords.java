package learn;

public class RevStringWords {
    static void reverseEachWordOfString(String ipString) {
	String[] words = ipString.split(" ");

	String IStringRev = "";
	;
	// reverse individual words of String
	for (String word : words) {
	    String revWord = "";
	    for (int j = word.length() - 1; j >= 0; j--) {
		revWord += word.charAt(j);
	    }

	    IStringRev += revWord + " ";
	}

	// Indexwise reverse String
	String rev = "";
	for (int i = words.length - 1; i >= 0; i--) {
	    rev += words[i] + " ";
	}
	// Full String Reverse
	String revString = "";
	String revString1 = "";
	char[] is = ipString.toCharArray();
	for (int i = is.length - 1; i >= 0; i--) {
	    revString1 += is[i];
	}
	for (int i = ipString.length() - 1; i >= 0; i--) {
	    revString += ipString.charAt(i);
	}

	System.out.println("inputString is: " + ipString);
	System.out.println("-------------------------");
	System.out.println("Individually Reversed String: " + IStringRev);
	System.out.println("-------------------------");
	System.out.println("Indexwise Reversed String: " + rev);
	System.out.println("-------------------------");
	System.out.println("Fully reversed String: " + revString);
	System.out.println("-------------------------");
	System.out.println("Fully reversed String: " + revString1);
	System.out.println("-------------------------");

	// return IStringRev;
    }

    public static void main(String[] args) {
	String s1 = "I evoL yM aidnI ";
	String s = "I hate to relocate from banglore with in India";
	reverseEachWordOfString("I Love My India");
	System.out.println(s);
	reverseEachWordOfString("Hello Good Morning India");

	if (s1.equals(s)) {
	    System.out.println("Test Pass");
	} else {
	    System.out.println("Test Fail");
	}

    }
}

//public class RevStringWords1 {
//    public static String reverseString(String ipString) {
//	String[] words = ipString.split(" ");
//
//	String IStringRev = "";
//
//	for (String word : words) {
//	    String revWord = "";
//	    for (int j = word.length() - 1; j >= 0; j--) {
//		revWord += word.charAt(j);
//	    }
//
//	    IStringRev += revWord + " ";
//	}
//	return IStringRev;
//    }
//
//    public static void main(String[] args) {
//	System.out.println(reverseString("how are you"));
//    }
//}
