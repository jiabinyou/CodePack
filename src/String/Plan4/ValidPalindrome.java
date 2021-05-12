package String.Plan4;

/**
 * Clarify:
 * 包括什么，字母，数字，还是都有？
 * 大小写是否都有，A uppercase是否可以对应a lowercase？
 *
 * 难点1：
 * 如果想用pointers，就必须先转成大写或者小写，因为如果不一致，'A' 和 'a'就返回false了
 * 难点2：
 * 注意这一题valid包括字母和数字
 */
public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        //sanity check
        if (s == null || s.length() == 0) {
            return true;
        }
        s = s.toLowerCase();
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            while (i < j && !isValid(s.charAt(i))) {
                i++;
            }
            while (i < j && !isValid(s.charAt(j))) {
                j--;
            }
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    private boolean isValid(char c) {
        if (c >= 'a' && c <= 'z' || c >= '0' && c <= '9') {
            return true;
        }
        return false;
    }
}

/**也可以直接使用API判断*/
class Solution {
    public boolean isPalindrome(String s) {
        //sanity check
        if (s == null || s.length() == 0) {
            return true;
        }
        s = s.toLowerCase();
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {  //API
                i++;
            }
            while (i < j && !Character.isLetterOrDigit(s.charAt(j)))  {
                j--;
            }
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}