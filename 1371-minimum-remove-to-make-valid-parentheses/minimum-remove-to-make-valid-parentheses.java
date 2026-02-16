import java.util.*;

class Solution {
    public String minRemoveToMakeValid(String s) {
        
        StringBuilder sb = new StringBuilder(s);
        Stack<Integer> stack = new Stack<>();
        
        // First pass: remove invalid ')'
        for (int i = 0; i < sb.length(); i++) {
            char ch = sb.charAt(i);
            
            if (ch == '(') {
                stack.push(i);
            } 
            else if (ch == ')') {
                if (!stack.isEmpty()) {
                    stack.pop();   // valid pair
                } else {
                    sb.setCharAt(i, '#');  // mark invalid ')'
                }
            }
        }
        
        // Second pass: remove remaining '('
        while (!stack.isEmpty()) {
            sb.setCharAt(stack.pop(), '#');
        }
        
        // Build final string
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) != '#') {
                result.append(sb.charAt(i));
            }
        }
        
        return result.toString();
    }
}
