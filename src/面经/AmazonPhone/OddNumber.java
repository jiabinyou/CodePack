package 面经.AmazonPhone;

/**题目一：一个整型数组里除了1个数字之外，其他的数字都出现了偶数次，找出这个数字，要求时间复杂度O(n)，空间复杂度O(1)
 * 异或运算，只需要把所有数组中所有数字异或一次就可以了
 * 因为异或运算是相同得0，不同得1
 * 如果是偶数次出现的数字，等所有数字异或完成后，所有偶数次数字结果相互抵消必定是0，剩下的结果就是出现奇数次的数字本身
 * */
public class OddNumber {
    public int findNumber1AppearOdd(int[] array) {
        //sanity check
        if (array == null || array.length == 0) {
            return -1;
        }
        if (array.length == 1) {
            return array[0];
        }
        int res = 0;
        int i;
        for (i = 0; i < array.length; i++) {
            res = res ^ array[i];
        }
        return res;
    }
}

