package String.Plan4;

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
