package Array.Plan0;

/**
 direction of going up right or going down left depends whether the index sum is even or odd
 for each even or odd diagonal, there are three cases:
 1. there is room to go that direction
 2. there is no row space to go further but there is col space
 3. there is no col space to go further but there is row space
 */

/**
 难点：
 1.对于右上方向要先判断都到最右一列
 原因在于一个特殊例子：走到右上角元素3时候，需要做的事情是row++,即对应右上方向走到最右一列情况，如果先判断走到最上一行，
 执行了col++就不对了, outOfBounds

 2.对于左下方向要先判断走到最下一行
 原因在于一个特殊例子：走到左下角元素时候，需要做的事情是col++,即对应左下方向走到最下一行情况，如果先判断走到最下一行，
 执行了row++就不对了, outOfBounds
 */
public class DiagonalTraverse {
    public int[] findDiagonalOrder(int[][] matrix) {
        //sanity check
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[] res = new int[m * n];
        int row = 0;
        int col = 0;
        for (int i = 0; i < m * n; i++) {
            res[i] = matrix[row][col];
            if ((row + col) % 2 == 0) {  //右上方向
                if (col == n - 1) {   //右上方向走到最右一列
                    row++;
                } else if (row == 0) {  //右上方向走到最上一行
                    col++;
                } else {
                    row--;
                    col++;
                }
            } else { //左下方向
                if (row == m - 1) { //左下方向走到最下一行
                    col++;
                } else if (col == 0) { //左下方向走到最左一列
                    row++;
                } else {
                    col--;
                    row++;
                }
            }
        }
        return res;
    }
}
