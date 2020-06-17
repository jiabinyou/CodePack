package LinkedList.Plan2;

import LinkedList.ListNode;

/**
 语义：
 slow： including， last pos of res -->第一个不一定保留，需要dummy，slow需从dummy开始，语义including
 slow.next = fast : first pos of unexpolored area
 */
public class RemoveDuplicatesFromSortedListII {
    public ListNode deleteDuplicates(ListNode head) {
        //sanity check
        if (head == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;

        while (slow.next != null) {
            ListNode fast = slow.next;
            //fast跳过dup，同在last pos of one group
            while (fast.next != null && fast.next.val == fast.val) {
                fast = fast.next;
            }

            //如果刚刚group有dup，则不保留；如果只出现一次，则保留
            if (slow.next != fast) {  //fast移动了超过一次
                slow.next = fast.next;
            } else {
                slow = slow.next;
            }
        }
        return dummy.next;
    }
}
