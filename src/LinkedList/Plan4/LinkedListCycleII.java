package LinkedList.Plan4;

import LinkedList.ListNode;

public class LinkedListCycleII {
    public ListNode detectCycle(ListNode head) {
        //sanity check
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode newSlow = head;
                while (newSlow != slow) {
                    newSlow = newSlow.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }
}
