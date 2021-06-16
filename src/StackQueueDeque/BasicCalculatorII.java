package StackQueueDeque;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * use a stack to track the numbers who have been met.
 * Use a global var "preSign" to remember the latest operator we have met,
 * a global "var" num for the number we are dealing with, and a stack to accommodate the numbers.
 * Go through the string by characters, and the rule is:
 * 分成不同case处理：
 * 1.If current character is blank, continue to next step.
 * 2.If current character is digit, multiply num with 10 and add current value to num
 * 3.If preSign is '+' or '-', we are not sure about the operating because there might be a operator with higher priority in the future.
 *   So add num or -num to the stack， we will come back later when come to its priority
 * 4.If preSign is '*' or '/', we know they have highest priority , hence can perform the operating immediately.
 *   So get the latest number of the stack, perform the operating and push the result back to the stack:
 *
 * At last, we have a stack containing a couple of numbers and all of them have the same operator '+'.
 * Just sum them up and return the result
 *
 * TC:O(N), N is character length of input string
 * SC:O(N)
 * */
public class BasicCalculatorII {
    public int calculate(String s) {
        //sanity check
        if (s == null || s.length() == 0) {
            return 0;
        }
        int num = 0;
        char preSign = '+';
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                continue;
            } else if (Character.isDigit(c)) {
                num = c - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                if (preSign == '+') {
                    stack.push(num);
                } else if (preSign == '-') {
                    stack.push(-num);
                } else if (preSign == '*') {
                    stack.push(stack.pop() * num);
                } else{
                    stack.push(stack.pop() / num);
                }
            } else {
                preSign = c;
            }
        }
        //build res
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
}
