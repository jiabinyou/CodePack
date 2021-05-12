package BinarySearch.Plan2;


/**Sol:
 * input规律：
 * row从左向右变大，col从上向下变大（但上一行末尾不一定比下一行开头小，不能扩展成一行）
 * input有范围：左上角到右下角，并且数据sorted，可以使用BS
 * 可以放在右上角开始，each round，大了就往左走，小了就往右走
 * 每次找到target以后，freq+1，再更新pointer继续走
 *
 * 能这样用的重点：给出了条件每行，每列不会有dup
 * */
public class SearchA2DMatrixIILintcode {
    public int searchMatrix(int[][] matrix, int target) {
        // write your code here
        //sanity check
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int count = 0;

        int i = 0;
        int j = matrix[0].length - 1;
        while (i < matrix.length && j >= 0) {
            if (matrix[i][j] == target) {
                count++;
                i++;     /**关键：找到一个target后要更新指针*/
                j--;
            } else if (matrix[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return count;
    }
}

/**过例子：
 * matrix = [
 *       [1, 3, 5, 7],
 *       [2, 4, 7, 8],
 *       [3, 5, 9, 10]
 *     ]
 *
 *     i       j     matrix[i][j]    freq
 *     0       3        7
 *     0       2        5
 *     0       1        3              1
 *     1       0        2
 *     2       0        3              2 ->finish
 * */

