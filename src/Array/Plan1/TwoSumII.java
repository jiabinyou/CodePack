package Array.Plan1;

public class TwoSumII {
    /**
     input sorted two sum
     指针可不回头 --》 相向而行two pointer
     */
    public int[] twoSum(int[] numbers, int target) {
        //sanity check
        if (numbers == null || numbers.length == 0) {
            return new int[0];
        }

        int i = 0;
        int j = numbers.length - 1;
        while (i < j) {
            int sum = numbers[i] + numbers[j];
            if ( sum == target) {
                return new int[]{i + 1, j + 1};
            } else if (sum < target) {
                i++;
            } else {
                j--;
            }
        }
        return new int[]{-1, -1};
    }
}
