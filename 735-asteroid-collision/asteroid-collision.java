import java.util.*;

class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        for (int asteroid : asteroids) {
            boolean destroyed = false;

            while (!stack.isEmpty() && asteroid < 0 && stack.peek() > 0) {
                if (stack.peek() < -asteroid) {
                    stack.pop(); // top explodes
                    continue;
                } else if (stack.peek() == -asteroid) {
                    stack.pop(); // both explode
                }
                destroyed = true;
                break;
            }

            if (!destroyed) {
                stack.push(asteroid);
            }
        }

        // Convert stack to array
        int[] result = new int[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }

        return result;
    }
}
