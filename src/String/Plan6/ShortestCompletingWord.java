package String.Plan6;

/**
 找到license plate最好能够所有的字母以及出现的频率，在words中第一个符合的就是要求的word
 */

import java.util.HashMap;
import java.util.Map;

/**
 Sol1.使用hashMap
 思路：可以制作license plate的freq map，将words中每个word与freq map作比较，全部符合的即为结果
 */

/**
 * 缺点：
 * 这个解法时间复杂度太高了，因为每个word，都要准被一个全新的licensePlate的freq map，时间复杂度是m*n平方级别起步
 */
public class ShortestCompletingWord {
    public String shortestCompletingWord(String licensePlate, String[] words) {
        licensePlate = licensePlate.toLowerCase();  //words中全部小写，需转换
        int minLen = Integer.MAX_VALUE;  //找到所有符合要求中的最短的长度
        String res = ""; //需要得到的是所有符合要求中的长度最短，并且最先出现的word
        for (int i = 0; i < words.length; i++) {
            //build freq map for licensePlate
            Map<Character, Integer> map = new HashMap<>();
            for (char c : licensePlate.toCharArray()) {
                if (Character.isLetter(c)) {
                    map.put(c, map.getOrDefault(c, 0) + 1);
                }
            }
            //check if the word is target
            int targetLen = map.size();
            int matchedNum = 0;
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                Integer freq = map.get(c);
                if (freq != null) {
                    if (freq == 1) {
                        matchedNum++;
                    }
                    map.put(c, freq - 1);
                }
            }
            if (matchedNum == targetLen && word.length() < minLen) {
                res = word;
                minLen = word.length();
            }
        }
        return res;
    }
}

/**
 * Sol1b.
 * 上面sol的主要缺点就是遍历每个word时候，都要制作新的freq map，
 * 学会clone hashmap的方法，就可以每次clone即可
 */
class SolLKF1b {
    public String shortestCompletingWord(String licensePlate, String[] words) {
        licensePlate = licensePlate.toLowerCase();  //words中全部小写，需转换
        //buld freq map for licensePlate
        Map<Character, Integer> map = new HashMap<>();
        for (char c : licensePlate.toCharArray()) {
            if (Character.isLetter(c)) {
                map.put(c, map.getOrDefault(c, 0) + 1);
            }
        }
        int targetLen = map.size();
        int matchedNum = 0;
        int minLen = Integer.MAX_VALUE;  //找到所有符合要求中的最短的长度

        String res = ""; //需要得到的是所有符合要求中的长度最短，并且最先出现的word
        for (int i = 0; i < words.length; i++) {
            //check if the word is target
            Map<Character, Integer> curMap = new HashMap();  //为每个word制作新的map
            curMap.putAll(map);   /**clone hashmap方法*/
            matchedNum = 0;  //重置
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                Integer freq = curMap.get(c);
                if (freq != null) {
                    if (freq == 1) {
                        matchedNum++;
                    }
                    curMap.put(c, freq - 1);
                }
            }
            if (matchedNum == targetLen && word.length() < minLen) {
                res = word;
                minLen = word.length();
            }
        }
        return res;
    }
}

/**
 * Sol2.使用int[]
 * 优化思路：因为freqmap中key装的只有26个小写字母，所以int[]完全够用：<idx: char - 'a', val: freq>
 */
class SolSCW2 {
    public String shortestCompletingWord(String licensePlate, String[] words) {
        licensePlate = licensePlate.toLowerCase();  //words中全部小写，需转换
        //buld freq map for licensePlate
        int targetCount = 0;  /**注意：用int[], 只能记录要匹配的字母出现总次数最方便*/
        int[] map = new int[26];
        for (char c : licensePlate.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                map[c - 'a']++;
                targetCount++;
            }
        }
        int matchedNum = 0;
        int minLen = Integer.MAX_VALUE;  //找到所有符合要求中的最短的长度

        String res = ""; //需要得到的是所有符合要求中的长度最短，并且最先出现的word
        for (int i = 0; i < words.length; i++) {
            //check if the word is target
            int[] curMap = map.clone();  //为每个word制作新的map /**clone int[]方法*/
            matchedNum = 0;  //重置
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                if (curMap[c - 'a'] > 0) {
                    matchedNum++;
                    curMap[c - 'a']--;
                }
            }

            if (matchedNum == targetCount && word.length() < minLen) {
                res = word;
                minLen = word.length();
            }
        }
        return res;
    }
}