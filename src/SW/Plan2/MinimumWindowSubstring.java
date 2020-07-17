package SW.Plan2;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        //sanity check
        if (s == null || s.length() == 0 || t == null || t.length() == 0) {
            return "";
        }

        //representative ds
        Map<Character, Integer> map = new HashMap<>();  //still matched info in t: <char, freq>
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        //NFS-SW
        int glbShortest = Integer.MAX_VALUE;
        int startIdx = 0;  //最终最短sw的开始位置

        int matchCount = 0;  //# char matched
        int slow = 0; //sw:[s,f]
        for (int fast = 0; fast < s.length(); fast++) {
            //add fast
            char c = s.charAt(fast);
            Integer freq = map.get(c);
            if (freq != null) {
                if (freq == 1) {
                    matchCount++;
                }
                map.put(c, freq - 1);
            }

            //remove slow
            while (matchCount == map.size()) {
                //update glbShoetest
                if (fast - slow + 1 < glbShortest) {
                    glbShortest = fast - slow + 1;
                    startIdx = slow;
                }

                //remove slow
                c = s.charAt(slow);
                freq = map.get(c);
                if (freq != null) {
                    if (freq == 0) {
                        matchCount--;
                    }
                    map.put(c, freq + 1);
                }
                //update slow
                slow++;
            }
        }
        return glbShortest == Integer.MAX_VALUE ? "" : s.substring(startIdx, startIdx + glbShortest);
    }
}
