package Array.Plan4;

import java.util.Arrays;

/**
 * Sol1.DP
 * 找longest length of increasing subsequece
 *
 * O(n ^ 2)
 */
class SolITS1 {
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                if (dp[i] == 3) {
                    return true;
                }
            }
        }
        return false;
    }
}
/**
 * Sol2.中心开花
 * for each j：
 * 往左边找有没有更小的
 * 往右边找有没有更大的
 *
 *  O(n ^ 2)
 */
class IncreasingTripletSubsequence {
    public boolean increasingTriplet(int[] nums) {
        //sanity check
        if (nums == null || nums.length < 3) {
            return false;
        }
        for (int j = 0; j < nums.length; j++) {
            boolean lFind = false;;  //if find small at left of j
            for (int i = 0; i < j; i++) {
                if (nums[i] < nums[j]) {
                    lFind = true;
                    break;
                }
            }

            boolean rFind = false; //if find larger at right of j
            for (int i = j + 1; i < nums.length; i++) {
                if (nums[i] > nums[j]) {
                    rFind = true;
                    break;
                }
            }

            if (lFind && rFind) {
                return true;
            }
        }
        return false;
    }
}

/**
 * Sol2b. 上面中心开花有大量重复计算
 * 比如实际上只要memo
 * l to r 到cur的最小值
 * r to l 到cur的最大值
 * 即可
 * （和trapping rain water非常类似）
 *
 * O(N), O(N)
 */
class SolITS2b {
    public boolean increasingTriplet(int[] nums) {
        //sanity check
        if (nums == null || nums.length < 3) {
            return false;
        }
        //o(n)找到左边min
        //物理意义：leftMin[i] : 从[0, i-1]的最小值，i是excluding的
        int[] leftMin = new int[nums.length];
        Arrays.fill(leftMin, Integer.MAX_VALUE);
        for (int i = 1; i < nums.length; i++) {
            leftMin[i] = Math.min(leftMin[i - 1], nums[i - 1]);
        }

        //o(n)找到右边max
        //物理意义：rightMax[i] : 从(i, length - 1]的最大值，i是excluding的
        int[] rightMax = new int[nums.length];   //这里改成r to l即可写成DP形式
        Arrays.fill(rightMax, Integer.MIN_VALUE);
        for (int i = nums.length - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], nums[i + 1]);
        }

        //o(n)累计结果
        for (int j = 1; j < nums.length; j++) {
            if (leftMin[j] < nums[j] && nums[j] < rightMax[j]) {
                return true;
            }
        }
        return false;
    }
}

/**
 * Sol2c.上个sol继续优化，空间上，rightMax[]使用一个变量就足够了,后面两个O(N)合并
 *
 * O(N), O(N)
 */
class SolITS2c {
    public boolean increasingTriplet(int[] nums) {
        //sanity check
        if (nums == null || nums.length < 3) {
            return false;
        }
        //o(n)找到左边min
        //物理意义：leftMin[i] : 从[0, i-1]的最小值，i是excluding的
        int[] leftMin = new int[nums.length];
        Arrays.fill(leftMin, Integer.MAX_VALUE);
        for (int i = 1; i < nums.length; i++) {
            leftMin[i] = Math.min(leftMin[i - 1], nums[i - 1]);
        }

        //o(n)找到右边max + 累计结果
        int rightMax = Integer.MIN_VALUE;
        for (int i = nums.length - 2; i >= 0; i--) {
            rightMax = Math.max(rightMax, nums[i + 1]);
            //判断结果
            if (leftMin[i] < nums[i] && nums[i] < rightMax) {
                return true;
            }
        }
        return false;
    }
}



/**
 * Sol3
keep record of min and secondMIn

 o(n)  o(1)
 */
class SolITS3 {
    public boolean increasingTriplet(int[] nums) {
        //sanity check
        if (nums == null || nums.length < 3) {
            return false;
        }
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= first) {
                first = nums[i];
            } else if (nums[i] <= second) {
                second = nums[i];
            } else {
                return true;
            }
        }
        return false;
    }
}
