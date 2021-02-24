package Graph.DFS3.Plan3;

/**
 * 这道题的难点在于cases很多，需要一一分清
 */

import java.util.ArrayList;
import java.util.List;

/**
 * backTracking分解：
 * 层数：input有多少个char，就有多少层
 * 每层branch：当前index(level)的字母开头，后面有多少可选candidate char，就有多少branch
 *
 * 进入下一层方法：i + 1 ！！因为只能从当前层结尾，再往后一个char开始处理
 * iterate all branch方法: 注意！！[index, min（index + 3，s.length() - 1)] 都是valid branch
 *                         因为对于每个recursion point来说，最多只能往后再走三位，除非是已经越界，所以后端点谁小取谁
 */
public class RestoreIPAddresses {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        //sanity check
        if (s == null || s.length() == 0) {
            return res;
        }
        List<String> path = new ArrayList<>();
        backTracking(s, 0, path, res);
        return res;
    }

    private void backTracking(String s, int index, List<String> path, List<String> res) {
        //base case
        if (index == s.length()) {  //定义停止层
            if (path.size() == 4) { //check res
                res.add(buildOneRes(path));
            }
            return;
        }
        if (path.size() > 3) { //pruning:path size > 3位，一定大于255，不需要判断了
            return;
        }
        //recursion
        for (int i = index; i < Math.min(s.length(), index + 3); i++) {
            String curr = s.substring(index, i + 1);
            if (isValid(curr)) {
                path.add(curr);
                backTracking(s, i + 1, path, res);
                path.remove(path.size() - 1);    //recover
            }
        }
    }

    /**非常重要！！判断当前substring是否是合理的一节ip address*/
    private boolean isValid(String s) {
        if (s.charAt(0) == '0') {      //0只能作为单独一位出现
            return s.length() == 1;
        }
        int num = Integer.valueOf(s);
        return num >= 0 && num <= 255;
    }

    private String buildOneRes(List<String> path) {
        StringBuilder sb = new StringBuilder();
        for (String s : path) {
            sb.append(s).append(".");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}

