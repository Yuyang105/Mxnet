/**
Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:
Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.
*/

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
    public int closestValue(TreeNode root, double target) {
        TreeNode cur = root;
        int res = cur.val;
        while (cur != null) {
            if (Math.abs(res - target) > Math.abs(cur.val - target))
                    res = cur.val;
            if (cur.val > target)
                cur = cur.left;
            else 
                cur = cur.right;
        }
        return res;
    }
}
