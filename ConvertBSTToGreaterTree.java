/**

递归/遍历：
时间复杂度都是O(n)；空间复杂度都是O(n).

递归，还是需要一个stack，不断的把右节点加进去

First, we initialize an empty stack and set the current node to the root. 
Then, we push all of the nodes along the path to the rightmost leaf onto the stack. 
Next, we visit the node on the top of our stack, and consider its left subtree. 

This is just like visiting the current node before recursing on the left subtree in the recursive solution. 
Eventually, our stack is empty and node points to the left null child of the tree's minimum value node, 
so the loop terminates.


*/

/**
Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.

Example:

Input: The root of a Binary Search Tree like this:
              5
            /   \
           2     13

Output: The root of a Greater Tree like this:
             18
            /   \
          20     13
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
    // 遍历
    public TreeNode convertBST(TreeNode root) {
        int sum = 0;
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<TreeNode>();

        while (!stack.isEmpty() || node != null) {
            /* push all nodes up to (and including) this subtree's maximum on
             * the stack. */
            while (node != null) {
                stack.push(node);
                node = node.right;
            }

            node = stack.pop();
            sum += node.val;
            node.val = sum;

            /* all nodes with values between the current and its parent lie in
             * the left subtree. */
            node = node.left;
        }

        return root;
    }
    
    // 递归
    private int sum = 0;
    public TreeNode convertBST1(TreeNode root) {
        if (root == null) return root;
        convertBST(root.right);
        sum += root.val;
        root.val = sum;
        convertBST(root.left);
        return root;
    }
}
