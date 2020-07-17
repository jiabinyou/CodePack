package Array.Plan0;

/**
 * Sol1.
 * 根据定义进行旋转，一步转到目标位置
 */
public class RotateImage {
    public void rotate(int[][] matrix) {
        //sanity check
        if (matrix == null || matrix[0].length == 0) {
            return;
        }
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = i; j < n - i - 1; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }
}

/**
 * Sol2.
 * Step1.沿着右斜对角线镜像
 *   [1,2,3],      --》     [1 4 7]
 *   [4,5,6],               [2 5 8]
 *   [7,8,9]                [3 6 9]
 * Step 2.沿着中间col，将同一行左右置换
 *   [7,4,1],
 *   [8,5,2],
 *   [9,6,3]
 */
class SolRI2 {
    public void rotate(int[][] matrix) {
        //sanity check
        if (matrix == null || matrix[0].length == 0) {
            return;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        //step 1
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        //step 2
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = temp;
            }
        }
    }
}