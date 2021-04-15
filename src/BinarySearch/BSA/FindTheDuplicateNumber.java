package BinarySearch.BSA;

/**Sol2.BSA
 *          step 1：找固定区间
 *             left:  1
 *             right: N = arr.length - 1
 *             mid: left + (right - left) / 2;
 *
 *         step 2: 找mid判断的移动规则
 *             （important！！！BSA中即找helper function逻辑：
 *             将mid类比成”实际res“，mid与实际res大：左移区间； mid与实际res小：右移区间）
 *
 *             假设我们已经知道重复的元素大小为E（mid）， 那么arr中<= E的元素的个数一定是E个
 *             所以我们可以使用helper function去计算矩阵中小于等于mid的元素数量
 *
 *             --》以此才能得出正确的区间移动规则：
 *             小于等于mid的元素数量 == mid   ->  说明mid可能就在mid元素上，或者还是偏小了，可以继续右移区间，
 *             小于等于mid的元素数量 <  mid   --> 说明mid偏小了，右移区间
 *             小于等于mid的元素数量 >  mid   --> 说明mid偏大了(即此时mid一定>=dup)，左移区间
 *
 *
 * TC: O(nlogn)
 * */
public class FindTheDuplicateNumber {
    public int findDuplicate(int[] nums) {
        // sanity check
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 1;
        int right = nums.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (countSmallerOrEqual(nums, mid) <= mid) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (countSmallerOrEqual(nums, left) > left) {
            return left;
        }
        return right;
    }

    private int countSmallerOrEqual(int[] nums, int mid) {
        int count = 0;
        for (int num : nums) {
            if (num <= mid) {
                count++;
            }
        }
        return count;
    }
}
