package Graph.DFS3.Plan3;

import java.util.*;

/**
 * Sol1.backTracking
 * 这个解法在LC中TLE
 */
/**
 * backTracking分解：
 * 层数：input有多少个char，就有多少层
 * 每层branch：当前index(level)的字母开头，后面有多少可选candidate char，就有多少branch
 *
 * 进入下一层方法：i + 1 ！！因为只能从当前层结尾，再往后一个char开始处理
 * iterate all branch方法: [index, s.length - 1] 都是valid branch
 */

public class WordBreakII {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() == 0 || wordDict == null || wordDict.isEmpty()) {
            return result;
        }
        List<String> path = new ArrayList<>(); //存放一条dfs path结果，即一个可能的sol
        Set<String> wordSet = new HashSet<>(wordDict); //先dedup一次，提高效率
        dfs(s, 0, path, result, wordSet);
        return result;
    }
    private void dfs(String s, int index, List<String> path, List<String> result, Set<String> wordSet) {
        if (index == s.length()) {
            result.add(parseString(path));
            return;
        }
        for (int i = index; i < s.length(); i++) {
            String cur = s.substring(index, i + 1);
            if (wordSet.contains(cur)) {
                path.add(cur);
                dfs(s, i + 1, path, result, wordSet);
                path.remove(path.size() - 1);
            }
        }
    }
    private String parseString(List<String> path) {
        StringBuilder sb = new StringBuilder();
        for (String s : path) {
            sb.append(s).append(" ");
        }
        sb.deleteCharAt(sb.length() - 1); //去掉最后一个空格" "
        return sb.toString();
    }
}

/**
 * Sol2. recursion + memo
 */
class SolWBII{
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() == 0 || wordDict == null || wordDict.isEmpty()) {
            return result;
        }
        Set<String> wordSet = new HashSet<>(wordDict);
        Map<Integer, List<String>> memo = new HashMap<>();
        return recursion(s, s.length(), wordSet, memo);
    }

    private static List<String> recursion(String s, int index, Set<String> wordSet, Map<Integer, List<String>> memo) {
        if (memo.containsKey(index)) {
            return memo.get(index);
        }
        List<String> allPaths = new ArrayList<>();
        if (index == 0) {
            allPaths.add("");
            return allPaths;
        }
        for (int i = index; i >= 0; i--) {
            String cur = s.substring(i, index);
            if (wordSet.contains(cur)) {
                List<String> prevPaths = recursion(s, i, wordSet, memo);
                for (String path : prevPaths) {
                    if (path.isEmpty()) {
                        allPaths.add(path + cur);
                    } else {
                        allPaths.add(path + " " + cur);
                    }
                }
            }
        }
        memo.put(index, allPaths);
        return allPaths;
    }
}

/**
 * Sol3. DP + recover all path
关键：
在word break1中，使用的是boolean[] dp来记录每个位置能/不能被break这两种信息即可
在这一题中，对于s中的每一个位置，我们都需要使用一个list来记录：
    该位置是否能被break？如果能被break，以当前位置为终点，前面的切口有哪些？
所以这里选择的是ArrayList来实现dp结构，如果该位置list是null，就说明不能被切；
如果不是null，就把ending at i的所有前面能切的位置的index装在list，放在该位置；
*/
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        //sanity check
        if (s == null || s.length() == 0) {
            return null;
        }

        List<Integer>[] dp = new ArrayList[s.length() + 1]; //即array上面每一个位置都是一个arraylist,只是用arraylist new array出来
        //base case
        dp[0] = new ArrayList<>();
        //这里j从0开始，即把两个case合并在一起的写法
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                if (dp[j] != null && wordDict.contains(s.substring(j, i + 1))) {
                    if (dp[i + 1] == null) {
                        dp[i + 1] = new ArrayList<>();
                    }
                    dp[i + 1].add(j);
                }
            }
        }


        //recover res
        List<String> res = new ArrayList<>();
        if (dp[dp.length - 1] != null) {      //说明该string可以被break，此时才调用dfs revory res
            getAllWord(s, dp, s.length() - 1, new ArrayList<>(), res);
        }
        return res;
    }

    //dfs 3 : recover all path
    private void getAllWord(String s, List<Integer>[] dp, int index, List<Integer> path, List<String> res) {
        if (index == -1) {
            StringBuilder sb = new StringBuilder();
            for (int i = path.size() - 1; i >= 0; i--) {
                if (i != 0) {
                    sb.append(s.substring(path.get(i), path.get(i - 1))).append(" ");
                } else {
                    sb.append(s.substring(path.get(i), s.length()));
                }
            }
            res.add(sb.toString());
            return;
        }
        //all branches
        for (int i = 0; i < dp[index + 1].size(); i++) {
            path.add(dp[index + 1].get(i));
            getAllWord(s, dp, dp[index + 1].get(i) - 1, path, res);
            //recovery
            path.remove(path.size() - 1);
        }
    }
}


