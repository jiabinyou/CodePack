package SW.Plan2;

import java.util.HashMap;
import java.util.Map;

/**
 审题：
 因为是要求window中最多只有两种字母，所以这题不需要记录单独的repeatNum了
 而是在fre = 0时候，将字母删除，然后直接记录map size即可， map.size() == # distinct char in window
 */
public class LongestSubstringWithAtMostTwoDistinctCharacters {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        //sanity check
        if (s == null || s.length() == 0) {
            return 0;
        }
        int glbLongest = 0;
        //representative ds
        Map<Character, Integer> map = new HashMap<>(); //<char, fre>
        //NFS-SW
        int slow = 0;
        for (int fast = 0; fast < s.length(); fast++) {
            //add fast
            char c = s.charAt(fast);
            map.put(c, map.getOrDefault(c, 0) + 1);

            //remove slow
            while (map.size() > 2) {
                c = s.charAt(slow);
                map.put(c, map.get(c) - 1);
                if (map.get(c) == 0) {
                    map.remove(c);
                }
                slow++; //update slow
            }

            //check res
            glbLongest = Math.max(glbLongest, fast - slow + 1);
        }
        return glbLongest;
    }
}
