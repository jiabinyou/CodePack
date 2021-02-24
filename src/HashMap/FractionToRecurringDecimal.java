package HashMap;

import java.util.HashMap;

/**
 * 除法计算，无限循环小数用括号表示
 */
public class FractionToRecurringDecimal {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        //sign
        if (!(numerator < 0) == (denominator < 0)) { /**表示两个数字异号*/
            sb.append("-");
        }
        long num = Math.abs((long)numerator);
        long denom = Math.abs((long)denominator);
        //integer part
        sb.append(num / denom);
        long remainder = num % denom;
        if (remainder == 0) {
            return sb.toString();
        }
        //fractional part
        sb.append(".");
        /**hashmap处理小数*/
        HashMap<Long, Integer> map = new HashMap<>(); /**remainder计算结果在sb中的index*/
        while (remainder != 0) {
            map.put(remainder, sb.length());
            remainder *= 10;
            sb.append(remainder / denom);
            remainder %= denom;
            Integer index = map.get(remainder);
            if (index != null) {  /**之前这个remainder在map中已经出现，说明index到cur全部重复*/
                sb.insert(index, "("); /**在index位置放上左括号*/
                sb.append(")");
                break;
            }
        }
        return sb.toString();
    }
}

