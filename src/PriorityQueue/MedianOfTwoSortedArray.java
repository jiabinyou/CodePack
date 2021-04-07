package PriorityQueue;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Sol1: maxHeap + minHeap
 * */
public class MedianOfTwoSortedArray {
    PriorityQueue<Double> l;
    PriorityQueue<Double> r;
    public double findMedianSortedArrays(int[] A, int[] B) {
        l = new PriorityQueue<>(11, Collections.reverseOrder());
        r = new PriorityQueue<>();
        for (int a : A) {
            addNum(a);
            balance();
        }
        for (int b : B) {
            addNum(b);
            balance();
        }
        //find median //和keep leftSize = rightSize + 1配合使用
        if (l.size() == r.size()) {
            return l.peek() / 2.0 + r.peek() / 2.0;
        } else {
            return l.peek();
        }
    }

    public void addNum(double num) {
        if (l.isEmpty() || num <= l.peek()) { /**这里小于或者小于等于都可以*/
            l.offer(num);
        } else {
            r.offer(num);
        }
        balance();
    }

    private void balance() {
        if (l.size() > r.size() + 1) {
            r.offer(l.poll());
        } else if (l.size() < r.size()) {
            l.offer(r.poll());
        }
    }
}

/**
 * Sol2. BS  （具体解析见ipad 高频题）
 * */
/**
    因为array是有序的，所以可以使用binary search --> 将找中位数转化成find kth smallest problem
    each round抛弃和保留的规则：
    The key point of this problem is to ignore half part of A and B each step recursively by comparing the median of remaining A and B:

 规则:
    if (aMid < bMid) Keep [aRight + bLeft]
    else Keep [bRight + aLeft]
    (这个规则需要证明！！)

    time = O(log(m + n))

*/

class MedianOfTwoSortedArraySol2 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int N1 = nums1.length;
        int N2 = nums2.length;
        if (N1 < N2) {  // Make sure A2 is the shorter one.
            return findMedianSortedArrays(nums2, nums1);
        }

        int left = 0;
        int right = N2 * 2;
        while (left <= right) {
            int mid2 = (left + right) / 2;   // Try Cut 2， 其实就是N2（但是不能直接写N2）
            int mid1 = N1 + N2 - mid2;  // Calculate Cut 1 accordingly

            // Get L1, R1, L2, R2 respectively + corner case（全部根据公式计算)
            //如果某一半不够长，导致mid1 == 0 或者mid2 == 0 --c>orner case，让L,R全部取不可能拿到的极值即可
            double L1 = (mid1 == 0) ? Integer.MIN_VALUE : nums1[(mid1-1)/2]; //L1 = (C1 - 1) / 2
            double L2 = (mid2 == 0) ? Integer.MIN_VALUE : nums2[(mid2-1)/2]; //L2 = (C2 - 1) / 2
            double R1 = (mid1 == N1 * 2) ? Integer.MAX_VALUE : nums1[(mid1)/2]; //r1 = c1 / 2
            double R2 = (mid2 == N2 * 2) ? Integer.MAX_VALUE : nums2[(mid2)/2]; //r2 = c2 / 2

            //统一通过移动c2来调整区间
            if (L1 > R2) { // A1's lower half is too big（L1大了）; need to move C1 left (C2 go right，因为C2大，即R2更大)
                left = mid2 + 1;
            } else if (L2 > R1) { // A2's lower half too big（L2大了）; need to move C2 go left.（C2变小，即L2变小）
                right = mid2 - 1;
            } else { // Otherwise, that's the right cut.
                //left切口附近元素中最大的，right切口附近元素中最小的，即可得我们要的meadian = （l + r） / 2
                return (Math.max(L1,L2) + Math.min(R1, R2)) / 2;
            }
        }
        return -1;
    }
}


