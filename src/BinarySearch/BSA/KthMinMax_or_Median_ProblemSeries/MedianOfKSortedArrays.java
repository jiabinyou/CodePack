package BinarySearch.BSA.KthMinMax_or_Median_ProblemSeries;

/**静态输入，输入具有sorted特性，但没有index能直接取到median元素-->最优化算法是BSA*/

/**Sol.BSA
 *          step 1：找固定区间
 *                  left:  所有list中min
 *                  right: 所有list中max
 *                  mid: left + (right - left) / 2;
 *
 *         step 2: 找mid判断的移动规则
 *                 找median转化成找"K"th smallest element （BSA解法），唯一的就是外面套一层马甲
 *                 总元素数量奇数： K = len / 2
 *                 总元素数量奇数： K1 = len / 2  , K2 = len / 2 + 1
 *                               最终median = (K1th smallest + K2th smallest) / 2
 *
 *                 剩下的就是经典的"find Kth smallest element"的BSA解法
 * TC: O(log(max - min))
 * SC: O(1)
 */
 public class MedianOfKSortedArrays {
    public double findMedian(int[][] nums) {
        // sanity check
        if (nums == null || nums.length == 0) {
            return 0.0;
        }
        //确定BSA的固定区间
        int left = Integer.MAX_VALUE;
        int right = Integer.MIN_VALUE;
        int len = 0;  //totalLen
        for (int[] arr : nums) {     //因为每个arr都是sorted，所以0位置是最小，len - 1是最大
            if (arr == null || arr.length == 0) { /**易错：要提前检查，否则NPE*/
                continue;
            }
            left = Math.min(left, arr[0]);
            right = Math.max(right, arr[arr.length - 1]);
            len += arr.length;
        }
        //将find median转换成find kth smallest问题，并调用BSA解决
        if (len == 0) {    //corner case
            return 0;
        }
        //len奇数，median转换成find (len / 2)h smallest问题
        /**注意：k是第几个，而不是index，所以奇数返回len / 2 + 1, 而不是直接len / 2*/
        if (len % 2 == 1) {
            return kthSmallest(nums, len / 2 + 1, left, right);
        } else {
            //len偶数，median转换成(K1th smallest + K2th smallest) / 2 ， K1 = len / 2  , K2 = len / 2 + 1
            int k1 = len / 2;
            int k2 = len / 2 + 1;
            return kthSmallest(nums, k1, left, right) / 2.0 + kthSmallest(nums, k2, left, right) / 2.0;  //分别除是为了防溢出
        }
    }

    //经典BSA find Kth smallest element (in multiple array,其实和in matrix那题是一样的)
    private int kthSmallest(int[][] nums, int k, int left, int right) {
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (countSmallerOrEqual(nums, mid) >= k) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (countSmallerOrEqual(nums, left) >= k) {  //仍然按照移动规则，左边的如果符合要求，就是第k小，再去看右边
            return left;
        }
        return right;
    }

    /**calculate the #element in nums <=target, 这题唯一和matrix那题不一样就是，这里要每个array数再叠加*/
    private int countSmallerOrEqual(int[][] nums, int target) {
        int count = 0;
        for (int[] arr : nums) {
            count += countInSingleArr(arr, target);
        }
        return count;
    }

    /**calculate the #element in single array <=target*/
    /**难点：这里当然可以遍历一遍，但因为每个arr都是sorted，所以可使用binary search优化 -》
     转化成找smallestLargerThan Target（不能带有equal to），则最后找到的那个index就是长度*/
    private int countInSingleArr(int[] arr, int target) {
        if (arr == null || arr.length == 0) { /**易错：要提前检查，否则NPE*/
            return 0;
        }
        int left = 0;
        int right = arr.length - 1;
        if (arr[right] <= target) {
            return arr.length;
        }
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {  //小了
                left = mid;
            } else if (arr[mid] < target) { //小了
                left = mid;
            } else {     //符合条件，找有没有更小的
                right = mid;
            }
        }
        if (arr[left] > target) {
            return left;
        }
        return right;
    }
}


