package Graph.DFS3.Plan4;

import java.util.ArrayList;
import java.util.List;

public class FactorCombinations {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        //sanity check
        if (n == 0 || n == 1) {
            return res;
        }
        List<Integer> path = new ArrayList<>();
        backTracking(n, n / 2, path, res);
        return res;
    }

    private void backTracking(int target, int prevFactor, List<Integer> path, List<List<Integer>> res) {
        //base case
        if (target == 1) { //check res
            res.add(new ArrayList<>(path));
        }
        //recursion + base case
        for (int i = 2; i <= Math.min(target, prevFactor); i++) {  //all branch: i --> all可能的除数
            if (target % i == 0) { //valid branch
                path.add(i);
                backTracking(target / i, i, path, res);
                path.remove(path.size() - 1);
            }
        }
    }
}
