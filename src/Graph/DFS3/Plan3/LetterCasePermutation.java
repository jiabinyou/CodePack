package Graph.DFS3.Plan3;

import java.util.ArrayList;
import java.util.List;

/**
 Sol1.pick or not pick
 对于每个char，都有变或不变两种选择,path可以直接使用S本身
 */
public class LetterCasePermutation {
    public List<String> letterCasePermutation(String S) {
        List<String> res = new ArrayList<>();
        //sanity check
        if (S == null || S.length() == 0) {
            return res;
        }
        char[] arr = S.toCharArray();
        backTracking(arr, 0, res);
        return res;
    }

    private void backTracking(char[] arr, int level, List<String> res) {
        //base case
        if (level == arr.length) {
            res.add(new String(arr));
            return;
        }
        if (Character.isDigit(arr[level])) {
            backTracking(arr, level + 1, res);
        } else {
            //exchange
            arr[level] = exchange(arr, level);
            backTracking(arr, level + 1, res);
            arr[level] = exchange(arr, level);    //recover
            //not exchange
            backTracking(arr, level + 1, res);
        }
    }

    //大小写交换
    private char exchange(char[] arr, int level) {
        char c = arr[level];
        if (c >= 'a' && c <= 'z') {
            c -= 32;
        } else {
            c += 32;
        }
        return c;
    }
}
