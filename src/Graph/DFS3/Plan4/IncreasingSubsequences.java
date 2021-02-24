package Graph.DFS3.Plan4;

/**
 * Sol1.
 * backTracking : pick or not pick
 * 换出recurision tree，会发现，这个方法，不好去dedup
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Sol2.backTracking
 * add char one by one
 */
public class IncreasingSubsequences {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        //sanity check
        if (nums == null || nums.length == 0) {
            return res;
        }
        List<Integer> path = new ArrayList<>();
        backTracking(nums, 0, path, res);
        return res;
    }

    private void backTracking(int[] nums, int index, List<Integer> path, List<List<Integer>> res) {
        //base case
        if (path.size() > 1)  {
            res.add(new ArrayList<>(path));
        }
        /**recursion + base case*/
        //pick
        Set<Integer> set = new HashSet<>();
        for (int i = index; i < nums.length; i++) {  //traverse all branch, 同时也是recursion定义停止层
            if (path.size() == 0 || nums[i] >= path.get(path.size() - 1)) {
                if (set.add(nums[i])) {
                    path.add(nums[i]);
                    backTracking(nums, i + 1, path, res);
                    path.remove(path.size() - 1);
                }
            }
        }
    }
}
