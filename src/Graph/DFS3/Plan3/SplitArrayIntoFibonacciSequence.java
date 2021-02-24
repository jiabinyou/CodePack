package Graph.DFS3.Plan3;

/**
 * 这道题的难点在于cases很多，需要一一分清
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Sol1. Find All Path，get the first one
 *
 * backTracking分解：
 * List<String> path: 记录一条dfs path上，各个区分出来的string
 * 层数：input有多少个char，就有多少层
 * 每层branch：当前index(level)的字母开头，后面有多少可选candidate char，就有多少branch
 *
 * 进入下一层方法：i + 1 ！！因为只能从当前层结尾，再往后一个char开始处理
 * iterate all branch方法: 注意！！[index, s.length() - 1)] 都是valid branch
 *                         而当前recursion point所取得substring是s.substring(index, i + 1)
 */
public class SplitArrayIntoFibonacciSequence {
    public List<Integer> splitIntoFibonacci(String S) {
        List<List<Integer>> allRes = new ArrayList<>();
        //sanity check
        if (S == null || S.length() == 0) {
            return new ArrayList<>();
        }
        List<Integer> path = new ArrayList<>();
        backTracking(S, 0, path, allRes);
        return allRes.size() == 0 ? new ArrayList<>() : allRes.get(0);
    }

    private void backTracking(String S, int index, List<Integer> path, List<List<Integer>> allRes) {
        //base case
        if (index == S.length()) {
            if (path.size() >= 3) {
                allRes.add(new ArrayList<>(path));
                return;
            }
        }
        //recursion
        for (int i = index; i < S.length(); i++) {    //iterate all branch
            String curr = S.substring(index, i + 1);
            long num = Long.valueOf(curr);  //需使用long，因为中间值可能超过int边界
            /**pruning: 题目each piece must not have extra leading zeroes, except if the piece is the number 0 itself */
            if (curr.length() > 1 && curr.charAt(0) == '0') {
                break;
            }
            if (num > Integer.MAX_VALUE) {  //pruning
                break;
            }
            //valid branch
            if (path.size() < 2 || num == path.get(path.size() - 1) + path.get(path.size() - 2)) {
                path.add((int)num);
                backTracking(S, i + 1, path, allRes);
                path.remove(path.size() - 1);
            }
        }
    }
}

/**
 * Sol2.使用return val优化backTracking
 *
 * 思考：上一题中，其实没有博尧找到所有符合要求的sol，直接有一条path是valid sol，就可以直接返回
 * 可以使用boolean值标记path是否是valid sol
 * 一个是true，就可以返回true
 */
class SolSAFS2 {
    public List<Integer> splitIntoFibonacci(String S) {
        //sanity check
        if (S == null || S.length() == 0) {
            return new ArrayList<>();
        }
        List<Integer> path = new ArrayList<>();
        if (backTracking(S, 0, path)) {
            return path;
        }
        return new ArrayList<>();
    }

    private boolean backTracking(String S, int index, List<Integer> path) {
        //base case
        if (index == S.length()) {
            return path.size() >= 3;
        }
        //recursion
        for (int i = index; i < S.length(); i++) {    //iterate all branch
            String curr = S.substring(index, i + 1);
            long num = Long.valueOf(curr);  //需使用long，因为中间值可能超过int边界
            /**pruning: each piece must not have extra leading zeroes, except if the piece is the number 0 itself */
            if (curr.length() > 1 && curr.charAt(0) == '0') {
                break;
            }
            if (num > Integer.MAX_VALUE) {  //pruning
                break;
            }
            //valid branch
            if (path.size() < 2 || num == path.get(path.size() - 1) + path.get(path.size() - 2)) {
                path.add((int)num);
                if (backTracking(S, i + 1, path)) {   //一个是true，就可以返回true
                    return true;
                }
                path.remove(path.size() - 1);
            }
        }
        return false;
    }
}


