package SortingAlgorithm.SelectionSort;

public class SelectionSort {
    public int[] solve(int[] array) {
        // saity check
        if (array == null || array.length == 0) {
            return array;
        }
        int len = array.length;
        for (int i = 0; i <= len - 2; i++) {
            int minIdx = i;
            for (int j = i + 1; j <= len - 1; j++) {
                if (array[j] < array[minIdx]) {
                    minIdx = j;
                }
            }
            swap(array, i, minIdx);
        }
        return array;
    }

    private void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
