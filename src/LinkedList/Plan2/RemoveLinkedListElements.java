package LinkedList.Plan2;

import LinkedList.ListNode;

/**
 语义：
 slow： including， last pos of res
 slow.next : first pos of unexplored area
 */
public class RemoveLinkedListElements {
    public ListNode removeElements(ListNode head, int val) {
        //sanity check
        if (head == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy; //slow从dummy开始，表示语义是including的last pos of res

        while (slow.next != null) {
            if (slow.next.val != val) {
                slow = slow.next;
            } else {
                slow.next = slow.next.next;
            }
        }
        return dummy.next;
    }
}
