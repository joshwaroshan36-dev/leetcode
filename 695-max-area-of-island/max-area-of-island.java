class Solution {

    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int area = dfs(grid, i, j);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        return maxArea;
    }

    private int dfs(int[][] grid, int row, int col) {
        
        // Boundary + water check
        if (row < 0 || col < 0 ||
            row >= grid.length || col >= grid[0].length ||
            grid[row][col] == 0) {
            return 0;
        }

        // Mark visited
        grid[row][col] = 0;

        // Count current + 4 directions
        return 1
            + dfs(grid, row + 1, col)
            + dfs(grid, row - 1, col)
            + dfs(grid, row, col + 1)
            + dfs(grid, row, col - 1);
    }
}