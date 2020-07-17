package SW.Plan1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 FS-SW
 helper ds:
 map (存String p的<char, fre>)
 int targetLen = map.size();(初始值)
 representative ds:
 int matchedLen;
 */
public class FindAllAnagramsInAString {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        //sanity check
        if (s == null || s.length() == 0 || p == null || p.length() == 0 || p.length() > s.length()) {
            return res;
        }
        //helper ds + representative ds
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {  //freMap for p
            char c = p.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int targetLen = map.size();
        int k = p.length();

        //FS-SW
        int matchedLen = 0;
        for (int j = 0; j < s.length(); j++) {
            //add fast
            char c = s.charAt(j);
            Integer freq = map.get(c);
            if (freq != null) {
                if (freq == 1) {
                    matchedLen++;
                }
                map.put(c, freq - 1);
            }

            //remove slow
            if (j >= k) {
                c = s.charAt(j - k);
                freq = map.get(c);
                if (freq != null) {
                    if (freq == 0) {
                        matchedLen--;
                    }
                    map.put(c, freq + 1);
                }
            }

            //check res
            if (j >= k - 1) {
                if (matchedLen == targetLen) {
                    res.add(j - k + 1);
                }
            }
        }
        return res;
    }
}

