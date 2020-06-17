package LinkedList.Plan2;

import LinkedList.ListNode;

/**
 目标：fast走完时，让slow停留在倒数第N个node的前一个(所以slow和fast之间index相差n+1 -->相差n步)
 做法：slow，fast从dummy开始，fast先走N步

 语义：fast.next: first pos of unexplored area
 */
public class RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //sanity check
        if (head == null || n == 0) {
            return head;
        }
        //step 1: fast先走N步
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
            if (fast == null) {  //sanity check: when n < len
                return head;
            }
        }

        //step 2: fast和slow同移动，fast走完时slow停留在倒数第N个node的前一个
        ListNode slow = dummy;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;

        //return
        return dummy.next;
    }
}
