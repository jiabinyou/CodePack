package SW.Plan2;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringwithAtMostKDistinctCharacters {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
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
            while (map.size() > k) {
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
