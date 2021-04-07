package StackQueueDeque;


import java.util.ArrayDeque;
import java.util.Deque;

/**
 这一题和decode string题目类似：
 Sol1.使用一个stack，装数字，装遍历出来的符号
 技巧：手里拿着两个变量
 res：到目前为止已经计算出来的数值
 sign：1表示遇到+， -1表示遇到-
 遇到“ ”：直接跳过；
 遇到符号：+号sign记为1，反之记为-1
 遇到数字：将数字全部解读出来,存入临时tempNum(注意可能多位数字，要累计)；
 因为现在在当前层括号中，拿到所有数字后，可将该数字按照符号dign，加进res中
 遇到（ ：说明此时开启新的一层，将之前手里有的暂时存起来
 将数字tempNum压入stack， tempNum清空
 将符号sign压入stack， sign清空成1
 遇到] :  说明当前轮结束，要将stack中的最后一个sign，倒数第二个num拿出来，叠加到res上。
 res = res * sign + 之前存下的数字;
 */
public class BasicCalculator {
    public int calculate(String s) {
        // sanity check
        if (s == null || s.length() == 0) {
            return 0;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        int res = 0;
        int sign = 1;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                continue;
            } else if (c == '+' || c == '-') {
                sign = c == '+'? 1 : -1;
            } else if (Character.isDigit(c)) {
                int tempNum = c - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    tempNum = tempNum * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                res += tempNum * sign;
            } else if (c == '(') {  //先push数字，再push符号
                stack.push(res);
                stack.push(sign);
                //重置临时变量
                res = 0;
                sign = 1;
            } else {
                res = res * stack.pop() + stack.pop();
            }
        }
        return res;
    }
}

/**
 * Sol2.
 * */









