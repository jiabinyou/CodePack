package BinarySearch.Plan1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**BS
 * 1.use bs find find SLE than x
 * 2.two pointer，中心开花找k个
 *
 * TC:O(LOGN) + O(K)
 * SC:O(1)
 * */
public class FindKClosestElements {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        //sanity check
        if (arr == null || arr.length == 0 || k == 0) {
            return res;
        }
        //中心开花找K个
        int neiIdx = findSmallestLargerThanOrEqualTo(arr, x);
        int left = neiIdx - 1;
        int right = neiIdx;
        for (int i = 0; i < k; i++) {
            if (right >= arr.length || left >= 0 && x - arr[left] <= arr[right] - x) {
                res.add(arr[left--]);
            } else {
                res.add(arr[right++]);
            }
        }
        Collections.sort(res);
        return res;
    }

    //find SLE than x
    private int findSmallestLargerThanOrEqualTo(int[] arr, int x) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == x) {  //gp left
                right = mid;
            } else if (arr[mid] < x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}

