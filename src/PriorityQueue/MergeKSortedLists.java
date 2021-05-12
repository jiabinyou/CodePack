package PriorityQueue;
import LinkedList.ListNode;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;


/**Sol2.minHeap， 原理：k pointers, 谁小移谁
 * 初始将所有数组的首个元素入minHeap, 并记录入堆的元素是属于哪个数组的.
 * 每次取出堆顶元素, 并放入该元素所在数组的下一个元素.
 * TC: minHeap长度为K，假设共有N个数字，是O(NLOGK)
 * sc:O(K)
 * */
public class MergeKSortedLists {
    public ListNode mergeKLists(List<ListNode> lists) {
        //sanity check
        if (lists == null || lists.size() == 0) {
            return null;
        }
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(11, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode l1, ListNode l2) {
                if (l1.val == l2.val) {
                    return 0;
                }
                return l1.val < l2.val ? -1 : 1;
            }
        });

        //init:put all head into minHeap
        for (ListNode head : lists) {
            if (head != null) {
                minHeap.offer(head);
            }
        }
        //k pointer, 谁小移谁
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        while (!minHeap.isEmpty()) {
            ListNode nextMin = minHeap.poll();
            //connect Res
            prev.next = nextMin;
            prev = prev.next;
            //将nextMin的下一个元素塞入minHeap，即更新k pointer的过程
            if (nextMin.next != null) {
                minHeap.offer(nextMin.next);
            }
        }
        //return
        return dummy.next;
    }
}


