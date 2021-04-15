package String.Plan4;

import java.util.*;

/**解法 ：hashcode + hashmap
 * step 1:
 * To find all the anagrams and group them together, first we can sort each word and traverse them into same format(e.g.hashcode).
 * cause the strs[i] only consists of lower-case English letters，so we can use a fixed length array as a frequency map, then convert
 * each word to hashcode.
 *    e.g.      idx  "a" "b" "c" "d"       (ascII code)
 *        hacode     []  []  []  []  []
 * step 2:
 * use all hashcode as the hashmap key, put the corresponding into hashmap value.
 * Once finish traverse all words. All words unser the same key will natruely be the same anagram group.
 *
 * TC: O(N * K)  --> n,length of strrsm k-- the longest length of the single word. traverse each word, for each word we traverse each character
 * SC: O(N * K)
 *
 * */
public class GroupAnagrams {
    /**
     * @param strs: the given array of strings
     * @return: The anagrams which have been divided into groups
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        // sanity check
        if (strs == null || strs.length == 0) {
            return res;
        }
        Map<String, List<String>> map = new HashMap<>();
        //将一个word转化成统一形式, 每个word都有一个hashcode
        for (String word : strs) {
            int[] hashcode = new int[26];
            for (int j = 0; j < word.length(); j++) {
                hashcode[word.charAt(j) - 'a']++;
            }
            String key = Arrays.toString(hashcode);
            //check Res
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(word);
        }
        //build res
        for (List<String> oneGroup : map.values()) {
            res.add(new ArrayList<>(oneGroup));
        }
        return res;
    }
}

