package DP.XIAOBAN.ClassOne;

import java.util.ArrayList;
import java.util.List;

/**
 * pure recursion
 */
public class XiaoBanAllSubset {
    private List<List<Integer>> allSubsets(int[] array) {
        List<List<Integer>> res = pureRecursion(array, array.length - 1);
        return res;
    }

    private List<List<Integer>> pureRecursion(int[] array, int idx) {
        //base case
        if (idx == -1) {
            List<Integer> subset = new ArrayList<>();
            List<List<Integer>> res = new ArrayList<>();
            res.add(subset);
            return res;
        }

        //solve subproblem
        List<List<Integer>> subSol = pureRecursion(array, idx - 1);
        List<List<Integer>> res = new ArrayList<>();

        //case 1. add
        for (List<Integer> list : subSol) {
            List<Integer> tmp = new ArrayList<>(list);
            tmp.add(array[idx]);
            res.add(tmp);
        }

        //case 2. not add
        res.addAll(subSol);

        //return val
        return res;
    }
}

/**
 * Sol2. 改写成DP
 */
class XiaoBanAllSubset2 {
    private List<List<Integer>> allSubsets(int[] array) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();
        //base case ： dp[-1]
        res.add(subset);
        //induction rule
        for (int i = 0; i < array.length; i++) {
            int size = res.size();
            for (int j = 0; j < size; j++) {
                subset.add(array[i]);
                res.add(subset);
            }
        }
        return res;
    }
}
