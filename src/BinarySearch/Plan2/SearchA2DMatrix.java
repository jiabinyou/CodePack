package BinarySearch.Plan2;

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
