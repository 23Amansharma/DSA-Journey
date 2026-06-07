/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
      public TreeNode createBinaryTree(int[][] descriptions) {
     HashMap<Integer, TreeNode> map = new HashMap<>();
     HashSet<Integer> children = new HashSet<>();

    for (int[] d : descriptions) {
        int parentVal = d[0], childVal = d[1], isLeft = d[2];
        
        // Node create karna hai  agar pehle se exist nahi karta to 
        map.putIfAbsent(parentVal, new TreeNode(parentVal));
        map.putIfAbsent(childVal, new TreeNode(childVal));
        
        // Parent Child link setup kiya
        if (isLeft == 1) map.get(parentVal).left = map.get(childVal);
        else map.get(parentVal).right = map.get(childVal);
        
        children.add(childVal);
    }

    // Root wo node hai jo kabhi kisi ka child nahi bna hai ya nhi hai 
    // traverse kiya if not contain to return value
    for (int val : map.keySet()) {
        if (!children.contains(val)) return map.get(val);
    }
    return null;
}
     
    }