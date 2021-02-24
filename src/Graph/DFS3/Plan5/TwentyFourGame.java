package Graph.DFS3.Plan5;

/**
 * 题目：
 * 1.只能单独数字运算，不会出现两位数
 * 2./ 计算真正除法，而不是整数除法，比如 4 / (1/3) = 12
 *      技巧：有此类除法，一般定义eps，小于这个误差的就是正确答案
 * 3.输入的影院是四个数字
 * 4.输入的顺序是可以调换的
 */

/**
 * backTracking:
 * Repeatedly select 2 numbers (all combinations) and compute,
 * until there is only one number, check if it's 24 or eps to 24 < 1e - 10 (eps是自己定义的).
 * 注意：输入是int，需要全部准换成double计算，否则除法可能丢失精读
 */
public class TwentyFourGame {
    private static final double EPS = 1e-10;
    public boolean judgePoint24(int[] nums) {
        double[] doubleArray = new double[4];
        for (int i = 0; i < 4; i++) {
            doubleArray[i] = (double) nums[i];
        }
        return compute(doubleArray, 4);
    }
    private boolean compute(double[] array, int n) { //n -- 表示剩余数字数量
        /**base case: 直到剩余一个数字，it's 24 or eps to 24 < 1e - 10，则表示找到了*/
        if (n == 1) {
            if (Math.abs(array[0] - 24) <= EPS) {
                return true;
            }
        }
        /**recursion*/
        for (int i = 0; i < n; i++) {     //手中剩余数字，任意取两个计算
            for (int j = i + 1; j < n; j++) {
                double a = array[i];
                double b = array[j];
                array[j] = array[n - 1]; /**这一步像是推移array[j],没有完全看懂！！*/
                // + branch
                array[i] = a + b;
                if (compute(array, n - 1)) {
                    return true;
                }
                // - branch
                array[i] = a - b;
                if (compute(array, n - 1)) {
                    return true;
                }
                array[i] = b - a;
                if (compute(array, n - 1)) {
                    return true;
                }
                //* branch
                array[i] = a * b;
                if (compute(array, n - 1)) {
                    return true;
                }
                // / branch, 需保证除数不为0，才能进行
                if (b != 0) {
                    array[i] = a / b;
                    if (compute(array, n - 1)) {
                        return true;
                    }
                }
                if (a != 0) {
                    array[i] = b / a;
                    if (compute(array, n - 1)) {
                        return true;
                    }
                }
                //recover
                array[i] = a;
                array[j] = b;
            }
        }
        return false;
    }
}

