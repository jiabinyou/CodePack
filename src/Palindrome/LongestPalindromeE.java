package Palindrome;

/**ASCII 码一共规定了128个字符的编码
 */
public class LongestPalindromeE {
    public int longestPalindrome(String s) {
        //sanity check
        if (s.length() == 0) {
            return 0;
        }
        int[] count = new int[128];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            count[c]++;
        }
        int res = 0;
        for (int i = 0; i < count.length; i++) {
            res += count[i] /2 * 2; //每中字母，最多能够拿出freq  / 2 * 2来组成plindrome
        }
        return res == s.length() ? res : res + 1; //result ！= s.length()，说明有超多一个的落单字母，可以拿出一个
    }
}

