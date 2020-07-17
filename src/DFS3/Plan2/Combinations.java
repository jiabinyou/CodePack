package DFS3.Plan2;

/**
 * input: no dup
 * res: no dup
 * 不需要dedup
 */

/**
 Sol1. pick or not pick
 O(2^N)  O(N)
 */

import java.util.ArrayList;
import java.util.List;

/**关键：base case控制recursion结束条件*/
public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        //sanity check
        if (n == 0 || k == 0 || n < k) {
            return res;
        }
        List<Integer> path = new ArrayList<>();
        backTracking(n, k, 0, path, res);
        return res;
    }

    /**base case考虑三种：check res，recursion定义停止层，recursion提前停止层*/
    private void backTracking(int n, int k, int level, List<Integer> path, List<List<Integer>> res) {
        //base case
        if (path.size() == k) {  //提前停止层 + check res
            res.add(new ArrayList<>(path));
            return;
        }
        if (level == n) {  //定义停止层
            return;
        }
        //update path + recursion
        //pick
        path.add(level + 1);
        backTracking(n, k, level + 1, path, res);
        path.remove(path.size() - 1);
        //not pick
        backTracking(n, k, level + 1, path, res);
    }
}
