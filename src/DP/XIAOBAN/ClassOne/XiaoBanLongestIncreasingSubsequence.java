package DP.XIAOBAN.ClassOne;
import java.util.*;
/**
 * Sol1. dfs backtracking
 */
public class XiaoBanLongestIncreasingSubsequence {
    private int longest = 0;
    public int longest(int[] array) {
        List<Integer> path = new ArrayList<>();
        path.add(array[array.length - 1]);
        dfs(array, array.length - 1, path);
        path.remove(path.size() - 1);
        return longest;
    }

    private void dfs(int[] array, int idx, List<Integer> path) {
        //base case
        longest = Math.max(longest, path.size());
        //induction rule
        for (int i = idx - 1; i >= 0; i--) {
            if (array[idx] > array[i]) {
                path.add(array[i]);
                dfs(array, i, path);
                path.remove(path.size() - 1);
            }
        }
    }
}

/**
 * Sol.1b dfs backtracking
 * 简化代码，去掉path信息，只留len即可
 */
class SolXBLIS1b {
    private int longest = 0;
    public int longest(int[] array) {
        dfs(array, array.length - 1, 0);
        return longest;
    }

    private void dfs(int[] array, int idx, int curLen) {
        //base case
        longest = Math.max(longest, curLen);
        //induction rule
        for (int i = idx - 1; i >= 0; i--) {
            if (array[idx] > array[i]) {
                curLen += 1;
                dfs(array, i, curLen);
            }
        }
    }
}

/**
 * Sol2.DFS pure recursion
 */
class SolXBLIS2 {
    public int longest(int[] array) {
        return dfs(array, 0);
    }

    /**ending at idx, what are all increasing subsequence path*/
    private int dfs(int[] array, int idx) {
        //base case
        int longest = 1;  /**init length*/
        //induction rule
        for (int i = idx - 1; i >= 0; i--) {
            if (array[idx] > array[i]) {
                int prevLongest = dfs(array, i);
                prevLongest += 1;  //update cur path
                longest = Math.max(longest, prevLongest);
            }
        }
        return longest;
    }
}

/**
 * Sol3: DFS pure recursion + memo
 */
class SolXBLIS3 {
    int[] mem;  /**mem[i] : longest increasing subsequence end at idx i*/
    public int longest(int[] array) {
        mem = new int[array.length];
        return dfs(array, 0);
    }

    /**ending at idx, what are all increasing subsequence path*/
    private int dfs(int[] array, int idx) {
        //base case
        if (mem[idx] != 0) { /**检查如果memo中已经记录结果，直接使用即可*/
            return mem[idx];
        }
        int longest = 1;  /**init length*/
        //induction rule
        for (int i = idx - 1; i >= 0; i--) {
            if (array[idx] > array[i]) {
                int prevLongest = dfs(array, i);
                prevLongest += 1;  //update cur path
                longest = Math.max(longest, prevLongest);
            }
        }
        mem[idx] = longest;  /**把当前recurison point结果记录在memo中*/
        return longest;
    }
}