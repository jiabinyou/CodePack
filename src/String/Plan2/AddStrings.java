package String.Plan2;

/**与AddBinary题目唯一区别：换成十进制运算*/
public class AddStrings {
    public String addStrings(String num1, String num2) {
        //sanity check
        if (num1 == null || num1.length() == 0) {
            return num2;
        }
        if (num2 == null || num2.length() == 0) {
            return num1;
        }

        int carrier = 0;
        int i = num1.length() - 1;  //从低位开始运算
        int j = num2.length() - 1;
        int sum = 0;
        StringBuilder sb = new  StringBuilder();
        while (i >= 0 || j >= 0) {
            /**需要加括号，不然运算顺序不对*/
            sum = (i >= 0 ? num1.charAt(i) - '0': 0) +
                    (j >= 0 ? num2.charAt(j) - '0': 0) +
                    carrier;
            sb.append(sum % 10);
            carrier = sum / 10;
            //update pointer
            i--;
            j--;
        }
        //post
        if (carrier != 0) {
            sb.append(carrier);
        }
        return sb.reverse().toString();
    }
}

