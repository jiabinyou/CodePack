package String.Plan6;

public class OneEditDistance {
    public boolean isOneEditDistance(String s, String t) {
        //sanity check
        if (s == null || s.length() == 0) {
            return t == null || t.length() == 1;
        }
        if (t == null || t.length() == 0) {
            return s == null || s.length() == 1;
        }

        int m = s.length();
        int n = t.length();
        if (Math.abs(m - n) > 1) {
            return false;
        }
        int p1 = 0;
        int p2 = 0;
        int count = 0; //#edit needed
        while (p1 < m && p2 < n) {
            if (s.charAt(p1) == t.charAt(p2)) { //do not need edit
                p1++;
                p2++;
            } else {  //neeed edit
                count++;
                if (count > 1) {
                    return false;
                }
                /**关键：怎样update pointers*/
                if (m == n) {   //edit：修改
                    p1++;
                    p2++;
                } else if (m < n) {  //edit：s添加一位
                    p2++;
                } else {  //edit：t添加一位
                    p1++;
                }
            }
        }
        count += (m - p1) + (n - p2);
        return count == 1;
    }
}
