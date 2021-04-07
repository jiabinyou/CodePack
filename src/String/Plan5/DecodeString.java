package String.Plan5;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
Sol1.使用两个stack，一个num：装数字，一个str：装遍历出来的string
        遇到数字：将数字解读出来,存入临时tempNum(注意可能多位数字，要累计)
        遇到字母：将字母存入临时tempStr
        遇到[ ： 说明数字读完/str读完，准备数字和string：
        将数字tempNum压入num， tempNum清空
        将字符串tempStr压入str， tempStr清空
        遇到] :  说明当前轮结束，要连着数字准备字母：
        准备【strCombin】，存放我们现在手里有的所有东西：
        1.将str上一个string取出，放入strCombin中（这是我们前面已经做好所有部分）
        2.将num中上一个数字取出，，再将tempStr重复num.pop()遍，放入strCombin中
        3.（重点！！）-》将strCombin再传递给tempStr（因为这个strCombin作为整体，在更高一层大括号中可能被再次重复）
*/
class Solution {
    public String decodeString(String s) {
        // sanity check
        if (s == null || s.length() == 0) {
            return "";
        }

        Deque<Integer> num = new ArrayDeque<>();
        Deque<String> str = new ArrayDeque<>();
        StringBuilder tempStr = new StringBuilder();
        int tempNum = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                tempNum = tempNum * 10 + c - '0';
            } else if (Character.isLetter(c)) {
                tempStr.append(c);
            } else if (c == '[') {
                num.push(tempNum);
                str.push(tempStr.toString());
                tempNum = 0;
                tempStr.setLength(0);
                //tempStr = new StringBuilder();
            } else {  //c == ']'
                StringBuilder strCombin = new StringBuilder();
                //1.将str上一个string取出，放入strCombin中,
                String lastStr = str.pop();
                strCombin.append(lastStr);
                //2.将num中上一个数字取出，，再将tempStr重复num.pop()遍，放入strCombin中
                int lastNum = num.pop();
                for (int j = 0; j < lastNum; j++) {
                    strCombin.append(tempStr);
                }
                tempStr = strCombin;  //3.将strCombin再传递给tempStr
            }
        }
        return tempStr.toString();
    }
}

/**
 * Sol2.使用一个queue， 使用DFS
 *         遇到数字：将数字解读出来,存入临时tempNum(注意可能多位数字，要累计)
 *         遇到字母：将字母存入临时tempStr
 *         遇到[ ： 不用做任何事情，跳过
 *         遇到] :  说明当前轮结束，要连着数字准备字母：
 *                 使用DFS,将当前queue作为input送进DFS计算，计算结果是当前数字后面，要被重复的string
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
        return DFS(queue);
    }

    public String DFS(Queue<Character> queue) {
        StringBuilder tempStr = new StringBuilder();  //sb-->解法一种的tempStr
        int tempNum = 0;
        while (!queue.isEmpty()) {
            char c= queue.poll();
            if (Character.isDigit(c)) {
                tempNum = tempNum * 10 + c - '0';
            } else if (c == '[') {        //一个子结果开始，计算后要重置num
                String repeatSeed = DFS(queue);   //
                for (int i = 0; i < tempNum; i++) {
                    tempStr.append(repeatSeed);
                }
                tempNum = 0;
            } else if (c == ']') {
                break;
            } else {
                tempStr.append(c);
            }
        }
        return tempStr.toString();
    }
}
