package Array.Plan4;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Sol1.
 * Naive O(n^3) solution
 */
public class OneThreeTwoPattern {
    public boolean find132pattern(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] < nums[k] && nums[k] < nums[j]) return true;
                }
            }
        }
        return false;
    }
}

/**
 * Sol2.中心开花
 * Improved O(n^2) solution
 *
 * 观察：once the first two numbers nums[i] and nums[j] are fixed,
 *      we are up to find the third number nums[k] which will be within the range (nums[i], nums[j])
 *
 * 固定维度j，那么有了fixed nums[j],此时找到对应i，是的132成立可能性最大，那么i即为[0,j）的最小值
 * 此时只要在（j, length - 1)中间有nums[k] 在 nums[i]和nums[j]之间即可
 */
class Sol1321b{
    public boolean find132pattern(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < nums.length; j++) {
            min = Math.min(nums[j], min);
            if (min == nums[j]) {
                continue;
            }

            for (int k = nums.length - 1; k > j; k--) {
                if (min < nums[k] && nums[k] < nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }
}

/**
 * Sol3. r to l DMS : mono Stack
 *
 * O(N) O(N)
 */
class Sol1322 {
    public boolean find132pattern(int[] nums) {
        //sanity check
        if (nums == null || nums.length < 3) {
            return false;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        int two = Integer.MIN_VALUE;  //two是132中的2
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] < two) {
                return true;
            }
            while (!stack.isEmpty() && nums[i] > stack.peek()) {
                two = stack.pop();
            }
            stack.push(nums[i]);
        }
        return false;
    }
}