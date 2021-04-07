package BinarySearch.BSA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**Sol1.BF*/
public class ThekthsubarrayLintCode {
    public long thekthSubarray(int[] a, long k) {
        // sanity check
        if (a == null || a.length == 0) {
            return 0;
        }
        //int[] preSum = new int[a.length + 1]；
        long[] preSum = new long[a.length + 1];
        for (int i = 0; i < a.length; i++) {
            preSum[i + 1] = preSum[i] + a[i];
        }
        List<Long> subArraySum = new ArrayList<>();
        /**!!记录所有subarray sum的方法：*/
        for (int i = 0; i < preSum.length; i++) {
            for (int j = i + 1; j < preSum.length; j++) {
                subArraySum.add(preSum[j] - preSum[i]);
            }
        }
        Collections.sort(subArraySum);
        int idx = (int) (k > subArraySum.size() ? k % subArraySum.size() : k);
        return subArraySum.get(idx - 1);
    }
}

/**SOL2.BS*/
class Solution1 {
    public long thekthSubarray(int[] a, long k) {
        // sanity check
        if (a == null || a.length == 0) {
            return 0;
        }
        //calculate preSum
        long[] preSum = new long[a.length + 1];
        for (int i = 0; i < a.length; i++) {
            preSum[i + 1] = preSum[i] + a[i];
        }
        //bs
        long left = Integer.MAX_VALUE;
        long right = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            left = Math.min(left, a[i]);
        }
        right = preSum[a.length];

        long count = 0;
        while (left < right) {
            long mid = left + (right - left) / 2;
            count = getSmaller(preSum, mid);  //# <= mid
            /**如果sum小于等于mid的子数组个数是<k的，说明mid太小，那么答案在[ mid + 1 : right ] 里,否则就是在[ left : mid ]里*/
            if (count >= k) {   /**难点：不可输出，要一直把bs做完，因为mid不一定是subarray sum，要继续go left找第一次kth出现地方，且mid可能是*/
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    //找# of subarray sum <= mid, 同向而行two pointer
    private long getSmaller(long[] preSum, long mid) {
        long count = 0;
        int i = 0;
        int j = 1;
        while (j < preSum.length) {
            while (preSum[j] - preSum[i] > mid) {  /**j固定，一开始i小，所以diff肯定值大*/
                i++;
            }
            count += j - i;
            j++;
        }
        return count;
    }
}
