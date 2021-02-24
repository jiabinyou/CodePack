package Graph.DFS3.Plan3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 思路：
 * 本质：find all combination
 * backTracking分解：
 * 层数：input有多少个数字，就有多少层（0, 1是invalid数字）
 * 每层branch：每个数字能decode多少字母，就要多少branch
 *
 * 进入下一层方法：index + 1 （index即层数）
 * iterate all branch方法:可制作map存放数字与字母对应关系<Integer, String>
 */
public class LetterCombinationsOfAPhoneNumber {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        //sanity check
        if (digits == null || digits.length() == 0) {
            return res;
        }
        //build map for <phone number, all char>
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        //backtracking on input digits
        StringBuilder sb = new StringBuilder();
        backTracking(digits, 0, sb, map, res);
        return res;
    }

    private void backTracking(String digits, int index, StringBuilder sb, Map<Character, String> map, List<String> res) {
        //base case
        if (index == digits.length()) {
            res.add(sb.toString());
            return;
        }
        //recursion
        char c = digits.charAt(index);
        for (char candidate : map.get(c).toCharArray()) {
            sb.append(candidate);
            backTracking(digits, index + 1, sb, map, res);
            sb.deleteCharAt(sb.length() - 1); //recover
        }
    }
}




