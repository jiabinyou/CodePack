package SortingAlgorithm;

public class mergeSort {
    public int[] mergeSort(int[] array) {
        // sanity check
        if(array == null || array.length == 0) {
            return new int[0];
        }
        mergeSort(array, 0, array.length - 1);
        return array;
    }

    //recursion
    private void mergeSort(int[] array, int left, int right) {
        //base case
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(array, 0, mid);
        mergeSort(array, mid + 1, right);
        merge(array, left, mid, right);
    }

    private void merge(int[] array, int left, int mid, int right) {
        int[] copy = new int[array.length];
        for (int i = left; i <= right; i++) {
            copy[i] = array[i];
        }

        int i = left;
        int j = mid + 1;
        while (i <= mid && j <= right) {
            if (copy[i] < copy[j]) {
                array[left++] = copy[i++];
            } else {
                array[left++] = copy[j++];
            }
        }
        //post
        while (i <= mid) {
            array[left++] = copy[i++];
        }
    }

    public static void main(String[] args) {
        mergeSort ob = new mergeSort();
        int[] arr = new int[]{3, 1, 2};
        ob.mergeSort(arr);
        for (int i :arr) {
            System.out.println(i);
        }
    }
}
