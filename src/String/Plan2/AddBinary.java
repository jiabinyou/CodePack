package String.Plan2;

public class AddBinary {
    public String addBinary(String a, String b) {
        //sanity check
        if (a == null || a.length() == 0) {
            return b;
        }
        if (b == null || b.length() == 0) {
            return a;
        }

        int carrier = 0;
        int i = a.length() - 1;  //从低位开始运算
        int j = b.length() - 1;
        int sum = 0;
        StringBuilder sb = new  StringBuilder();
        while (i >= 0 || j >= 0) {
            /**需要加括号，不然运算顺序不对*/
            sum = (i >= 0 ? a.charAt(i) - '0': 0) +
                    (j >= 0 ? b.charAt(j) - '0': 0) +
                    carrier;
            sb.append(sum % 2);
            carrier = sum / 2;
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

