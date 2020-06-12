package BinarySearch.Plan1;

/**
 index从1开始，return index
 find leftMost pos of bas version
 */
public class FirstBadVersion {
    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;  //一定有结果，都不需要post-process了
    }

    //fake helper function
    private boolean isBadVersion(int idx) {
        return true;
    }
}
