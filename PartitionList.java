/**
Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

For example,
Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5.


*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }
        
        ListNode leftDummy = new ListNode(0);
        ListNode rightDummy = new ListNode(0);
        ListNode l = leftDummy, r = rightDummy;
        
        while (head != null) {
            if (head.val < x) {
                l.next = head;
                l = head;
            }
            else {
                r.next = head;
                r = head;
            }
            head = head.next;
        }
        r.next = null;
        l.next = rightDummy.next;
        return leftDummy.next;
    }
}
