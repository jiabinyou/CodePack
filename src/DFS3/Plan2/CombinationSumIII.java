package DFS3.Plan2;

import java.util.ArrayList;
import java.util.List;

/**
 * input: no dup
 * res: no dup
 * 不需要dedup
 */

/**
 Sol2. select as order
 前提：保证input有序，才能select as order，勿忘排序！！！
 */

/**
 * DFS:
 * 最多n个数字，且不能重复使用，所以最多n层；
 * 每行有多少valid的candidate int num，那层就有多少branch
 *
 * 更新行数方法：下一层idx = i +1（因为不可重复使用！！仍从当前recursion point值开始）
 * 每层build branch方法： >=自己的，即[i, 9],都是valid branch
 */
public class CombinationSumIII {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        //sanity check
        if (k == 0) {
            return res;
        }
        List<Integer> path = new ArrayList<>();
        backTracking(n, k, 1, path, res);
        return res;
    }

    private void backTracking(int leftTarget, int k, int idx, List<Integer> path, List<List<Integer>> res) {
        //base case
        if (leftTarget == 0 && path.size() == k) {  //check res
            res.add(new ArrayList<>(path));
            return;
        }
        if (leftTarget < 0) {  //pruning层
            return;
        }
        for (int i = idx; i <= 9; i++) {  //定义停止层
            path.add(i);
            backTracking(leftTarget - i, k, i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }
}
