package String.Plan5;

import java.util.HashMap;
import java.util.Map;

/**
 * 思路：
 * 使用map装s与t中mapping的对应关系<key: s(i), value:t(i)>，有两种case
 * 1.map中没有key s(i),但是已经有了value t(i),说明其他char被编码成t(i)了，返回false
 * 2.map中有key s(i),但是key s(i)对应value != t(i),说明s(i)被编码成多种char了，返回false
 */
public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        //sanity check
        if (s == null && t == null || s.length() == 0 && t.length() == 0) {
            return true;
        }
        if (s == null || t == null || s.length() == 0 || t.length() == 0) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char key = s.charAt(i);
            char val = t.charAt(i);
            if (!map.containsKey(key)) {
                if (map.containsValue(val)) {
                    return false;
                }
                //update map
                map.put(key, val);
            } else {   //map containsKey(s.charAt(i)
                if (val != map.get(key)) {
                    return false;
                }
            }
        }
        return true;
    }
}

/**
 * 优化空间Sol：
 * 注意这道题实际上是对ASCII码进行encoding，所以最多不超过256种，可使用INT[]代替hashmap
 */
/**
 * The idea is that we need to map a char to another one, for example,
 * "egg" and "add", we need to constract the mapping 'e' -> 'a' and 'g' -> 'd'.
 * Instead of directly mapping 'e' to 'a', another way is to mark them with same value, for example,
 * 'e' -> 1, 'a'-> 1, and 'g' -> 2, 'd' -> 2, this works same.
 *
 * So we use two arrays here m1 and m2,
 * initialized space is 256 (Since the whole ASCII size is 256, 128 also works here).
 * Traverse the character of both s and t on the same position,
 * if their mapping values in m1 and m2 are different,
 * means they are not mapping correctly, returen false;
 * else we construct the mapping,
 * since m1 and m2 are both initialized as 0, we want to use a new value when i == 0, so i + 1 works here.
 */
class SolIS2 {
    public boolean isIsomorphic(String s, String t) {
        //sanity check
        if (s == null && t == null ||
                s.length() == 0 && t.length() == 0) {
            return true;
        } else if (s == null || t == null ||
                s.length() == 0 || t.length() == 0) {
            return false;
        }
        int[] m1 = new int[256];
        int[] m2 = new int[256];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (m1[s.charAt(i)] != m2[t.charAt(i)]) return false;
            m1[s.charAt(i)] = i + 1;
            m2[t.charAt(i)] = i + 1;
        }
        return true;
    }
}
