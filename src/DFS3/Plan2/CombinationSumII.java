package DFS3.Plan2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * input: has dup
 * res: no dup
 * 需要dedup
 */

/**
 Sol2. select as order
 前提：保证input有序，才能select as order，勿忘排序！！！
 */

/**
 * 与combination sum区别：
 * 1.每个idx只能使用一次，但是如果是不同idx有相同的val，是可以使用的
 * --》要对idx进行dedup
 *
 * 2.DFS进入下一层recursion：
 * 不能从当前idx开始，而应该是idx + 1开始，所以i + 1
 */
public class CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        //sanity check
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        Arrays.sort(candidates);
        List<Integer> path = new ArrayList<>();
        backTracking(candidates, target, 0, path, res);
        return res;
    }

    private void backTracking(int[] candidates, int leftTarget, int idx, List<Integer> path, List<List<Integer>> res) {
        //base case
        if (leftTarget == 0) {  //check res
            res.add(new ArrayList<>(path));
            return;
        }
        if (leftTarget < 0) {  //pruning层
            return;
        }
        for (int i = idx; i < candidates.length; i++) {  //定义停止层
            /**dedup关键：同一层，不同idx，相同val的只使用一次*/
            if (i > idx && candidates[i] == candidates[i - 1]) {
                continue;
            }
            path.add(candidates[i]);
            backTracking(candidates, leftTarget - candidates[i], i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }
}
