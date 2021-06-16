package Graph.Topo;

import java.util.HashMap;
import java.util.Map;

/**解法：BF,不是topo！！
 * clarify：当word相同长度内全部相等，怎样排序，比如apple和apples怎样排序？
 *
题目给出的所有字符都是小写字符，给了一个新的字母表顺序，问，给出的words数组，是不是有序的
直接依次进行判断即可。拿出两个相邻的字符串pre和after，然后判断他们的相同位置的每个字符的顺序，
 如果pre的某个位置小于after，说明这两个字符串是有序的，那么继续判断；
 如果Pre的某个位置大于after，说明不有序，直接返回False。
 如果全部判断完，在同样长度内的顺序都相同，还需要确认是不是长度小的在前面，比如apple需要在apples前面
在遍历完所有的字符串之后都没有返回False，说明是有序的，那么返回True.

*/
public class VerifyingAnAlienDictionary {
    public boolean isAlienSorted(String[] words, String order) {
        //sanity check
        if (words == null || words.length == 0) {
            return true;
        }
        //build map
        Map<Character, Integer> map = new HashMap<>(); //<char, idx>
        for (int i = 0; i < order.length(); i++) {
            map.put(order.charAt(i), i);
        }

        for (int i = 0; i < words.length - 1; i++) {
            String curr = words[i];  //pre char
            String next = words[i + 1]; //after char
            if (!isValid(curr, next, map)) {
                return false;
            }
        }
        return true;
    }

    private boolean isValid(String curr, String next, Map<Character, Integer> map) {
        int len = Math.min(curr.length(), next.length());
        for (int i = 0; i < len; i++) {
            char one = curr.charAt(i);
            char two = next.charAt(i);
            if (one != two) {
                return map.get(one) < map.get(two);
            }
        }
        return curr.length() <= next.length();
    }
}
