package LinkedList.Plan3;

import LinkedList.ListNode;

/**
sanity:
        1.head == null
        2.head.next == null
        3.k == 0
        4. k >= len ！！

 iterative解法，可以使用dummy
        用一个slow pointer，找到len，计算真正K（< len的）
        fast先走，从dummy开始跳跃ksteps，保证s,f到结尾时候，之间index相差k，再重连pointer即可

        最后重连pointer即可
 */

/**
 * Trick：
 *      当k >= len时候，就应该实施 k % len, 而不是k > len时候
 *      因为假设len = 5, k = 5, 如果直接计算，最后fast先走k步，直接停留在最后一个，slow仍然在dummy
 *      此时slow.next = null;会导致最后输出是空list[]
 * 优化写法：
 *      反正k < len时候， k % len值不变，不如一起全部k % len处理
 */
public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        //sanity check
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        //find len, get real k
        ListNode cur = head;
        int len = 0;
        while (cur != null) {
            cur = cur.next;
            len++;
        }
        k = k >= len ? k % len : k; //不如直接写k %= len;

        //fast pointer从dummy开始跳跃(len - k) steps,重连pointer
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        fast.next = dummy.next;
        dummy.next = slow.next;
        slow.next = null;
        return dummy.next;
    }
}
