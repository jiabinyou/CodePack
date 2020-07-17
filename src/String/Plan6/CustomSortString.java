package String.Plan6;

import java.util.HashMap;
import java.util.Map;

/**
 思路：
 可以为T建立freqMap，按照S中出现的顺序，将T中的元素放入res中
 最后再将T剩下的字母，排进res中
 */
public class CustomSortString {
    public String customSortString(String S, String T) {
        //sanity check
        if (T == null || T.length() == 0 || S == null || S.length() == 0) {
            return T;
        }
        //build freq map for T
        Map<Character, Integer> map = new HashMap<>();
        for (char c : T.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        //put T's char into res as S's order
        StringBuilder sb = new StringBuilder();
        for (char c : S.toCharArray()) {
            Integer freq = map.get(c);
            if (freq != null) {
                for (int i = 0; i < freq; i++) {
                    sb.append(c);
                }
            }
            map.remove(c);
        }
        //put T's left char into res
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            int freq = entry.getValue();
            for (int i = 0; i < freq; i++) {
                sb.append(entry.getKey());
            }
        }
        return sb.toString();
    }
}
