package Graph.DFS3.Plan5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DifferentWaysToAddParentheses {
    public List<Integer> diffWaysToCompute(String input) {
        Map<String, List<Integer>> map = new HashMap<>();
        return compute(input, map);
    }

    /**技巧：使用return value的backTracking*/
    private List<Integer> compute(String input, Map<String, List<Integer>> map) {
        //base case
        if (map.containsKey(input)) {  //pruning: 做memo，遇到之前计算过的string，直接拿到结果
            return map.get(input);
        }
        List<Integer> res = new ArrayList<>();
        if (input == null) {   //定义停止层
            return res;
        }
        //recursion
        for (int i = 0; i < input.length(); i++) {
            if (isOperator(input.charAt(i))) {   //说明两遍可以计算
                List<Integer> left = compute(input.substring(0, i), map);
                List<Integer> right = compute(input.substring(i + 1), map);
                for (int i1: left) {
                    for (int i2: right) {
                        res.add(calculate(input.charAt(i), i1, i2));
                    }
                }
            }
        }
        if (res.size() == 0) { /**说明input本身就是一个数字*/
            res.add(Integer.parseInt(input));
        }
        map.put(input, res);
        return res;
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*';
    }

    private int calculate(char c, int i, int j) {
        if (c == '+') {
            return i + j;
        }
        if (c == '-') {
            return i - j;
        }
        return i * j;
    }

}
