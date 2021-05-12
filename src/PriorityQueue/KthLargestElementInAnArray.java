package PriorityQueue;

import java.util.Comparator;
import java.util.PriorityQueue;

/** Sol1.可以使用"相反"PQ，求kth largest用minHeap （就是top k问题）
 * 【TC:nlogk, SC:o(k)】
 * 因为第k大的，就是找top k元素，最后留在minHeap中顶部元素，即为第k大的
 *
 * 过例子：
 * [3,2,1,5,6,4]， 2
 * [3, 2, 1, 5, 6, 4]                     minHeap（size = 2）
 *  i                                       3
 *     i                                    2,3
 *       i                                  1,2,3 ->2,3
 *           i                              2,3,5 ->3,5
 *              i                           3,5,6 ->5,6
 *                  i                       4,5,6 ->5,6 ->return 5
 *
 */

public class KthLargestElementInAnArray {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.add(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.poll();
    }
}

 /**Sol2.quick select
 quick select （结束时候两边子问题不一定sorted）                   【TC:最优o(n),最差o(n^2), SC:o(1)】     ---------》input unsorted最优解法
 *       找kth largest《-》找第n-k个smallest element，对input使用partition，
 *       最终stop at the point where the pivot itself is the kth largest element
 */
class KthLargestElementInAnArraySol2 {
    public int kthLargestElement(int k, int[] nums) {
        if (nums == null || nums.length == 0 || k < 1 || k > nums.length){
            return -1;
        }
        return partition(nums, 0, nums.length - 1, nums.length - k);
    }

    private int partition(int[] nums, int start, int end, int k) {
        if (start >= end) {
            return nums[k];
        }
        int left = start;
        int right = end;
        int pivot = nums[(start + end) / 2];
        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
                left++;
            }
            while (left <= right && nums[right] > pivot) {
                right--;
            }
            if (left <= right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }

        if (k <= right) {
            return partition(nums, start, right, k);
        }
        if (k >= left) {
            return partition(nums, left, end, k);
        }
        return nums[k];
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
