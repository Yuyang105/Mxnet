/**
==============
    encode
通过层序遍历，使用Queue。其中，不再需要读当前queue.size()，记录每层大小。可以直接来。因为每层都是满的。


==============
    decode
也是逐层变回树，也需要Queue。从左到右加，每读出两个节点，就从Queue里poll出来他们的爸爸，同时塞他俩进去。

*/

/**
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

For example, you may serialize the following tree

    1
   / \
  2   3
     / \
    4   5
as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

 

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.

Credits:
Special thanks to @Louis1992 for adding this problem and creating all test cases.
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
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return null;
        
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur == null) {
                sb.append("null ");
                continue;
            }
            sb.append(cur.val + " ");
            queue.offer(cur.left);
            queue.offer(cur.right);
        }
        
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) return null;
        
        String[] temp = data.split(" ");
        TreeNode root = new TreeNode(Integer.parseInt(temp[0]));
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        for (int i = 1; i < temp.length; i++) {
            TreeNode cur = queue.poll();
            if (!temp[i].equals("null")) {
                cur.left = new TreeNode(Integer.parseInt(temp[i]));
                queue.offer(cur.left);
            }
            if (!temp[++i].equals("null")) {
                cur.right = new TreeNode(Integer.parseInt(temp[i]));
                queue.offer(cur.right);
            }
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
