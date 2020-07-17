package String.Plan6;

/**
 * Sol1
 * 从左向右遍历：
 * len % K != 0,就先把余数个数的char放进字母中
 */
public class LicenseKeyFormatting {
    public String licenseKeyFormatting(String S, int K) {
        //sanity check
        if (S == null || S.length() == 0 || K == 0) {
            return "";
        }
        String temp = "";
        S = S.toUpperCase(); /**注意：输出需全部转成大写字母*/
        for (char c : S.toCharArray()) {
            if (c != '-') {
                temp += c;
            }
        }
        /**难点：如果没有char，也属于corner case*/
        if (temp.length() == 0) {
            return "";
        }

        int len = temp.length();
        int quo = len / K;   //商
        int rem = len % K;   //余数
        StringBuilder sb = new StringBuilder();
        if (rem != 0) {
            for (int i = 0; i < rem; i++) {
                sb.append(temp.charAt(i));
            }
            sb.append('-');
            //以K为单位，放入字母和'-''
            for (int i = quo; i > 0; i--) {
                sb.append(temp.substring(rem + (quo - i) * K, rem + (quo - i + 1) * K));
                sb.append('-');
            }
            sb.deleteCharAt(sb.length() - 1);
        } else {
            for (int i = quo; i > 0; i--) {
                sb.append(temp.substring((quo - i) * K, (quo - i + 1) * K));
                sb.append('-');
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}

/**
 * Sol2.从右向左遍历
 * 这个解法比较难想到，但是能够大大简化代码
 * 因为可以最后处理len % K != 0情况，从右向左剩下部分，两种case可以放在一起处理
 */
class SolLKF2 {
    public String licenseKeyFormatting(String S, int K) {
        //sanity check
        if (S == null || S.length() == 0 || K == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = S.length() - 1; i >= 0; i--) {
            char c = S.charAt(i);
            if (c == '-') {
                continue;
            }
            if (c >= 'a' && c <= 'z') {
                c -= 32;
            }
            sb.append(c);
            count++;
            if (count % K == 0) {
                sb.append("-");
            }
        }
        if (sb.length() != 0 && sb.charAt(sb.length() - 1) == '-') {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.reverse().toString();
    }
}
