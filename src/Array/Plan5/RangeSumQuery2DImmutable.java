package Array.Plan5;

/**
 row不变，针对每一行，按照preSum计算方法

 最后每行preSum累加即可
 */
public class RangeSumQuery2DImmutable {
    int[][] preSum;
    public void NumMatrix(int[][] matrix) {
        //sanity check
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        preSum = new int[matrix.length][matrix[0].length + 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                preSum[i][j + 1] = preSum[i][j] + matrix[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int row = row1; row <= row2; row++) {
            sum += preSum[row][col2 + 1] - preSum[row][col1];
        }
        return sum;
    }
}


