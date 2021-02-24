package Graph.DFS3.Plan1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 input: dup val
 res: no dup idx, can have dup val
 -->需要dedup idx
 */

/**
 Sol1. pick or not pick
 O(2^N)  O(N)
 */
public class SubsetsII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        /**先sort，才能dedup*/
        Arrays.sort(nums);
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
        //pick
        path.add(nums[level]);
        backTracking(nums, level + 1, path, res);
        path.remove(path.size() - 1);
        /**关键：dedup before not pick, 跳过相同val的元素*/  //not pick
        while (level + 1 < nums.length && nums[level] == nums[level + 1]) {
            level++;
        }
        backTracking(nums, level + 1, path, res);
    }
}

/**
 Sol2. select as order
 */
class SoluSII2 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        /**先sort，才能dedup*/
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        //sanity check
        if (nums == null || nums.length == 0) {
            return res;
        }
        res.add(new ArrayList<>()); //需单独处理root节点，加入" "
        List<Integer> path = new ArrayList<>();
        backTracking(nums, 0, path, res);
        return res;
    }

    private void backTracking(int[] nums, int level, List<Integer> path, List<List<Integer>> res) {
        //base case(已在recursion条件中完成，可省略)
        for (int i = level; i < nums.length; i++) {
            /**dedup关键：同一层，只要跳过val相同元素，即可dedup*/
            if (i > level && nums[i] == nums[i - 1]) {
                continue;  //for循环i自动++
            }
            path.add(nums[i]);
            res.add(new ArrayList<>(path));
            backTracking(nums, i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }
}

