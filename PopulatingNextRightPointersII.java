/**
Follow up for problem "Populating Next Right Pointers in Each Node".

What if the given tree could be any binary tree? Would your previous solution still work?

Note:

You may only use constant extra space.
For example,
Given the following binary tree,
         1
       /  \
      2    3
     / \    \
    4   5    7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL
*/

/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    // level order
    public void connect(TreeLinkNode root) {
        TreeLinkNode level_start = null, pre = null, cur = root;
        while (cur != null) {
            while (cur != null) {
                if (cur.left != null) {
                    if (pre != null)
                        pre.next = cur.left;
                    else
                        level_start = cur.left;
                    pre = cur.left;
                }
                if (cur.right != null) {
                    if (pre != null)
                        pre.next = cur.right;
                    else
                        level_start = cur.right;
                    pre = cur.right;
                }
                cur = cur.next;
            }
            cur = level_start;
            level_start = null;
            pre = null;
        }
    }
}
