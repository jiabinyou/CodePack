package String.Plan5;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Sol1.使用queue， DFS
 */
public class DecodeString {
    public String decodeString(String s) {
        //sanity check
        if (s == null || s.length() == 0) {
            return "";
        }
        Queue<Character> queue = new LinkedList<>();
        for (char c : s.toCharArray()) {
            queue.offer(c);
        }
        return helper(queue);
    }

    public String helper(Queue<Character> queue) {
        StringBuilder sb = new StringBuilder();
        int num = 0;
        while (!queue.isEmpty()) {
            char c= queue.poll();
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if (c == '[') {        //一个子结果开始，计算后要重置num
                String sub = helper(queue);
                for (int i = 0; i < num; i++) {
                    sb.append(sub);
                }
                num = 0;
            } else if (c == ']') {
                break;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}

/**
 * Sol2.使用两个stack（类似calculator）
 */
class Solution {
    public String decodeString(String s) {
        //sanity check
        if (s == null || s.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Deque<String> strStack = new ArrayDeque<>();  //放string
        Deque<Integer> counter = new ArrayDeque<>();  //放num
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                count = count * 10 + c - '0';
            } else if (Character.isLetter(c)) {
                sb.append(c);
            } else if (c == '[') {  //遇到[，将当前结果放进stack，并重置sb和count
                if (count > 0) {
                    counter.push(count);
                }
                strStack.push(sb.toString());
                sb.setLength(0);
                count = 0;
            } else {  //遇到]，从stack中拿出计算
                StringBuilder tmp = new StringBuilder().append(strStack.pop());
                int times = counter.pop();
                for (int j = 0; j < times; j++) {
                    tmp.append(sb);
                }
                sb = tmp;
            }
        }
        return sb.toString();
    }
}
