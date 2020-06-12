package BinarySearch.Plan1;

/**
 * clr:
 *  1.if (nums == null || nums.length == 0)
 *  2.如果所有元素均大于等于target怎么办？
 *     这里默认返回index = 0
 *
 * traget范围：
 * 有两种选择：
 * 1.找rightmost pos 元素 < target      --> 显然这种更简便
 * 2.找leftMost pos元素 = 或者 > target
 */

public class SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        //sanity check
        if (nums == null || nums.length == 0) {
            return -1;
        }
        //BS
        int left = 0;
        int right = nums.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        if (nums[right] < target) {
            return right + 1;
        } else if (nums[left] < target) {
            return left + 1;
        }
        return 0;
    }
}
