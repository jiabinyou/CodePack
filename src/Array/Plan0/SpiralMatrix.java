package Array.Plan0;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        //sanity check
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int fR = 0; //first row
        int lR = m - 1; //last row
        int fC = 0; //first col
        int lC = n - 1; //last col
        while (fR < lR && fC < lC) {
            for (int j = fC; j <= lC; j++) {
                res.add(matrix[fR][j]);
            }
            for (int i = fR + 1; i <= lR - 1; i++) {
                res.add(matrix[i][lC]);
            }
            for (int j = lC; j >= fC; j--) {
                res.add(matrix[lR][j]);
            }
            for (int i = lR - 1; i >= fR + 1; i--) {
                res.add(matrix[i][fC]);
            }
            fR++;
            lR--;
            fC++;
            lC--;
        }
        //post-processing
        if (fR > lR || fC >lC) {
            return res;
        } else if (fR == lR) {
            for (int j = fC; j <= lC; j++) {
                res.add(matrix[fR][j]);
            }
        } else {
            for (int i = fR; i <= lR; i++) {
                res.add(matrix[i][fC]);
            }
        }
        return res;
    }
}
