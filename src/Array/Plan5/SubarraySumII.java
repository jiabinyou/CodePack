package Array.Plan5;

public class SubarraySumII {
    /**PrefixSum类型
     input all positive-》preSum array有单调性，可直接用two pointers转换成two diff问题：
     求 #subsrray，which sum满足： start <= preSum[j] - preSum[i] <= end

     难点：因为要求subarray sum在某一区间[start, end],当满足start <= preSum[j] - preSum[i] <= end
     不能直接判断i, j之间所夹的(j -i)个pair都是符合要求的。因为以j为基准，i右移过程中sum会继续减小，
     可能就会小于start，就不再符合要求了。--》经典two diff不适用

     思路：e.g.要求subarray sum在区间[start,end]之间的subarray个数，
     我们可以用“经典two diff”分别求出subarray sum <= end 和 <= （start - 1）的，然后两者数目相减即可。
     */
    public int subarraySumII(int[] A, int start, int end) {
        // sanity check
        if (A == null || A.length == 0) {
            return 0;
        }
        if (start == end) { /**特殊corner case：如果区间头尾相同，那么就是没有区间可取*/
            return 0;
        }
        int[] preSum = new int[A.length + 1];
        for (int i = 0; i < A.length; i++) {
            preSum[i + 1] = preSum[i] + A[i];
        }
        //two diff on preSum[]
        int countEnd = twoDiff(preSum, end);
        int countStart = twoDiff(preSum, start - 1);
        return countEnd - countStart;
    }

    private int twoDiff(int[] preSum, int target) {
        int count = 0;

        int i = 0;
        int j = 1;  //arr[]如果一个数，preSum[]最少两个数，所以idx = 1一定有值的
        while (j < preSum.length) {
            int diff = preSum[j] - preSum[i];
            if (diff <= target) {
                count += j - i;
                j++;
            } else {
                i++;
            }
        }
        return count;
    }
}
