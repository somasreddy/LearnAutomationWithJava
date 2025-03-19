package Test;

import java.util.*;

public class FirstNonRepChar {
    public static void main(String[] args) {
        String input = "swiss";
        System.out.println("First Non-Repeating Character: "+findFirstNonRepeatingChar(input));
        System.out.println("First Non-Repeating Character: "+findFirstNonRepChar(input));
        System.out.println(findFirstNonRepCha(input));
    }

    public static char findFirstNonRepCha(String s) {
        // Loop through the string and check the count of each character
        for (int i = 0; i < s.length(); i++) {
            int count = 0;
            // Check for the current character throughout the entire string
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    count++;
                }
            }
            // If the character appears only once, return it
            if (count == 1)
                return s.charAt(i);
        }
        return '\0'; // If no non-repeating character is found
    }

    public static char findFirstNonRepeatingChar(String input) {
        HashMap<Character, Integer> map = new HashMap<>();
        Queue<Character> queue = new LinkedList<>();
        for(char ch : input.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0)+1);
            queue.offer(ch);
            // Remove repeated characters from the queue
            while(!queue.isEmpty()&&map.get(queue.peek())>1) {
                queue.poll();
            }
        }
        return queue.isEmpty() ? '_' : queue.peek(); // Return first non-repeating character
    }

    public static char findFirstNonRepChar(String input) {
        Map<Character, Integer> charCountMap = new LinkedHashMap<>();
        // Fill the map with character counts
        for(char ch : input.toCharArray()) {
            charCountMap.put(ch, charCountMap.getOrDefault(ch, 0)+1);
        }
        // Find the first character with count 1
        for(Map.Entry<Character, Integer> entry : charCountMap.entrySet()) {
            if(entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return '_'; // Return underscore if no non-repeating character is found
    }
}
