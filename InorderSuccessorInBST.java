/**

给定了BST和p，找p的后继节点，两种情况：
    1. p有右孩子：后继为右孩子的最左孩子。
    2. p无右孩子：后继为离它最近的第一个大于它的父节点，如果它父节点没有大于它的，返回null
    
            15
           /  \
        10      20
       /  \    /  \
      8   12  17   25
     /    /   /     \
    6    11  16     27     6, 8, 10, 11, 12, 15, 16, 17, 20, 25, 27
    
root.val > p.val. 
    In this case, root can be a possible answer, 
    so we store the root node first and call it res. However, 
    we don’t know if there is anymore node on root's left that is larger than p.val. 
    So we move root to its left and check again.

root.val <= p.val. 
    In this case, root cannot be p's inorder successor, 
    neither can root's left child. So we only need to consider root's right child, 
    thus we move root to its right and check again.

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
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode res = null;
        while (root != null) {
            if (root.val <= p.val)
                root = root.right;
            else {
                res = root;
                root = root.left;
            }
        }
    return res;
    }
}
