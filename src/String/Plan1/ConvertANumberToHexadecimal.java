package String.Plan1;

/**
 * java中有三种移位运算符
 * 1.  << : 左移运算符，num << 1,相当于num乘以2
 * 2.  >> : 右移运算符，num >> 1,相当于num除以2
 * 3.  >>> : 无符号右移，忽略符号位，空位都以0补齐
 *      value >>> num     --   num 指定要移位值value 移动的位数。
 *      无符号右移的规则只记住一点：忽略了符号位扩展，0补最高位
 *      无符号右移运算符>>> 只是对32位和64位的值有意义
 *      (>>> is used to right-shifted `4` bit positions with zero-extension.
 *      The zero-extension will naturally deal with negative number.)
 */

/**
 * 二进制中，1111表示15，是能够转化成16进制的最大单位
 * 所以每次我们将num与1111，即十进制中的15做&运算，得到的就是num的二进制中最低位的四位，每四位是一个单位，转化成16进制字符；
 * 转化完成后，右移4位，并用0补齐，即将刚刚已经计算过的4位update走，所以需要无符号右移>>>=;
 * 最后直到整个num为0，说明转化完毕
 * 因为sb append是从低位到高位，所以最后reverse一下
 *
 */
public class ConvertANumberToHexadecimal {
    public String toHex(int num) {
        char[] map = new char[] {'0', '1', '2', '3', '4', '5', '6', '7',
                '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};  // We will pick from this set of characters
        //sanity check
        if (num == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            sb.append(map[num & 15]);  //等价于num & 0xf;
            num >>>= 4; //无符号右移四位
        }
        return sb.reverse().toString();
    }
}

