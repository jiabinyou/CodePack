package Array.Plan1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * input: !!un-sorted, no dup (因为有正负，所以按照绝对值sort的，结果并不是按照大小sort好的)
 * return : index pairs
 *
 * Sol:memo
 * 因为unsorted，但是要求最终输出index，即使sort以后使用two pointer也会使得index丢失，所以使用memo
 * Map<Integer, Integer> ->map<num, idx>
 * then iterate the whole input, use map to find if there is pairs which sum equal to target(in map, check target - curVal)
 *
 * TC:O(N)
 * SC:O(N)
 *
 * e.g.target = 1
 *   [0,   -1,    2,    -3,    4]      map                                      res
 *idx 0     1     2      3      4
 *p   i                                <0, 0>
 *          i                          <0, 0> <-1, 1>
 *                i                    <0, 0> <-1, 1><2, 2>                   <1, 2>
 *                       i             <0, 0> <-1, 1><2, 2><-3, 3>            <1, 2>
 *                             i       <0, 0> <-1, 1><2, 2><-3, 3><4, 4>      <1, 2><3, 4>
 * */
public class TwoSumVII {
    public List<List<Integer>> twoSumVII(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        // sanity check
        if (nums == null || nums.length <= 1) {
            return res;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer idx = map.get(target - nums[i]);
            if (idx != null) {
                List<Integer> curRes = new ArrayList<>();
                curRes.add(idx);
                curRes.add(i);
                res.add(new ArrayList<>(curRes));
            }
            map.put(nums[i], i);
        }
        return res;
    }
}

