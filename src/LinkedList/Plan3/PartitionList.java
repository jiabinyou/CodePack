package LinkedList.Plan3;

import LinkedList.ListNode;

public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        //sanity check
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        ListNode dummySmall = new ListNode(0); //all nodes < x
        ListNode small = dummySmall;
        ListNode dummyLarge = new ListNode(0); //all nodes >= x
        ListNode large = dummyLarge;

        while (cur != null) {
            if (cur.val < x) {
                small.next = cur;
                small = small.next;
            } else {
                large.next = cur;
                large = large.next;
            }
            cur = cur.next;
        }
        small.next = dummyLarge.next;
        large.next = null;
        return dummySmall.next;
    }
}
