package LinkedList.Plan4;

import LinkedList.ListNode;

public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //sanity check
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        int prevCarrier = 0;
        int nextCarrier = 0;
        int sum = 0;

        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        while (l1 != null || l2 != null || prevCarrier != 0) {
            sum = (l1 == null ? 0 : l1.val) +
                    (l2 == null ? 0 : l2.val) +
                    prevCarrier;
            nextCarrier = sum / 10;
            prev.next = new ListNode(sum % 10);
            prev = prev.next;

            prevCarrier = nextCarrier;
            l1 = l1 == null ? l1 : l1.next;
            l2 = l2 == null ? l2 : l2.next;
        }
        return dummy.next;
    }
}
