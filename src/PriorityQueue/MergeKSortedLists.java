package PriorityQueue;
import LinkedList.ListNode;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;


/**
 * k pointers, 谁小移谁
 * 可以使用minHeap管理K个linkedlist head，当做是k pointer，
 * 方便每次poll出下一个值最小的pointer
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


