package StackQueueDeque;

import java.util.Stack;

/**
 * The only difference between the solution of Basic Calculator II is :
 * we added a recursive call of our function when we encounter a parenthese.
 * Let the recursion calculate the number for us and the deeper level of recursion will increment to the next of pairing parenthese.
 * */

/**
 * use a stack to track the numbers who have been met.
 * Use a global var "preSign" to remember the latest operator we have met,
 * a global "var" num for the number we are dealing with, and a stack to accommodate the numbers.
 * Go through the string by characters, and the rule is:
 * 分成不同case处理：
 * 1.If current character is blank, continue to next step.
 * 2.If current character is '(', start recursion:
 *   each round find the most inner layer (), consider them as a new expression to be evaluated,
 *   hence use i mark most inner ( idx, use "count" find length between most inner '(' and most inner ')';
 *   regard substring（i+1, i+count) as a new operator for this new sub-expression
 * 3.If current character is digit, multiply num with 10 and add current value to num
 * 4.If preSign is '+' or '-', we are not sure about the operating because there might be a operator with higher priority in the future.
 *   So add num or -num to the stack， we will come back later when come to its priority
 * 5.If preSign is '*' or '/', we know they have highest priority , hence can perform the operating immediately.
 *   So get the latest number of the stack, perform the operating and push the result back to the stack:
 *
 * At last, we have a stack containing a couple of numbers and all of them have the same operator '+'.
 * Just sum them up and return the result
 *
 * TC:O(N), N is character length of input string 虽然有recursion，但是recursion只有一个branch，类似linkedlist recursiion，最终recursionTC相当于一遍遍历
 * SC:O(N)
 * */
public class BasicCalculatorIII {
    public int calculate(String s) {
        Stack<Long> stack = new Stack<Long>();
        int len = s.length();
        int i = 0;
        int res = 0;
        long num = 0;
        char sign = '+';
        while(i < len) {
            if (s.charAt(i) == ' ') {
                continue;
            }
            if(Character.isDigit(s.charAt(i))) {
                num = num * 10 + (s.charAt(i) - '0');
            }
            if(s.charAt(i) == '(') {
                int count = countValid(s.substring(i));
                num = calculate(s.substring(i + 1, i + count));
                i += count;
            }

            if(i >= len - 1 || !Character.isDigit(s.charAt(i))) {
                if(sign == '+')  stack.push(num);
                else if(sign == '-') stack.push(-num);
                else if(sign == '*') stack.push(stack.pop() * num);
                else if(sign == '/') stack.push(stack.pop() / num);
                num = 0;
                sign = s.charAt(i);
            }
            i++;
        }

        while(!stack.isEmpty()) {
            res += stack.pop();
        }

        return (int)res;
    }

    private int countValid(String s){
        int counter = 0;
        int i = 0;
        while(i < s.length()) {
            if(s.charAt(i) =='(') counter ++;
            else if(s.charAt(i) ==')') counter --;
            if(counter == 0) break;
            i++;
        }
        return i;
    }
}