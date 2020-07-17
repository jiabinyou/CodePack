package String.Plan6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Sol.invertIdx
 * 这道题的解题思路非常巧妙，因为寻找的是matching的subsequence，所以
 * 1.不要求连续
 * 2.按顺序！！出现过即可
 *      这里条件2非常关键，因为仍然要按顺序才叫做sequence，e.g.
 *      S = "abcde"
 *      words = ["a", "bb", "acd", "ace"]
 *      这里words中每个word，出现顺序仍然要按照S中abcde，才行，比如cba就不是subsequence
 *
 *解题思路：重要！！！！判断是不是subsequece的方法：
 * step 1: 使用invertIdxMap保存字典S中的<char, List<idx>>
 * step 2:  遍历每个word，对于word中的每个char，记录在S中已经遍历的idx：curIdx,
 *          只要word中每个char，都要在S中按照顺序找到存在的idx，该word就是S的一个subsequence
 *          即：只需要记录S当前遍历的curIdx，对于char c = word(i), 在S的List<word(i) idx>中，
 *              找到是否存在smallestLarger curIdx的idx即可，如果对于word中每个char都找到了smallestLarger curIdx的idx，
 *              没有=-1不存在该idx的，那么该word就是S的一个subsequence
 */
public class NumberOfMatchingSubsequences {
    public int numMatchingSubseq(String S, String[] words) {
        //sanity check
        if (S == null || S.length() == 0 || words == null || words.length == 0) {
            return 0;
        }
        Map<Character, List<Integer>> invertIndexMap = getInvertIndexMap(S);
        int count = 0;
        for (String word : words) {
            int curIndex = -1;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                curIndex = smallestLarger(invertIndexMap.get(c), curIndex);
                if (curIndex == -1) {
                    break;
                }
            }
            if (curIndex != -1) {
                count++;
            }
        }
        return count;
    }

    private int smallestLarger(List<Integer> indices, int target) {
        if (indices == null || indices.isEmpty()) {
            return -1;
        }
        int left = 0;
        int right = indices.size() - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (indices.get(mid) > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return indices.get(left) > target ? indices.get(left) : -1;
    }

    private Map<Character, List<Integer>> getInvertIndexMap(String s) {
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.putIfAbsent(c, new ArrayList<>());
            map.get(c).add(i);
        }
        return map;
    }
}

