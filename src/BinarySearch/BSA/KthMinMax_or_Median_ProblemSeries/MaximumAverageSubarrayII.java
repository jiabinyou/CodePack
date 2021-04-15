package BinarySearch.BSA.KthMinMax_or_Median_ProblemSeries;

/**最优解法：BSA: Binary Search Application！！
 因为此时有negtive值，没办法保证f向右，s不向左，所以sf pointer不可用，用memo方法
 再思考：有没有合适的preSum memo ds可用？
 preSum memo ds要求：记录preSum val，记录idx，使得能够在每遍历一个新的j时候，能够找到
 和j配合的，max subarray sum。但是无论是用array，还是map等ds，对于每个j，仍然需要用O(N)
 复杂度去检查。最终时间复杂度仍然是o(n^2)级别。

 再思考有没有时间复杂度更加优秀的做法？-->BSA
 step 1：找固定区间
 left: min val in input
 right: max val in input
 mid: left + (right - left) / 2;

 step 2: 找mid判断的移动规则
 （inportant！！！BSA中即找helper functin逻辑：
 将mid类比成”实际res“，mid与实际res大：左移区间； mid与实际res小：右移区间）
 这道题要求：size >= k 的subarray的max average
 实际res：假设我们已知max average为maxAve，那么会发现对于原input中的每一个val，如果减去的是
 maxAve，那么每个值都是<0的。假设将这些差值相加组成的的MinusPreSum[]计算出来，一定也小于0.

 helper function:
 对于原input中的每一个val，如果减去的是mid, 并计算出MinusPreSum[]
 找到MinusPreSum[] array上size >=k 的preSum，判断与0的大小关系（即FFFFTTTT转折点）
 1.如果找到了>0的(返回true)，说明mid比实际中的maxAve偏小了，右移区间
 1.如果没找到>0的(返回false)，说明mid比实际中的maxAve偏大了，左移区间
 Coding 方法（难点！！）：在MinusPreSum[]做变形的FS-SW
 因为这里要求的是size >= k的，所以当i >= k时候，我们不应该remove slow，
 而应该remove [0, slow]区间之间最小的一个元素，这里对应的就是[0, slow]区间上MinusPreSum[]最小的值（minSlowSum）
 （因为最终要找的是是否有>0的结果，所以减去一个最小的，才最有可能找到>0的）
 */

/**
 * TC: O(nlog(MAX - MIN)) , n = nums.length, max = max(nums), min = min(nums)
 * */
public class MaximumAverageSubarrayII {
    public double maxAverage(int[] nums, int k) {
        // sanity check
        if (nums == null || k == 0 || nums.length < k) {
            return 0;
        }
        //find fix range for BS
        int min = nums[0];
        int max = nums[0];
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        return BSA((double)min, (double)max, nums, k);
    }

    //结束BS后，left和right的值无限趋近于mid，即最终的maxAve(误差在1e-5)，取left作为最终的maxAve即可
    //BS until find [average(left) works，left + 1e-5 does not work]
    private double BSA(double left, double right, int[] nums, int k) {
        while (left < right - 1e-5) { /**1e-5为临界误差*/
            double mid = left + (right - left) / 2;
            if (findSumOverZero(mid, nums, k)) {  //说明mid偏小，右移区间
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private boolean findSumOverZero(double mid, int[] nums, int k) {
        //calculate [num - mid] Array
        double[] nMM = new double[nums.length];  //int[] numMinusMid
        for (int i = 0; i < nums.length; i++) {
            nMM[i] = nums[i] - mid;
        }
        //变形FS-SW on int[] nMM： 利用preFixSum，在nMM[]上找：
        //是否有size>=k的，subarraySum >0的subarray
        //preSum只用一遍，可边走边算，不需单独DS浪费空间
        double sum = 0; //preSum in [0, fast]
        double slowSum = 0;  //preSum in [0, slow]
        double glbMinSlowSum = 0;  //preSum in [0, slow]中最小的一个值
        for (int fast = 0; fast < nMM.length; fast++) {
            sum += nMM[fast]; //add fast
            //remove minPreSum in [0, slow];
            if (fast >= k) {
                slowSum += nMM[fast - k];
                glbMinSlowSum = Math.min(glbMinSlowSum, slowSum);
            }
            //check res
            if (fast >= k - 1) {
                if (sum - glbMinSlowSum >= 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
