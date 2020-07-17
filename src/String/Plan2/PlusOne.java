package String.Plan2;

public class PlusOne {
    public int[] plusOne(int[] digits) {
        //sanity check
        if (digits == null || digits.length == 0) {
            return digits;
        }

        int carrier = 0;
        int i = digits.length - 1;
        int sum = 0;
        StringBuilder sb = new  StringBuilder();
        int count = 0;  //第几次加1
        while (i >= 0) {
            /**需要加括号，不然运算顺序不对*/
            sum = (i >= 0 ? digits[i] : 0) +
                    (count == 0 ? 1 : 0) +
                    carrier;
            sb.append(sum % 10);
            carrier = sum / 10;
            //update pointer
            count++;
            i--;
        }
        //post
        if (carrier != 0) {
            sb.append(carrier);
        }
        //get res
        String resStr = sb.reverse().toString();
        int[] res = new int[resStr.length()];
        for (int j = 0; j < resStr.length(); j++) {
            res[j] = resStr.charAt(j) - '0';
        }
        return res;
    }
}


