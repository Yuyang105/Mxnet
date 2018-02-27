/**
Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Note: Do not modify the linked list.

Follow up:
Can you solve it without using extra space?
*/

/**

找环的起点：

X     a     Y         b
·-----------·-------·
            |       |
            |       · Z
          c |       |
            |       |
            ·-------·
            
如图，出发点为X，环的起点为Y，Fast和Slow相遇于Z。期间距离为a, b, c.

Fast速度是Slow的两倍 => Fast走过的距离是Slow的两倍 
                   => a + b + c + b = 2 (a + b)
                   => a = c

由于[X到Y的距离a]等于[Z到Y的距离c]，再派一个Slow走X出发，原Slow继续走，它俩相逢点就是Y。


*/


/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                ListNode res = head;
                while (res != slow) {
                    res = res.next;
                    slow = slow.next;
                }
                return res;
            }
        }
        return null;
    }
}
