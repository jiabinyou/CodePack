package String.Plan5;

/**
 * 难点1：
 * string, int, char转换
 * 难点2：
 * 在原数组上抄写结果（类似s, f思想）
 */
public class StringCompression {
    public int compress(char[] chars) {
        //sanity check
        if (chars == null || chars.length == 0) {
            return 0;
        }
        int count = 0;  //每次count数字>1，才需要添加数字
        int slow = 0; //slow - 1 : last pos of remaind res
        int fast = 0;
        while (fast < chars.length) {
            chars[slow++] = chars[fast]; //先抄字母
            count = 0;
            while (fast < chars.length && chars[fast] == chars[slow - 1]) {
                fast++;
                count++;
            }
            if (count > 1) {
                String s = String.valueOf(count); //可能大于一位
                for (char c : s.toCharArray()) {
                    chars[slow++] = c;
                }
            }
        }
        return slow;
    }
}