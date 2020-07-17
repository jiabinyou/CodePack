package String.Plan5;

import java.util.List;

/**
 本质：检测第i行中第j个字符是否等于第j行的第i个字符
 难点：由于每行word的长度可能不等，考察是否能将边界条件考虑全面，即判断“对应位置”是否有字符存在，一旦OUT
 OF BOUND，或者对应字符存在，但是不相等，就返回false。
 遍历位置：(i,j)  --> 第i行第j列
 对应位置: (j, i) --> 第j行第i列
 第j行第i列边界条件：
 j >= row；  //行数j不能超过总行数words.size()
 i >= words.get(j).length()； //列数i不能超过第j行word长度
 */
public class ValidWordSquare {
    public boolean validWordSquare(List<String> words) {
        //sanity check
        if (words == null || words.size() == 0) {
            return true;
        }
        int row = words.size();
        for (int i = 0; i < row; i++) {
            int col = words.get(i).length();  //遍历第i行每一列
            for (int j = 0; j < col; j++) {
                if (j >= row || i >= words.get(j).length() || words.get(i).charAt(j) != words.get(j).charAt(i)) {
                    return false;
                }
            }
        }
        return true;
    }
}
