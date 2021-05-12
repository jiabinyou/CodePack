package Graph.DFS3.Plan3;

import java.util.HashSet;
import java.util.Set;

/**sOL: dp
 * 本题忽略大小写，所以先将所有字符转化为小写
 * 根据题目给出的描述，我们稍加思考，可以意识到对于句子s，我们可以拆分成s1和s2，那么根据乘法原理，s的拆分方法数就等于s1的拆分方法数乘以s2的拆分方法数，
 * 由此我们想到动态规划。
 * 先将dict中与s的子串匹配，计算出初始的dp数组
 * 枚举i和j，即s的所有子串，再枚举分割位置k计算出每一个子串的拆分方法数
 *
 * 复杂度分析
 * 空间复杂度：O(N^2)
 * 时间复杂度：O(N^3)
 * /


 /**
 * 优化:我们可以将状态定义成一维数组
 * dp[i] : s[0:i+1]的拆分方法数  (off by 1)
 *
 * induction rule:找i后面所有位置j，查询s[i, j]是否能够拆分出来
 * if (set.contains(s.substring(i, j + 1))) {
 *     dp[j + 1] += dp[i];
 *  }
 *
 * base : dp[0] = 1;   //dp[0]表示空串的拆分方法数
 * res : dp[n]
 *
 * 优化后复杂度分析
 * 空间复杂度：O(N)
 * 时间复杂度：O(N ^ 2 * L) 判断一个子串是否存在于dict使用hash判断，需要 L 的复杂度
 * */
/**过例子：
 * Input:
 * "CatMat"
 * ["Cat", "Mat", "Ca", "tM", "at", "C", "Dog", "og", "Do"]
 * 全部转换小写：
 * Input:
 * "catmat"
 * set: ["cat", "mat", "ca", "tm", "at", "c", "dog", "og", "do"]
 *  idx   0    1   2   3   4   5        dp   0    1   2   3   4   5   6
 *        c    a   t   m   a   t             1
 *        i,j                                     1
 *        i    j                                      1
 *        i        j                                      1
 *        i            j
 *        i                j
 *        i                    j
 *             i,j
 *             i   j                                      2
 *             i      j
 *             i           j
 *             i               j
 *                 i,j
 *                 i   j                                       1
 *                 i       j
 *                 i           j
 *                     i,j
 *                     i    j
 *                     i        j                                        2
 *                          i,j
 *                          i   j                                        3->final res
 *                              i,j
 * */
public class WordBreakIII {
    public int wordBreak3(String s, Set<String> dict) {
        if (s == null ||s.length() == 0 || dict == null || dict.size() == 0) {
            return 0;
        }
        //将字符全部转化为小写，并将dict转换成hash_stet存储，降低判断子串存在性的时间复杂度
        s = s.toLowerCase();
        Set<String> set = new HashSet<String>();
        for (String word : dict) {
            String str = word.toLowerCase();
            set.add(str);
        }

        //dp[i]表示s[0:i](不含s[i])的拆分方法数
        int len = s.length();
        int[] dp = new int[len + 1];

        //dp[0]表示空串的拆分方法数
        dp[0] = 1;

        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                //若存在匹配，则进行状态转移
                if (set.contains(s.substring(i, j + 1))) {
                    dp[j + 1] += dp[i];
                }
            }
        }
        return dp[len];
    }
}
