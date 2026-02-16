import java.util.*;

class Solution {
    public int[] finalPrices(int[] prices) {
        
        int n = prices.length;
        int[] result = prices.clone();   // copy original prices
        
        Stack<Integer> stack = new Stack<>();  // stores indices
        
        for (int i = 0; i < n; i++) {
            
            // If current price is <= price at stack top,
            // apply discount
            while (!stack.isEmpty() && prices[i] <= prices[stack.peek()]) {
                int index = stack.pop();
                result[index] -= prices[i];
            }
            
            stack.push(i);
        }
        
        return result;
    }
}
