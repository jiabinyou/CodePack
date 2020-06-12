package BinarySearch.Plan1;

public class FindMinimumInRotatedSortedArrayII {
    public int findMin(int[] nums) {
        //sanity check
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == nums[right]) {
                right = right - 1;         //一位一位dedup即可
            } else if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }
}
