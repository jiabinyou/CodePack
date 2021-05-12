package String.Plan1;

import java.util.ArrayDeque;
import java.util.Deque;

/**思路：使用stack
 stack为空，或者遇到的是（，,[，{时候直接push），],}
 each round判断stack top是否和new input是相同元素:
 case 1:如果是，就将栈顶元素pop出来
 case 2:如果不同，push进去
 最后只有stack全空时候，才是valid
 TC:O(N)
 sc:o(n)

 e.g.
 "( ) [ ] { }"

 Stack:
 ) ->push ->pop
 )
 ] ->push ->pop
 ]
 } ->push ->pop
 }
 stack empty->True

 */
public class ValidParentheses {
    public boolean isValidParentheses(String s) {
        // sanitycheck
        if (s == null || s.length() == 0) {
            return false;
        }
        char[] arr = s.toCharArray();
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : arr) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else {
                if (stack.isEmpty() || stack.peek() != c) {
                    stack.push(c);
                } else {
                    stack.pop();
                }
            }
        }
        return stack.isEmpty();
    }
}

/**
 * 优化：
 * 其实要想清楚，其实如果想要是valid，必定是每次遇到的右半边符号，stack的栈顶一定要是其对应的左半边符号才可以
 * 比如()[]{]    ->一旦出现这种类型，就不可能valid了
 * 所以each round我们都可以无脑直接pop，一旦发现不匹配直接return false即可
 * */
class ValidParenthesesSol2 {
    public boolean isValidParentheses(String s) {
        // sanitycheck
        if (s == null || s.length() == 0) {
            return false;
        }
        char[] arr = s.toCharArray();
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : arr) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else {
                if (stack.isEmpty() || stack.pop() != c) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
