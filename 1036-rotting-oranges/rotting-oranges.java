import java.util.*;

class Solution {
    public int orangesRotting(int[][] grid) {
        
        int m = grid.length;
        int n = grid[0].length;
        
        Queue<int[]> queue = new LinkedList<>();
        int fresh = 0;
        
        // Step 1: Add all rotten oranges & count fresh ones
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }
        
        // If no fresh oranges
        if (fresh == 0) return 0;
        
        int minutes = 0;
        int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};
        
        // Step 2: BFS
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean rottenThisMinute = false;
            
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                
                for (int[] dir : directions) {
                    int newRow = curr[0] + dir[0];
                    int newCol = curr[1] + dir[1];
                    
                    if (newRow >= 0 && newRow < m &&
                        newCol >= 0 && newCol < n &&
                        grid[newRow][newCol] == 1) {
                        
                        grid[newRow][newCol] = 2;
                        queue.offer(new int[]{newRow, newCol});
                        fresh--;
                        rottenThisMinute = true;
                    }
                }
            }
            
            if (rottenThisMinute) minutes++;
        }
        
        return fresh == 0 ? minutes : -1;
    }
}