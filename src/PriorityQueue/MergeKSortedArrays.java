package PriorityQueue;

/**
 * Sol: k pointer, 谁小移谁
 * N is 全部数字的总数.
 * k is the number of arrays.
 * TC:O(N *K)
 * SC:O(K)
 * --》但是题目要求Do it in O(N log k).
 * */

import java.util.PriorityQueue;

/**Sol2.minHeap， 原理：k pointers, 谁小移谁
 * 初始将所有数组的首个元素入minHeap, 并记录入堆的元素是属于哪个数组的.
 * 每次取出堆顶元素, 并放入该元素所在数组的下一个元素.
 * TC: minHeap长度为K，共有N个数字，所以是O(NLOGK)
 * sc:O(K)
 *
 *
 *
 * */
public class MergeKSortedArrays {
    public int[] mergekSortedArrays(int[][] arrays) {
        if (arrays == null || arrays.length == 0) {
            return new int[0];
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < arrays.length; i++) {
            for (int j = 0; j < arrays[i].length; j++) {
                pq.add(arrays[i][j]);
            }
        }

        int[] result = new int[pq.size()];
        int idx = 0;
        while (pq.size() > 0) {
            result[idx++] = pq.poll();
        }

        return result;
    }
}


