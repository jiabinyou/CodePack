package BinarySearch.Plan2;

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