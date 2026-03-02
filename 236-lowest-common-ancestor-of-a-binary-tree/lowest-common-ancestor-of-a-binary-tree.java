// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class Solution {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        
        // Base case
        if (root == null || root == p || root == q) {
            return root;
        }

        // Search in left subtree
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        
        // Search in right subtree
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // If both left and right are not null, root is LCA
        if (left != null && right != null) {
            return root;
        }

        // Otherwise return non-null value
        return (left != null) ? left : right;
    }
}