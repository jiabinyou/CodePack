package 面经.AmazonPhone;

import java.util.Arrays;

public class OddNum2 {
    /**有两个数字出现奇数次**/
    public static int[] findNumbers2AppearOdd(int[] array) {
        //sanity check
        if (array == null || array.length < 2) {
            return new int[0];
        }

        /**对整体做异或，得到不为0的值**/
        int excusiveOR_res = 0;
        int i;
        for (i = 0; i < array.length; i++) {
            excusiveOR_res = excusiveOR_res ^ array[i];
        }
        /**找到excusiveOR_res中第一个为1的位置**/
        int target_index = 0;
        while ((excusiveOR_res & 1) == 1) {  //&& target_index < 8 * sizeof(int
            /**右移1位**/
            excusiveOR_res = excusiveOR_res >> 1;
            target_index++;
        }

        /**按照target_index位置上的值是否为1，将数组分成两个部分计算，分别得到结果**/
        int res1 = 0;
        int res2 = 0;
        for (i = 0; i < array.length; i++) {
            if (isBit1(array[i], target_index)) {
                res1 = res1 ^ array[i]; //分到group1
            } else {
                res2 = res2 ^ array[i]; //分到group2
            }
        }
        return new int[]{res1, res2};
    }


    /**判断当前数字第target_index位是否为1**/
    private static boolean isBit1( int number, int target_index )
    {
        number = number >> target_index;
        return (number & 1) == 0;
    }

    public static void main(String[] args) {
        System.out.println(" Result for [0,9,9,7,6,6,8,8] is " + Arrays.toString(findNumbers2AppearOdd(new int[]{0,9,9,7,6,6,8,8})));
    }
}
