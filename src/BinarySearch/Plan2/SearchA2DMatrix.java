package BinarySearch.Plan2;

/**模板通用解法*/
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        //sanity check
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0;
        int right = m * n - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            int rowIdx = mid / n;
            int colIdx = mid % n;
            if (matrix[rowIdx][colIdx] == target) {
                return true;
            } else if (matrix[rowIdx][colIdx] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (matrix[left / n][left % n] == target) {
            return true;
        } else if (matrix[right / n][right % n] == target) {
            return true;
        }
        return false;
    }
}

/**严谨写法*/
public class SearchA2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        //sanity check
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int left = 0;
        int right = matrix.length * matrix[0].length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (matrix[mid / matrix[0].length][mid % matrix[0].length] == target) {
                return true;
            } else if (matrix[mid / matrix[0].length][mid % matrix[0].length] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }
}
