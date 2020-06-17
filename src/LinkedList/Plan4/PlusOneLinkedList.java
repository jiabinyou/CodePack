package LinkedList.Plan4;

import LinkedList.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 Add Two Numbers II变式：
 把S2变成1计算即可
 */
public class PlusOneLinkedList {
    public ListNode plusOne(ListNode head) {
        //sanity check
        if (head == null) {
            return head;
        }

        Deque<Integer> s1 = new ArrayDeque<>();
        while (head != null) {
            s1.push(head.val);
            head = head.next;
        }

        int prevCarrier = 0;
        int nextCarrier = 0;
        int sum = 0;

        ListNode dummy = new ListNode(0);
        int count = 0;  //记录加第几位信息
        while (!s1.isEmpty() || prevCarrier != 0) {
            sum = (s1.isEmpty() ? 0 : s1.peek()) +
                    (count == 0 ? 1 : 0) +
                    prevCarrier;
            nextCarrier = sum / 10;
            ListNode newHead = new ListNode(sum % 10);
            newHead.next = dummy.next;  //新的node插入在dummy后面
            dummy.next = newHead;

            count++;
            prevCarrier = nextCarrier;
            if(!s1.isEmpty()) {s1.pop();}
        }
        return dummy.next;
    }
}
