package Graph.DFS3.Plan4;

import java.util.ArrayList;
import java.util.List;

/**
 * 时间复杂度O(n*m)
 * n为主串长度，m为子串集长度
 * 空间复杂度O(n)
 * n为主串长度
 * */
public class FindWords {
    public List<String> findWords(String str, List<String> dict) {
        List<String> res = new ArrayList<>();
        // sanity check
        if (str == null || str.length() == 0 || dict == null || dict.size() == 0) {
            return res;
        }
        for (String word : dict) {
            if (dfs(word, str, 0, 0)) {
                res.add(word);
            }
        }
        return res;
    }

    private boolean dfs(String word, String str, int matchedLen, int level) {
        //base case
        if (matchedLen == word.length()) {
            return true;
        }
        if (level >= str.length()) {
            return false;
        }
        //recursion
        if (word.charAt(matchedLen) == str.charAt(level)) {
            matchedLen++;
        }
        if (dfs(word, str, matchedLen, level + 1)) {
            return true;
        }
        return false;
    }
}



