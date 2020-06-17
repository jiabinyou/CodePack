package LinkedList.Plan5;

import LinkedList.ListNode;

public class OddEvenLinkedList {
    public ListNode oddEvenList(ListNode head) {
        //sanity check
        if (head == null || head.next == null) {
            return head;
        }
        boolean isOdd = true;
        ListNode dummyOdd = new ListNode(0);
        ListNode odd = dummyOdd;
        ListNode dummyEven = new ListNode(0);
        ListNode even = dummyEven;

        while (head != null) {
            if (isOdd) {
                odd.next = head;
                odd = odd.next;
                isOdd = false;
            } else {
                even.next = head;
                even = even.next;
                isOdd = true;

            }
            head = head.next;
        }

        odd.next = dummyEven.next;
        even.next = null;
        return dummyOdd.next;
    }
}
