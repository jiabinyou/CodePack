package Array.Plan1;

public class TwoSumDifferenceEqualsToTarget {
/**sorted ->two pointers, find num1 + target = num2,pointer同向而行*/
    /**易错点：
     1.i == j时候，要再次移动指针（因为要防止diff = 0,输出时候i == j）
     2.题目只要求：diff of num1 and num2 equal to target, 并没有要求diff顺序，意味着
     num1 - num2 == diff / num2 - num1 == diff都符合，所以实际上是最经典two diff的变形
     要分成diff > 0 或者 diff < 0来处理。diff = 0放在任一个情况处理均可。
     */
    public int[] twoSum7(int[] nums, int target) {
        // sanity check
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int i = 0;
        int j = 1;
        while (j < nums.length) {
            if (target >= 0) {
                if (nums[i] + target == nums[j] ) {
                    return new int[]{nums[i], nums[j]};
                } else if (nums[i] + target < nums[j]) {
                    i++;
                } else {
                    j++;
                }
                if (i == j) { //配合diff = 0使用
                    j++;
                }
            } else {
                if (nums[j] + target == nums[i] ) {
                    return new int[]{nums[i], nums[j]};
                } else if (nums[j] + target < nums[i]) {
                    j++;
                } else {
                    i++;
                }
            }
        }
        return new int[0];
    }
}
