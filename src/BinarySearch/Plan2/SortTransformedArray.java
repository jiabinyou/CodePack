package BinarySearch.Plan2;
/**
 *   a = 0时候，res从左往右，从右往左填写都行，关键是要和startIdx的处理一致
 *   下面code中，a = 0时候startIdx初始化在末尾，即从右向左填写，所以while循环中a = 0也和a > 0放在一起处理
 */
public class SortTransformedArray {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        //sanity check
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int left = 0;
        int right = nums.length - 1;
        int startIdx = a >= 0 ? nums.length - 1 : 0;  //开始填写res的位置,a=0位置与下面条件处理必须一致
        int[] res = new int[nums.length];
        while (left <= right) {
            int leftVal = calFunc(a, b, c, nums[left]);
            int rightVal = calFunc(a, b, c, nums[right]);
            if (a >= 0) {  //res从右往左填写，谁大移谁
                if (leftVal > rightVal) {
                    res[startIdx--] = leftVal;
                    left++;
                } else {
                    res[startIdx--] = rightVal;
                    right--;
                }
            } else {    //res从左往右填写，谁小移谁
                if (leftVal < rightVal) {
                    res[startIdx++] = leftVal;
                    left++;
                } else {
                    res[startIdx++] = rightVal;
                    right--;
                }
            }
        }
        return res;
    }

    private int calFunc(int a, int b, int c, int num) {
        return a * num * num + b * num + c;
    }
}
