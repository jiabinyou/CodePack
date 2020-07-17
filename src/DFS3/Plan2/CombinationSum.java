package DFS3.Plan2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * input: no dup
 * res: no dup
 * 不需要dedup
 */

/**
 Sol1. pick or not pick
注意！！这道题，每个元素可以被重复pick，即理论上pick branch可以有无限多次，显然，这种DFS构建方法在
 这道题中不再好用
 */

/**
 Sol2. select as order
 前提：保证input有序，才能select as order，勿忘排序！！！
 */

/**
 * DFS:
 * 当多少个数字，就有多少层；
 * 每行有多少valid的candidate int num，那层就有多少branch
 *
 * 更新行数方法：下一层idx = i （因为可重复使用！！仍从当前recursion point值开始）
 * 每层build branch方法： >=自己的，即[i, candidate.length - 1],都是valid branch
 */
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
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
        for (int i = idx; i < candidates.length; i++) { //定义停止层 i < candidates.length
            path.add(candidates[i]);
            backTracking(candidates, leftTarget - candidates[i], i, path, res);
            path.remove(path.size() - 1);
        }
    }
}
