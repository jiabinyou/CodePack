package String.Plan1;

public class StringToIntegerAtoi {
    public int myAtoi(String str) {
        //首先清除前后空格
        str = str.trim();
        //sanity check
        if (str == null || str.length() == 0) {
            return 0;
        }
        //check if first valid & signal
        int flag = 1;
        int i = 0;
        if (str.charAt(0) == '-') {
            flag = -1;
            i++; //难点：只有第一个字母是正负符号时候，才从第二个位置开始找数字
        } else if (str.charAt(0) == '+') {
            i++;
        } else if (str.charAt(0) < '0' || str.charAt(0) > '9') { //难点：判invalid输入要在判断符号之后
            return 0;
        }
        //iterate
        double res = 0.0; //难点：因为最后res可能超Integer的max和min
        while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            res = res * 10 + (str.charAt(i) - '0');
            i++;
        }
        //post-processing
        res *= flag;
        if (res < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        } else if (res > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int)res;
    }
}



