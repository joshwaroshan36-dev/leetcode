import java.util.*;

class Solution {

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();

        dfs(root, targetSum, currentPath, result);

        return result;
    }

    private void dfs(TreeNode node, int remainingSum,
                     List<Integer> currentPath,
                     List<List<Integer>> result) {

        if (node == null) return;

        // Add current node to path
        currentPath.add(node.val);
        remainingSum -= node.val;

        // If leaf and sum matches
        if (node.left == null && node.right == null && remainingSum == 0) {
            result.add(new ArrayList<>(currentPath)); // copy
        }

        // Recurse
        dfs(node.left, remainingSum, currentPath, result);
        dfs(node.right, remainingSum, currentPath, result);

        // Backtrack
        currentPath.remove(currentPath.size() - 1);
    }
}
