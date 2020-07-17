package Array.Plan3;

/**
 2 pointers: 技巧点在于：
 nums1的长度恰好是m + n，所以可以从r to l遍历，但是
 从nums1的r to l抄写，谁大移谁
 */
public class MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int cur = m + n - 1; //抄写的idx
        int i = m - 1; //遍历nums1
        int j = n - 1; //遍历nums2
        while (i >= 0 && j >= 0) {
            if (nums1[i] >= nums2[j]) {
                nums1[cur--] = nums1[i--];
            } else {
                nums1[cur--] = nums2[j--];
            }
        }
        while (i >= 0) {
            nums1[cur--] = nums1[i--];
        }
        while (j >= 0) {
            nums1[cur--] = nums2[j--];
        }
    }
}


