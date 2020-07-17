package Array.Plan4;

public class TrappingRainWater {
    /**
     Sol1.中心开花
     O(N^2)
     */
    public int trap(int[] height) {
        //sanity check
        if (height == null || height.length == 0) {
            return 0;
        }

        int count = 0;
        for (int j = 0; j < height.length; j++) {
            int leftMax = 0;
            for (int i = 0; i < j; i++) {
                leftMax = Math.max(leftMax, height[i]);
            }

            int rightMax = 0;
            for (int i = j + 1; i < height.length; i++) {
                rightMax = Math.max(rightMax, height[i]);
            }

            count += Math.max(0, Math.min(leftMax, rightMax) - height[j]);
        }
        return count;
    }
}

/**
 Sol1b. DP,使用O（n）空间
 前面一个sol，在计算leftMax, rightMax过程中有大量的重复计算，实际上可用int[]记录
 每次计算新的leftMax[i], rightMax[i]，是要看前一步结果即可
 即
 leftMax[i] depend on  leftMax[i] - 1
 rightMax[i] depend on rightMax[i + 1]
 所以改写成DP即可

 O(N), O(N)
 */
class SolTRW1b {
    public int trap(int[] height) {
        //sanity check
        if (height == null || height.length == 0) {
            return 0;
        }

        //o(n)找到左边界
        int[] leftMax = new int[height.length];
        for (int i = 1; i < height.length; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i - 1]);
        }

        //o(n)找到右边界
        int[] rightMax = new int[height.length];   //这里改成r to l即可写成DP形式
        for (int i = height.length - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i + 1]);
        }

        //o(n)累计结果
        int count = 0;
        for (int j = 0; j < height.length; j++) {
            count += Math.max(0, Math.min(leftMax[j], rightMax[j]) - height[j]);
        }
        return count;
    }
}

/**
 Sol1c. DP,简化掉O（n）空间
 O(N), O(1)
 */
class SolTRW1c {
    public int trap(int[] height) {
        //sanity check
        if (height == null || height.length == 0) {
            return 0;
        }

        int l = 0;   //left pointer
        int r = height.length - 1;  //right pointer
        int leftMax = height[l];
        int rightMax = height[r];

        int count = 0;
        while (l < r) {
            if (height[l] < height[r]) {
                if (leftMax > height[l]) {
                    count += leftMax - height[l];
                } else {
                    leftMax = height[l];
                }
                l++;
            } else {
                if (rightMax > height[r]) {
                    count += rightMax - height[r];
                } else {
                    rightMax = height[r];
                }
                r--;
            }
        }
        return count;
    }
}


