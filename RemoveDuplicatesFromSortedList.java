/**
Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.

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
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        
        ListNode current = head;
        
        while (current != null) {
            if (current.next != null && current.val == current.next.val) {
                ListNode dummy = current.next;
                while (dummy.next != null && dummy.next.val == current.val) {
                    dummy = dummy.next;
                }
                current.next = dummy.next;
            }
            current = current.next;
        }
        return head;
    }
}
