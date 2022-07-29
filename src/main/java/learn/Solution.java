package learn;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {

    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter input array element seperated with spaces :");
        br.readLine();
        int output=0;
        String[] input = br.readLine().split(" ");
        for(String value:input)
        {
            output += Integer.parseInt(value);
        }
        System.out.println(output);
    }
}
