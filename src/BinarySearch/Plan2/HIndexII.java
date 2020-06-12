package BinarySearch.Plan2;

/**
 #citations at cur pos: citations[mid]
 definition of h-index:
    N papers have at least h citations each,
    and the other N − h papers have no more than h citations each
    这里h是要求解的未知数，即BS中的citations.length - mid
    我们要寻找的目标是：
        leftMost pos 满足 --> #citations at cur pos >= h
        即  leftMost pos 满足 --> citations[mid] >= citations.length - mid
 */
public class HIndexII {
    public int hIndex(int[] citations) {
        //sanity check
        if (citations == null || citations.length == 0 || citations[citations.length - 1] == 0) {
            return 0;
        }
        int left = 0;
        int right = citations.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (citations[mid] >= citations.length - mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return citations.length - left;  //最终返回的是h
    }
}
