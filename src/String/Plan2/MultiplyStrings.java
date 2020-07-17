package String.Plan2;

public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        //sanity check
        if (num1 == null || num1.length() == 0) {
            return num2;
        }
        if (num2 == null || num2.length() == 0) {
            return num1;
        }
        int[] res = new int[num1.length() + num2.length()];

        int sum = 0;
        for (int i = num1.length() - 1; i >= 0; i--) {
            for (int j = num2.length() - 1; j >= 0; j--) {
                /**与加法区别一：计算当前位置和，需要累加之前的值，即+res[i+j+1]*/
                sum = res[i + j + 1] + (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                res[i + j + 1] = sum % 10;
                /**与加法区别二：计算当前位置carrier，需要累加之前的值，即+res[i+j],并且值要放在对应位置上*/
                res[i + j] += sum / 10; //carrier
            }
        }
        //get res
        StringBuilder sb = new StringBuilder();
        for(int num : res) {
            if(!(sb.length() == 0 && num == 0)) {
                sb.append(num);
            }
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
