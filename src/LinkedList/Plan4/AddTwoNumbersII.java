package LinkedList.Plan4;

import LinkedList.ListNode;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 易错点：
 * 1.Deque中记录的是Integer，因为不需要保留指针信息，都是重新连接的
 * 2.每一位所得新的node应该插入dummy后面，而不是接在prev后面
 */
public class AddTwoNumbersII {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //sanity check
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        Deque<Integer> s1 = new ArrayDeque<>();
        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        Deque<Integer> s2 = new ArrayDeque<>();
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }

        int prevCarrier = 0;
        int nextCarrier = 0;
        int sum = 0;

        ListNode dummy = new ListNode(0);
        while (!s1.isEmpty() || !s2.isEmpty() || prevCarrier != 0) {
            sum = (s1.isEmpty() ? 0 : s1.peek()) +
                    (s2.isEmpty() ? 0 : s2.peek()) +
                    prevCarrier;
            nextCarrier = sum / 10;
            ListNode newHead = new ListNode(sum % 10);
            newHead.next = dummy.next;  //新的node插入在dummy后面
            dummy.next = newHead;

            prevCarrier = nextCarrier;
            if(!s1.isEmpty()) {s1.pop();}
            if(!s2.isEmpty()) {s2.pop();}
        }
        return dummy.next;
    }
}
