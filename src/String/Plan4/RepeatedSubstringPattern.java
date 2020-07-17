package String.Plan4;

/**问s是否可以使某一个substring重复多次得来*/
/**
 思路：
 因为至少要重复两次，所以可以在s的[0, length/2]之间，分别取substring【0，i】,重复组成一个s长度的新string，检查是否和s相同
 */
public class RepeatedSubstringPattern {
    public boolean repeatedSubstringPattern(String s) {
        //sanity check
        if (s == null || s.length() == 0) {
            return true;
        }
        if (s.length() == 1) {
            return false;
        }
        int len = s.length();
        /**注意：i只能从1开始，才能取到有效的substring，并且要包括len/2这一位。
         因为substring[0,len/2-1]，即s.substring(0, len/2)*/
        for (int i = 1; i <= len / 2; i++) {
            if (len % i == 0) {  //只有能以长度i重复到s长度，才是可能的candidate
                String subString = s.substring(0, i);
                int repeatNum = len / i;
                StringBuilder sb = new StringBuilder();
                while (repeatNum > 0) {
                    sb.append(subString);
                    repeatNum--;
                }
                if (sb.toString().equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }
}


