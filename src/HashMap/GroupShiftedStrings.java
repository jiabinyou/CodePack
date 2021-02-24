package HashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 题目解释：
 * 这一题是检查word各个char之间的offset，比如：
 * abc, bcd, xyz,这三个word几个char偏移量都是[1, 1]是一致的，所以是一组
 *
 * az偏移量pattern是[25]
 * ba偏移量pattern是[-1] -->但是字符是每隔26就一循环，所以，对于-1，你一旦加上26就是25
 *
 * Sol:我们可以把word各个char之间偏移量做成pattern，放在map中
 * <pattern, list of string>,这样pattern一致的就对应同一个key
 * 注意：对于offset < 0的，+26全部转正即可
 *
 */
public class GroupShiftedStrings {
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> result = new ArrayList<>();
        if (strings == null || strings.length == 0) {
            return result;
        }
        Map<String, List<String>> map = new HashMap<>(); //<pattern, list of string>
        for (String s : strings) {
            String pattern = getPattern(s);
            map.putIfAbsent(pattern, new ArrayList<>());
            map.get(pattern).add(s);
        }
        return new ArrayList<>(map.values());
    }

    private String getPattern(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < s.length(); i++) {
            int offset = s.charAt(i) - s.charAt(i - 1);
            sb.append(offset < 0 ? offset + 26 : offset).append(",");
        }
        return sb.toString();
    }
}

