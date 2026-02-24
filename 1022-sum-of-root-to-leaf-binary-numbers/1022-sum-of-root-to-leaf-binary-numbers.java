/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    
    public int sumRootToLeaf(TreeNode root) {
        // DFS traversal start karte hain root se, jisme shuruwat mein sum 0 hoga
        return calculatePathSum(root, 0);
    }

    private int calculatePathSum(TreeNode node, int currentPathVal) {
        // Base case: agar child exist nahi karta, toh sum 0 return hoga
        if (node == null) {
            return 0;
        }

        // Logic: Naya bit add karne ke liye purane number ko 2 se multiply kiya
        // Equivalent to bitwise logic: currentPathVal = (currentPathVal << 1) | node.val;
        currentPathVal = (currentPathVal * 2) + node.val;

        // Leaf node check: Agar dono children null hain, toh path complete ho gaya
        if (node.left == null && node.right == null) {
            return currentPathVal;
        }

        // Left aur Right branches ke liye recursion call
        int leftBranchSum = calculatePathSum(node.left, currentPathVal);
        int rightBranchSum = calculatePathSum(node.right, currentPathVal);

        // Dono taraf ke valid paths ka total sum return kar do
        return leftBranchSum + rightBranchSum;
    }
}