package String.Plan1;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
    public int romanToInt(String s) {
        //sanity check
        if (s == null || s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int res = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            int curr = map.get(s.charAt(i));
            int next = map.get(s.charAt(i + 1));
            if (curr >= next) {
                res += curr;
            } else {
                res -= curr;
            }
        }
        //post-process
        res += map.get(s.charAt(s.length() - 1));
        return res;
    }
}
