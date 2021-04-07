package StackQueueDeque;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 几种操作：
 .    the current directory
 ..   moves the directory up a level
 /    result must always begin with /,
 must be only a single / between two directory names,
 must not end with a trailing /

 理解题目：
 出题的前提是，input path都是实际用到，不是乱给，是没有错误的。
 比如出现abc//def, 不是给错了连写了两个/，而是说明当前两个/之间的目录名是空的。

 思路：
 用stack存放结果即可，模拟input究竟走到了哪一层
 技巧：
 因为/非常多，case也多，所以实际处理时候我们以/为基准，将input拆分成一个一个字符再分别进行判断
 去遍历分开后的每个单独的string，判断：

 遇到正常的目录名, 入栈
 遇到 '.' 或 空名称 (即对应 "//"分出来的) 则忽略，不入栈
 遇到 ".." 则从栈顶弹出一个元素 (如果栈为空则不弹栈, 对应 "/../")

 最后将栈中的元素以 '/' 连接得到结果.
 */
public class SimplifyPath {
    public String simplifyPath(String path) {
        Deque<String> stack = new ArrayDeque<>();
        String[] paths= path.split("/");
        for(int i = 0; i < paths.length; i++) {
            if(paths[i].equals("")) { //说明遇到空目录名，不入栈
                continue;
            }
            if(paths[i].equals("..")) { //遇到 ".." 则从栈顶弹出一个元素 (如果栈为空则不弹栈, 对应 "/../")
                if(!stack.isEmpty()) {
                    stack.pop();
                }
            }else if (paths[i].equals(".")) { //遇到 '.'，无效不入栈
                continue;
            } else {
                stack.push(paths[i]);
            }
        }
        //build res，将栈中的元素以 '/' 连接
        StringBuilder result = new StringBuilder();
        while(!stack.isEmpty()) {
            result.insert(0,"/"+ stack.pop()); //要倒着连接起来
        }
        if(result.length() == 0) {
            return "/";
        }
        return result.toString();
    }
}

