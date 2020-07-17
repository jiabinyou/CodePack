package SW.Plan2;

import java.util.HashMap;
import java.util.Map;

/**
 NFS-SW
 representative ds:
 map
 int repeatNum
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        //sanity check
        if (s == null || s.length() == 0) {
            return 0;
        }
        int glbLongest = 0;
        //representative ds
        Map<Character, Integer> map = new HashMap<>(); //<char, fre>
        int repeatNum = 0; //#出现重复的字母
        //NFS-SW
        int slow = 0;
        for (int fast = 0; fast < s.length(); fast++) {
            //add fast
            char c = s.charAt(fast);
            map.put(c, map.getOrDefault(c, 0) + 1);
            if (map.get(c) == 2) {
                repeatNum++;
            }

            //remove slow
            while (repeatNum != 0) {
                c = s.charAt(slow);
                map.put(c, map.get(c) - 1);
                if (map.get(c) == 1) {
                    repeatNum--;
                }
                slow++; //update slow
            }

            //check res
            glbLongest = Math.max(glbLongest, fast - slow + 1);
        }
        return glbLongest;
    }
}
