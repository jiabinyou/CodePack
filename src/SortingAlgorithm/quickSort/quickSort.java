package SortingAlgorithm.quickSort;

import java.util.Random;

/**
 * TC:
 * SC:
 * */
public class quickSort {
    Random random = new Random();
    public int[] quickSort(int[] array) {
        // sanity check
        if (array == null || array.length == 0) {
            return array;
        }
        quickSort(array, 0, array.length - 1);
        return array;
    }

    //recursion
    private void quickSort(int[] array, int left, int right) {
        //base case
        if (left >= right) {
            return;
        }
        int pivotIdx = partition(array, left, right);
        quickSort(array, left, pivotIdx - 1);
        quickSort(array, pivotIdx + 1, right);
    }

    private int partition(int[] array, int left, int right) {
        int pivotIdx = left + random.nextInt(right - left + 1);
        int pivotVal = array[pivotIdx];
        swap(array, pivotIdx, right);

        int i = left;
        int j = right - 1;
        while (i <= j) {
            if (array[i] < pivotVal) {
                i++;
            } else if (array[j] > pivotVal) {
                j--;
            } else {
                swap(array, i, j);
                i++;
                j--;
            }
        }
        swap(array, i, right);
        return i;
    }

    private void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
