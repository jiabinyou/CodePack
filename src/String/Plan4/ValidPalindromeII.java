package String.Plan4;

/**
 *这道题的字符串中只含有小写字母，而且这道题允许删除一个字符，
 * 那么当遇到不匹配的时候，我们到底是删除左边的字符，还是右边的字符呢，我们的做法是两种情况都要算一遍，
 * 只要有一种能返回true，那么结果就返回true
 * */

/**没有字符范围限制，什么都需要比较*/
public class ValidPalindromeII {
    public boolean validPalindrome(String s) {
        //sanity check
        if (s == null || s.length() == 0) {
            return true;
        }
        s = s.toLowerCase();
        int i = 0;
        int j = s.length() - 1;
        int deleteNum = 0;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return isPanlindrome(s, i + 1, j) || isPanlindrome(s, i, j - 1);
            }
            i++;
            j--;
        }
        return true;
    }

    private boolean isPanlindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
