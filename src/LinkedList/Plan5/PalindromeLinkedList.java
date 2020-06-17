package LinkedList.Plan5;

import LinkedList.ListNode;

/**
 * recursion解法：
 * 1 2 3 4 3 2 1
 * 利用的是recursion走到最后一层，相当于Linkedlist的最后一个元素，释放stack的过程，相当于从右往左遍历
 *
 * 使用全局变量cur来从左往右遍历（全局不受recursion影响）
 * 使用recursion来从右往左遍历
 * 相当于recursion从底层往高层构建为(利用panlidrome定义：从左往右 == 从右往左 来解题)
 * 1                                   （cur）
 *                  1                   (subHead)
 *
 * 1  2                                （cur）
 *               2  1                   (subHead)
 */
public class PalindromeLinkedList {
    ListNode cur;
    public boolean isPalindrome(ListNode head) {
        //sanity check
        if (head == null) {
            return true;
        }
        cur = head;
        return recursion(head);
    }

    private boolean recursion(ListNode subHead) {
        //base case
        if (subHead == null) {
            return true;
        }
        //recursion
        boolean lastRes = recursion(subHead.next);
        //induction rule
        if (lastRes && cur.val == subHead.val) {
            cur = cur.next;
            return true;
        }
        return false;
    }
}

//iterative Sol
//class Solution {
//    public boolean isPalindrome(ListNode head) {
//        //sanity check
//        if (head == null || head.next == null) {
//            return true;
//        }
//        ListNode mid = findMid(head);
//        ListNode newHead = mid.next;
//        mid.next = null;
//        ListNode reversedHead = reverse(newHead);
//        return isSame(head,  reversedHead);
//    }
//
//    private ListNode findMid(ListNode head) {
//        ListNode slow = head;
//        ListNode fast = head;
//        while (fast.next != null && fast.next.next != null) {
//            slow = slow.next;
//            fast = fast.next.next;
//        }
//        return slow;
//    }
//
//    private ListNode reverse(ListNode head) {
//        //base case
//        if (head.next == null) {
//            return head;
//        }
//        ListNode newHead = reverse(head.next);
//        head.next.next = head;
//        head.next = null;
//        return newHead;
//    }
//
//    private boolean isSame(ListNode one, ListNode two) {
//        while (one != null && two != null) {
//            if (one.val != two.val) {
//                return false;
//            }
//            one = one.next;
//            two = two.next;
//        }
//        return true;
//    }
//}
