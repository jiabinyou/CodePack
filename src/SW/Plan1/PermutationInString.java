package SW.Plan1;

import java.util.HashMap;
import java.util.Map;

public class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        //sanity check
        if (s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0 || s1.length() > s2.length()) {
            return false;
        }
        //helper ds + representative ds
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {  //freMap for p
            char c = s1.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int targetLen = map.size();
        int k = s1.length();

        //FS-SW
        int matchedLen = 0;
        for (int j = 0; j < s2.length(); j++) {
            //add fast
            char c = s2.charAt(j);
            Integer freq = map.get(c);
            if (freq != null) {
                if (freq == 1) {
                    matchedLen++;
                }
                map.put(c, freq - 1);
            }

            //remove slow
            if (j >= k) {
                c = s2.charAt(j - k);
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
                    return true;
                }
            }
        }
        return false;
    }
}

