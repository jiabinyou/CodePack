package HashMap;

import java.util.HashMap;
import java.util.Map;

public class PalindromePermutation {
    public boolean canPermutePalindrome(String s) {
        //sanity check
        if (s == null || s.length() == 0) {
            return true;
        }
        //build ferq map
        Map<Character, Integer> countMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }
        int count = 0;
        for (Map.Entry<Character,Integer> entry : countMap.entrySet()) {
            if (entry.getValue() % 2 == 1) {
                count++;
                if (count > 1) {  /**单数频率字母 > 1， 返回false，其余都是true*/
                    return false;
                }
            }
        }
        return true;
    }
}

