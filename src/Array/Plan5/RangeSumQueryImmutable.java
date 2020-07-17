package Array.Plan5;

/**
 计算preSum的两种方法：
 int[] prefixSum = new int[nums.length + 1];
 for (int i = 0; i < nums.length; i++) {
 prefixSum[i + 1] = prefixSum[i] + nums[i];
 }

 《==》

 int[] prefixSum = new int[nums.length + 1];
 for (int i = 0; i < prefixSum.length; i++) {
 prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
 }
 */
public class RangeSumQueryImmutable {
    int[] preSum;
    public void NumArray(int[] nums) {
        preSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
    }

    public int sumRange(int i, int j) {
        return preSum[j + 1] - preSum[i];
    }
}


