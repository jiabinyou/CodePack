package Array.Plan0;

public class SpiralMatrixII {
    public int[][] generateMatrix(int n) {
        //sanity check
        if (n == 0) {
            return new int[0][0];
        }

        int[][] res = new int[n][n];
        int fC = 0;
        int fR = 0;
        int lC = n - 1;
        int lR = n - 1;

        int curNum = 1;
        while (fC <= lC) {
            for (int j = fC; j <= lC; j++) {
                res[fR][j] = curNum++;
            }
            fR++;  //!!不能写成int i = fR - 1, 因为fR是需要实际+1的
            for (int i = fR; i <= lR; i++) {
                res[i][lC] = curNum++;
            }
            lC--;
            for (int j = lC; j >= fC; j--) {
                res[lR][j] = curNum++;
            }
            lR--;
            for (int i = lR; i >= fR; i--) {
                res[i][fC] = curNum++;
            }
            //重要！！update fC,才能继续往里转
            fC++;
        }
        return res;
    }
}
