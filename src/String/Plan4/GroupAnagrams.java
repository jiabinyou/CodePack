package String.Plan4;

import java.util.*;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        //sanity check
        if (strs == null || strs.length == 0) {
            return res;
        }
        Map<String, List<String>> map = new HashMap<>(); //<one string anagram, List<String>>
        for (int i = 0; i < strs.length; i++) {
            //将一个word转化成统一形式, 每个word都有一个hashcode
            int[] hashCode = new int[26];
            for (int j = 0; j < strs[i].length(); j++) {
                hashCode[strs[i].charAt(j) - 'a']++;
            }
            String key = Arrays.toString(hashCode);
            //check res
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(strs[i]);
        }
        //build res
        for (List list : map.values()) {
            res.add(new ArrayList<>(list));
        }
        return res;
    }
}
