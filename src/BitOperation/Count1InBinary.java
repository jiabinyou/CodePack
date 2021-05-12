package BitOperation;

/**
 * Step 1. (num - 1)
 * 作用：使得右边数第一个1变成0，低于右边数第一个1位置的所有0，全部变成1
 * e.g.
 * num   = 00010 10000
 * num-1 = 00010 01111
 *
 * step 2. num & (num - 1)
 *     num   = 00010 10000
 *  &  num-1 = 00010 01111
 *           = 00010 00000
 *  ->去掉了最低位的1
 *
 *  所以二进制中有多少个1，（在num变成0之前）就可以进行多少次&操作
 *
 * TC: 时间复杂度是o(m),m为1的位数
 * SC: O(1)
 * */
public class Count1InBinary {
    public int countOnes(int num) {
        int count = 0;
        while (num != 0) {
            num = num & (num - 1);
            count++;
        }
        return count;
    }
}
