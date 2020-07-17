package String.Plan5;

import java.util.HashMap;
import java.util.Map;

/**
 * 思路：
 * 使用map装pattern与str中mapping的对应关系<key: char in pattern, value:word，有两种case
 * 1.map中没有key pattern(i),但是已经有了value ： word ,说明有其他char被编码成word了，返回false
 * 2.map中有key pattern(i),但是key pattern(i)对应value != word,说明word被编码成多种char了，返回false
 */
public class WordPattern {
    public boolean wordPattern(String pattern, String str) {
        //sanity check
        if (pattern == null && str == null ||
                pattern.length() == 0 && str.length() == 0) {
            return true;
        }
        if (pattern == null || str == null || pattern.length() == 0 || str.length() == 0) {
            return false;
        }
        String[] words = str.split(" ");
        /**需要提前判断*/
        if (words.length != pattern.length()) {
            return false;
        }
        Map<Character, String> map = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char key = pattern.charAt(i);
            String val = words[i];
            if (!map.containsKey(key)) {  //case 1
                if (map.values().contains(val)) {
                    return false;
                }
                //update map
                map.put(key, val);
            } else {   //map containsKey(s.charAt(i)
                if (!map.get(key).equals(val)) {
                    return false;
                }
            }
        }
        return true;
    }
}

