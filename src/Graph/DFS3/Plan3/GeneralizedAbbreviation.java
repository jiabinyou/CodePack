package Graph.DFS3.Plan3;
import java.util.ArrayList;
import java.util.List;
/**
 * Sol1.backTracking
 * each recursion point two branch:
 * abbreviation or not abbreviation
 */

/**
 * abbreviation: 直接跳过当前char，进入下一层recursion，同时count+1 （count：连续abbreviation的字母个数)
 * not abbreviation:将当前的char放入路径中
 */

/**
 * for every letter, we can either choose to abbreviate it or not
 * "word",
 *   for "w"
 *     abbreviate :  "1ord",
 * 									for "o"
 * 									        abbreviate : "2rd",
 * 											not:               "1o1d"
 *      not :         "w1rd"
 *
 * 	 so we need a argument called length to record current abbreviate length
 */
public class GeneralizedAbbreviation {
    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<>();
        //sanity check
        if (word == null || word.length() == 0) {
            res.add("");
            return res;
        }
        StringBuilder sb = new StringBuilder();
        backTracking(word, 0, sb, 0, res);
        return res;
    }

    private void backTracking(String word, int index, StringBuilder sb, int count, List<String> res) {

        //base case
        if (index == word.length()) {
            if (count > 0) {
                sb.append(count);
            }
            res.add(sb.toString());
            return;
        }
        /**recursion*/
        //not abbreviation
        int len = sb.length();
        if (count > 0) {
            sb.append(count);
        }
        sb.append(word.charAt(index));
        backTracking(word, index + 1, sb, 0, res); /**难点：count需要重置0*/
        sb.setLength(len);  /**难点：因为此时sb要恢复的可能不止一位，所以记录len*/
        //abbreviation
        backTracking(word, index + 1, sb, count + 1, res); //count需要+1
    }
}

/**
 * path信息可以使用String，就不需要recover了，但是只能先进行abbreviation分支
 */
class SolGA1b {
    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<>();
        //sanity check
        if (word == null || word.length() == 0) {
            res.add("");
            return res;
        }
        StringBuilder sb = new StringBuilder();
        backTracking(word, 0, "", 0, res);
        return res;
    }

    private void backTracking(String word, int index, String s, int count, List<String> res) {

        //base case
        if (index == word.length()) {
            if (count > 0) {
                s += count;
            }
            res.add(s);
            return;
        }
        /**recursion*/
        //abbreviation
        backTracking(word, index + 1, s, count + 1, res); //count需要+1
        //not abbreviation
        if (count > 0) {
            s += count;
        }
        s += word.charAt(index);
        backTracking(word, index + 1, s, 0, res); /**难点：count需要重置0*/
    }
}

/**推荐写法！！！！
 * Sol 2:
 * build up solution 1 char by 1 char, only when previous char is not abbreviated we can add
 * number to the solution
 */
/** backTracking分解：
        * 层数：input str有多少个char，就有多少层
        * 每层的branch：分两种情况
        *     case 1.前面不是abbr(即前面一位是字母)
        *            那么当前index(level)的字母开头，全部是可以abbr的字母，并且有多少abbr，就可以放数字
        *            进入下一层方法：i + 1 ！！因为只能从当前层String curr结尾，再往后一个char开始处理
        *     case 1.前面是abbr(即前面一位是数字)
        *            那么当前index(level)的字母开头， 只能放字母，并且只能走一位
        *             进入下一层方法：index + 1
        *
 */
class SolGA2 {
    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<>();
        //sanity check
        if (word == null || word.length() == 0) {
            res.add("");
            return res;
        }
        StringBuilder sb = new StringBuilder();
        backTracking(word, 0, sb, res, false);
        return res;
    }

    private void backTracking(String word, int index, StringBuilder sb, List<String> res, boolean prevAbbr) {
        //base case
        if (index == word.length()) {
            res.add(sb.toString());
            return;
        }
        /**recursion*/
        int len = sb.length();
        if (!prevAbbr) {
            for (int i = index; i < word.length(); i++) {
                sb.append(i - index + 1);  //数字
                backTracking(word, i + 1, sb, res, true); //count需要+1
                sb.setLength(len);
            }
        }
        //prev not abbreviation
        sb.append(word.charAt(index));
        backTracking(word, index + 1, sb, res, false);
        sb.setLength(len);
    }
}

