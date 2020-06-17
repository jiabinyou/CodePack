package LinkedList.Plan5;

import LinkedList.ListNode;

public class ReorderList {
    public void reorderList(ListNode head) {
        //sanity check
        if (head == null || head.next == null) {
            return;
        }
        ListNode mid = findMid(head);
        ListNode newHead = mid.next;
        mid.next = null;
        ListNode reversedHead = reverse(newHead);
        mergeList(head,  reversedHead);
    }

    private ListNode findMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode reverse(ListNode head) {
        //base case
        if (head.next == null) {
            return head;
        }
        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    private void mergeList(ListNode l1, ListNode l2) {
        ListNode one = l1;
        ListNode two = l2;
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;  //从l1开始

        while (one != null && two != null) {
            cur.next = one;
            one = one.next;
            cur.next.next = two;
            two = two.next;
            cur = cur.next.next;
        }
        //post-processing
        if (one != null) {
            cur.next = one;
        }
        if (two != null) {
            cur.next = two;
        }
    }
}
