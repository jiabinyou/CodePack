package DFS3.Plan1;

import java.util.ArrayList;
import java.util.List;

/**
 * input: no dup
 * res: no dup
 * 不需要dedup
 */

/**
 Sol1. pick or not pick
      O(2^N)  O(N)
 */
public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        //sanity check
        if (nums == null || nums.length == 0) {
            return res;
        }
        List<Integer> path = new ArrayList<>();
        backTracking(nums, 0, path, res);
        return res;
    }

    private void backTracking(int[] nums, int level, List<Integer> path, List<List<Integer>> res) {
        //base case
        if (level == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        //update path + recursion
        //pick
        path.add(nums[level]);
        backTracking(nums, level + 1, path, res);
        path.remove(path.size() - 1);
        //not pick
        backTracking(nums, level + 1, path, res);
    }
}

/**
 Sol2. select as order
 */
class SolS2 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        //sanity check
        if (nums == null || nums.length == 0) {
            return res;
        }
        res.add(new ArrayList<>()); //需单独处理root节点
        List<Integer> path = new ArrayList<>();
        backTracking(nums, 0, path, res);
        return res;
    }

    private void backTracking(int[] nums, int level, List<Integer> path, List<List<Integer>> res) {
        //recursion + base case
        for (int i = level; i < nums.length; i++) {
            path.add(nums[i]);  //update cur path
            res.add(new ArrayList<>(path)); //每个recursion point都是一个有效解
            backTracking(nums, i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }
}
