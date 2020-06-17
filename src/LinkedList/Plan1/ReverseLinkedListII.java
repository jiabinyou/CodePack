package LinkedList.Plan1;

import LinkedList.ListNode;

/**
 1 2 3 4 5

 dummy   1       2      3    4     5
 prev

 第一步：prev : skip (m - 1) steps
 1       2      3    4     5
 prev   cur  next

 第二步：iterative "insert" (n - m) times, 将next插入prev和cur之间
 1       3      2    4     5
 prev   next   cur

 update pointer：  //重点：prev位置不变，永远经next插入prev的后面一个
 1       3      2    4     5
 prev          cur  next

 1 4 3 2 5
 */
public class ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        //sanity check
        if (head == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        //"prev" move (m - 1) steps
        for (int i = 1; i <= m - 1; i++) {
            prev = prev.next;
            if (prev == null) {  //sanity check: m < len
                return head;
            }
        }

        //reverse (n - m) steps
        ListNode cur = prev.next;
        for (int i = 1; i<= n - m; i++) {
            ListNode next = cur.next;
            //插入，而不是reverse
            cur.next = next.next;
            next.next = prev.next;  //!!易错点：这里语义是：永远把next接到prev的下一个，而不是cur的位置，因为cur会变
            prev.next = next;
        }
        return dummy.next;
    }
}
