package String.Plan4;

public class IsSubsequence {
    /**注意题目中：without disturbing the relative positions of the remaining characters
     这就意味着s出现相对顺序不能换，所以不需要转换成hashCode[], 直接用two pointer一遍不回头就够了*/
    public boolean isSubsequence(String s, String t) {
        //sanity check
        if (s == null && t == null || s.length() == 0 && t.length() == 0) {
            return true;
        }
        if (t == null || t.length() == 0 || s.length() > t.length()) {
            return false;
        }
        int i = 0;  //遍历s
        int j = 0;  //遍历t
        while (j < t.length()) {
            /**这里也需要i不超过边界，因为如果恰s与t同长，i会走到index s.length(), 则会out of bound*/
            if (i < s.length() && j < t.length() && s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == s.length();
    }
}

