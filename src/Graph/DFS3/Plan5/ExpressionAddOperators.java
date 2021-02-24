package Graph.DFS3.Plan5;

/**
 * index : the current digit we're looking at
 * prevVal: 前一位数字
 *
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 这题的难点：既要计算每个sol的值，又要recover all path
 * 计算+， - ： l to r依次计算即可
 *          e.g. 10 + 2 - 5 = 12 - 5 = 7
 * 计算* ：需要recover from preVal （难点！）
 *          e.g. 10 + 2 * 4 = 12 - 2 + (2 * 4) = preRes - preVal + (prevVal * curVal)
 *          e.g. 10 - 2 * 4 = 8 - (-2) + ((-2) * 4) = preRes - preVal + (prevVal * curVal)
 *          这样计算的目的是保证，一路在遇到+， -时候，依然可以顺次计算，遇到*再使用记录下来的preVal
 *          单独处理即可.
 *          注意：这里如果上一次遇到的operand是-，那么对应的preVal应该记录负数值，即preVal = - actualVal
 *          这样一来，所有计算可以转化成+运算，保证了一致性
 */
public class ExpressionAddOperators {
    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        if (num == null || num.length() == 0) {
            return result;
        }
        StringBuilder sb = new StringBuilder();
        dfs(num, target, 0, 0, 0, sb, result);
        return result;
    }

    private void dfs(String num, int target, int index, long preRes, long preVal, StringBuilder sb, List<String> result) {
        if (index == num.length()) {
            if (preRes == target) {
                result.add(sb.toString());
            }
            return;
        }
        for (int i = index; i < num.length(); i++) {
            if (i != index && num.charAt(index) == '0') {
                break;
            }
            long cur = Long.valueOf(num.substring(index, i + 1));  /**每次brach所处理可能不止一位，所有后面用len recover*/
            int len = sb.length();
            if (index == 0) {
                sb.append(cur); /**如果是第一位，没有operator，应该特殊处理*/
                dfs(num, target, i + 1, cur, cur, sb, result);
                sb.setLength(len);
            } else {
                //+ branch
                sb.append("+").append(cur);
                dfs(num, target, i + 1, preRes + cur, cur, sb, result);
                sb.setLength(len);
                //- branch
                sb.append("-").append(cur);
                dfs(num, target, i + 1, preRes - cur, -cur, sb, result);
                sb.setLength(len);
                //* branch
                sb.append("*").append(cur);
                dfs(num, target, i + 1, preRes - preVal + cur * preVal, preVal * cur, sb, result);
                sb.setLength(len);
            }
        }
    }
}

