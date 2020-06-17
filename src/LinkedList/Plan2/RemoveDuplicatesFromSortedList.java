package LinkedList.Plan2;

import LinkedList.ListNode;

/**
 语义：
 slow： excluding， last pos of res -->第一个一定保留，所以slow可放在head开始,语义是excluding，不需要dummy
 slow.next : first pos of unexplored area
 */
public class RemoveDuplicatesFromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        //sanity check
        if (head == null) {
            return head;
        }
        ListNode slow = head;
        while (slow.next != null) {
            if (slow.next.val != slow.val) {
                slow = slow.next;
            } else {
                slow.next = slow.next.next;
            }
        }
        return head; //同理，第一个一定保留，所以不会丢head
    }
}
