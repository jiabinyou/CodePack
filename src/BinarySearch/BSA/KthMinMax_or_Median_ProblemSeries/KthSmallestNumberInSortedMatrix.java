package BinarySearch.BSA.KthMinMax_or_Median_ProblemSeries;

/**
 * 别一激动看成杨氏矩阵BS了。这道题是求Kth smallest
 * 最优解法的确是BS,但是属于BSA
 * */

/**Sol1.最基本，将m*n个元素放入minHeap，再往外拿k次
 * O(log（m*n）） + o（k） */

/**Sol2.BSA
 *          step 1：找固定区间
 *             left:  左上角元素
 *             right: 右下角元素
 *             mid: left + (right - left) / 2;
 *
 *         step 2: 找mid判断的移动规则
 *             （important！！！BSA中即找helper function逻辑：
 *             将mid类比成”实际res“，mid与实际res大：左移区间； mid与实际res小：右移区间）
 *             假设我们已经知道第kth smallest的元素大小为E（mid）， 那么矩阵中<= E的元素的个数一定是K个
 *             所以我们可以使用helper function去计算矩阵中小于等于mid的元素数量
 *             注意易错点！！！：因为mid是left与right中间的一个虚拟值，不一定是矩阵中的一个元素，所以想一下，如果发现
 *             此时矩阵中比mid小的元素恰为k个时候，我们实际上要找的矩阵中mid左边的那个元素（即为第kth small的元素）
 *             --》以此才能得出正确的区间移动规则：
 *             小于等于mid的元素数量 == k   -> （这里不是得到res了）说明此时res就在mid的左侧，依然左移区间
 *             小于等于mid的元素数量 <  k   --> 说明mid偏小了，右移区间
 *             小于等于mid的元素数量 >  k   --> 说明mid偏大了，左移区间
 *             (类似杨氏矩阵的移动规则，反而是用在helper function中的)
 * */
public class KthSmallestNumberInSortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        // sanity check
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        //BS
        int left = matrix[0][0];
        int right = matrix[m - 1][n - 1];
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (countSmallerOrEqual(matrix, mid) >= k) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (countSmallerOrEqual(matrix, left) >= k) {  //仍然按照移动规则，左边的如果符合要求，就是第k小，再去看右边
            return left;
        }
        return right;
    }

    /**cal the #element in matrix <=target*/
    private int countSmallerOrEqual(int[][] matrix, int target) {
        int count = 0;
        int i = 0;
        int j = matrix[0].length - 1;
        while (i < matrix.length && j >= 0) {
            if (matrix[i][j] <= target) {
                count += (j + 1);  //row即将更新，所以把当前row符合条件的全部加入
                i++;
            } else {
                j--;
            }
        }
        return count;
    }
}


