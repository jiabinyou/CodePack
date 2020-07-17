package Array.Plan5;

import java.util.HashMap;
import java.util.Map;

/**
 Sol.preSum
 技巧：可以将0转换成-1，那么可以通过preSum == 0 ? 判断是否有相等数量的0和1
 观察:我们操作的是preSum本身,如果用arr记录preSum，因为input相当于引入-1负数，所以preSum[]没有sort性质，用arr记录无意义
 所以使用BF + MEMO进行优化，
 又因为求longest length，可使用preSum DS: map<preSum, leftMost idx>

 O(N) O(N)
 */
public class ContiguousArray {
    public int findMaxLength(int[] nums) {
        //sanity check
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //preSum ds
        Map<Integer, Integer> map = new HashMap<>();
        //init
        map.put(0, -1);

        //fix j, iterate
        int glbLongest = 0;
        int sum = 0;
        for (int j = 0; j < nums.length; j++) {
            sum += nums[j] == 0 ? -1 : 1;  //update preSum
            Integer leftMost = map.get(sum); //target: preSum[j] - preSum[i] == 0//target: sum = preSum[i]
            if (leftMost != null) {
                glbLongest = Math.max(glbLongest, j - leftMost);
            }
            map.putIfAbsent(sum, j);
        }
        return glbLongest;
    }
}
