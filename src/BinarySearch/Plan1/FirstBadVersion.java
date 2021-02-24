package BinarySearch.Plan1;

/**
 index从1开始，return index
 find leftMost pos of bas version
 */

/**模板通用解法*/
class VersionControl {
    public int firstBadVersion(int n) {
        //sanity check
        if (n == 0) {
            return 0;
        }
        int left = 1;
        int right = n;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {   //keep go left
                right = mid;
            } else {
                left = mid;
            }
        }
        if (isBadVersion(left)) {
            return left;
        } else if (isBadVersion(right)) {
            return right;
        }
        return 0;
    }

    //fake helper function
    private boolean isBadVersion(int idx) {
        return true;
    }
}

/**严谨写法*/
class FirstBadVersion {
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
