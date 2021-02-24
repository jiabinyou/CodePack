package HashMap;

public class LongestPalindrome {
    public int longestPalindrome(String s) {
        //sanity check
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] count = new int[128];
        for (char c : s.toCharArray()) {
            count[c]++;
        }
        int result = 0;
        for (int i : count) {
            result += i / 2 * 2;  //每中字母，最多能够拿出freq  / 2 * 2来组成plindrome
        }
        return result == s.length() ? result : result + 1; //result ！= s.length()，说明有超多一个的落单字母，可以拿出一个
    }
}

