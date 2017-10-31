/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        if (root == null) return null;
        
        List<Double> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        
        q.add(root);
        while (!q.isEmpty()) {
            int n = q.size();
            double sum = 0.0;
            for (int i = 0; i < n; i++) {
                TreeNode current = q.poll();
                sum += current.val;
                if (current.left != null) q.add(current.left);
                if (current.right != null) q.add(current.right);
            }
            res.add(sum / n);
        }
        return res;
    }
}
