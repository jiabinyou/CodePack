package LinkedList.Plan1;

import LinkedList.ListNode;

/**
 1 -> 2 -> 3 -> Null
 每两个看成一组：
 1 -> 2
 cur  Next

 2 -> 1 -> null
 next = cur.next;
 cur.next.next = cur;
 cur.next = null;

 TC:O(N)
 SC:0(N)
 */

/**过例子
 1 -> 2 -> 3 -> Null
 首先根据ListNode newHead = reverse(head.next);，会一直recursion到底层，这个例子中
 recursion到head = 3，返回newHead = 3，再往回一步一步看。

 head  next     Linkedlist                        newHead
  3             1 -> 2 -> 3 -> Null                  3

  2     3       1 -> 2 -> 3 -> Null

        变成：   3 -> 2 -> null
                1 -> 2                               3

 1     2                                             3
        变成：   3 -> 2 ->1 -> null
 */
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

    public static void main(String[] args) {
        ReverseLinkedList obj = new ReverseLinkedList();
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        one.next = two;
        two.next = three;
        three.next = null;
        obj.reverseList(one);
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
