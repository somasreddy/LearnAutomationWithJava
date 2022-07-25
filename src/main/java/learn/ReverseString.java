package learn;

public class ReverseString {
	 //Recursive method to reverse string
	static String recursiveMethod(String str) {
        if ((null == str) || (str.length() <= 1))
        {
               return str;
        }

        return recursiveMethod(str.substring(1)) + str.charAt(0);
   }
    public static void main(String[] args){
        String str = "I am a Software Test Engineer";
 
        //1. Using StringBuffer Class
 
        StringBuffer sbf = new StringBuffer(str);
 
        System.out.println(sbf.reverse());    //Output : avaJyM
 
        //2. Using reverse iterative method
 
        char[] strArray = str.toCharArray();
 
        for (int i = strArray.length - 1; i >= 0; i--)
        {
            System.out.print(strArray[i]);    //Output : avaJyM
        }
 
        System.out.println();
 
        //3. Using Recursive Method
 
        System.out.println(recursiveMethod(str));//Output : avaJyM
        
        //4. Using Iteration  
        char ch[]=str.toCharArray();
        String str1="";
        for (int i =ch.length-1;i >= 0; i--)
        {
        	str1=str1+ch[i];
              //Output : avaJyM
        }
        System.out.println(str1); 
        //5.
        String str2="";
        for (int i=str.length()-1;i >= 0; i--)
        {
        	str2=str2+str.charAt(i);
        }
        System.out.println(str2); //Output : avaJyM
    
    
    
    
    
    
    
    
    
    }
}