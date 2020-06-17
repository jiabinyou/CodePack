package LinkedList.Plan1;
import LinkedList.ListNode;

public class SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        //sanity check
        if (head == null) {
            return head;
        }
        //base case
        if (head.next == null) {
            return head;
        }
        ListNode curHead = head;
        ListNode curTail = head.next;
        ListNode nextHead = head.next.next;

        ListNode newHead = swapPairs(nextHead); //recursion
        curTail.next = curHead;  //induction rule（当前层）
        curHead.next = newHead;  //induction rule(层与层之间)

        return curTail;
    }
}

//iterative sol
//class Solution {
//    public ListNode swapPairs(ListNode head) {
//        //sanity check
//        if (head == null || head.next == null) {
//            return head;
//        }
//
//        ListNode dummy = new ListNode(0);
//        dummy.next = head;
//
//        ListNode prev = dummy;
//        ListNode curHead = head;
//
//        while (curHead != null && curHead.next != null) {
//            ListNode curTail = curHead.next;
//            ListNode nextHead = curHead.next.next;
//
//            prev.next = curTail;
//            curTail.next = curHead;
//            curHead.next = nextHead;
//
//            prev = curHead;
//            curHead = curHead.next;
//        }
//        return dummy.next;
//    }
//}