import java.util.*;

class Solution {

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) return result;

        dfs(root, "", result);
        return result;
    }

    private void dfs(TreeNode node, String path, List<String> result) {

        if (node == null) return;

        // If leaf node
        if (node.left == null && node.right == null) {
            result.add(path + node.val);
            return;
        }

        // Continue building path
        String newPath = path + node.val + "->";

        dfs(node.left, newPath, result);
        dfs(node.right, newPath, result);
    }
}
