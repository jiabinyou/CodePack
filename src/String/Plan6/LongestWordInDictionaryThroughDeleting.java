package String.Plan6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 使用和shortest completing word那题相似解法，是不行的
 因为如果只是为s制作freqMap，只要d中的word的char和freq，在freqMap中都有，那么这个word就成立
 无法反映出顺序
 */

/**
 注意题目说：”deleting some characters， 然后匹配“
 这道题的本质其实和”Number of Matching Subsequences“相似，要求不仅char和freq对应，而且出现顺序需要一致
 并且最后要找到长度最长，按照字母表顺序出现在最前面的那一个
 */

/**
 *解题思路：重要！！！！判断是不是subsequece的方法：
 * step 1: 使用invertIdxMap保存字典S中的<char, List<idx>>
 * step 2:  遍历每个word，对于word中的每个char，记录在S中已经遍历的idx：curIdx,
 *          只要word中每个char，都要在S中按照顺序找到存在的idx，该word就是S的一个subsequence
 *          即：只需要记录S当前遍历的curIdx，对于char c = word(i), 在S的List<word(i) idx>中，
 *              找到是否存在smallestLarger curIdx的idx即可，如果对于word中每个char都找到了smallestLarger curIdx的idx，
 *              没有=-1不存在该idx的，那么该word就是S的一个subsequence
 */
public class LongestWordInDictionaryThroughDeleting {
    public String findLongestWord(String s, List<String> d) {
        //sanity check
        if (s == null || s.length() == 0 || d == null || d.isEmpty()) {
            return "";
        }
        Map<Character, List<Integer>> invertIndexMap = getInvertIndexMap(s);
        int longest = 0;
        String result = "";
        for (String word : d) {
            int curIndex = -1;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                curIndex = smallestLarger(invertIndexMap.get(c), curIndex);
                if (curIndex == -1) {
                    break;
                }
            }
            if (curIndex != -1) {
                if (word.length() > longest) {
                    longest = word.length();
                    result = word;
                } else if (word.length() == longest && word.compareTo(result) < 0) {
                    result = word;
                }

            }
        }
        return result;
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

