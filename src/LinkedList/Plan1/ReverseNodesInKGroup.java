package LinkedList.Plan1;

import LinkedList.ListNode;

public class ReverseNodesInKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        //sanity check
        if (head == null) {
            return head;
        }

        //从head开始移动k-1步，停留在curTail
        ListNode cur = head;
        for (int i = 1; i <= k - 1; i++) { //等价于for (int i = 0; i < k - 1; i++)
            cur = cur.next;
            if (cur == null) {   //sanity check:when k < len
                return head;
            }
        }

        //recursion reverse k group
        ListNode curHead = head;
        ListNode curTail = cur;
        ListNode nextHead = cur.next;

        //recursion
        ListNode newHead = reverseKGroup(nextHead, k);
        //induction rule
        reverseSingle(curHead, nextHead); //reverse当前层
        curHead.next = newHead;  //处理层与层之间

        return curTail;
    }

    private ListNode reverseSingle(ListNode head, ListNode tail) {
        //base case
        if (head.next == null || head.next == tail) {
            return head;
        }
        ListNode newHead = reverseSingle(head.next, tail);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}

/**将curHead, curTail简化以后
 * //recursion reverse k group
 *         ListNode nextHead = cur.next;                   //row1
 *
 *         //recursion
 *         ListNode newHead = reverseKGroup(nextHead, k);  //row2
 *         //induction rule
 *         reverseSingle(head, nextHead); //reverse当前层
 *         head.next = newHead;  //处理层与层之间
 *最后将row1和row2合并，得到最后代码
 */

//class Solution {
//    public ListNode reverseKGroup(ListNode head, int k) {
//        //sanity check
//        if (head == null) {
//            return head;
//        }
//
//        //从head开始移动k-1步，停留在curTail
//        ListNode cur = head;
//        for (int i = 1; i <= k - 1; i++) { //等价于for (int i = 0; i < k - 1; i++)
//            cur = cur.next;
//            if (cur == null) {   //sanity check:when k < len
//                return head;
//            }
//        }
//
//        //recursion reverse k group
//        ListNode nextHead = cur.next;
//
//        //induction rule
//        reverseSingle(head, nextHead); //reverse当前层
//        head.next = reverseKGroup(nextHead, k); //recursion + 处理层与层之间
//
//        return cur;
//    }
//
//    private ListNode reverseSingle(ListNode head, ListNode tail) {
//        //base case
//        if (head.next == null || head.next == tail) {
//            return head;
//        }
//        ListNode newHead = reverseSingle(head.next, tail);
//        head.next.next = head;
//        head.next = null;
//        return newHead;
//    }
//}
