package LinkedList.Plan1;

import LinkedList.ListNode;

public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        //sanity check
        if (head == null) {
            return head;
        }
        //base case
        if (head.next == null) {
            return head;
        }
        //recursion
        ListNode newHead = reverseList(head.next);    //newHead = reverse(nextHead)
        head.next.next = head;
        head.next = null;
        return newHead;                               //return curTail
    }
}

//iterative Sol
//class Solution {
//    public ListNode reverseList(ListNode head) {
//        //sanity check
//        if (head == null) {
//            return head;
//        }
//        //base case
//        if (head.next == null) {
//            return head;
//        }
//        ListNode prev = null;
//        while (head != null) {
//            ListNode next = head.next;
//            head.next = prev;
//
//            prev = head;
//            head = next;
//        }
//        return prev;
//    }
//}
