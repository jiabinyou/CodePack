package BinarySearch.Plan2;

/**
 Step 1. 找peak 转折点，检查是否为target
 Step 2. 上一步没找到，就在左半边递增区域找是否存在target
 Step 3. 上一步没找到，就继续在右半边递减区域找是否存在target
 -->因为左右两段分别没有dup，所以不用考虑找left/rightMost pos, 找是否有target即可
 */
public class FindInMountainArray {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        //Step 1. 找peak 转折点，检查是否为target
        int l = 0;
        int r = mountainArr.length() - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (mountainArr.get(mid) > mountainArr.get(mid + 1) ) {
                r = mid;
            } else {
                l = mid + 1;
            }
            if (mountainArr.get(l) == target) {
                return l;
            }
        }


        //Step 2. 上一步没找到，就在左半边递增区域找是否存在target
        int left = 0;
        int right = l - 1;  //这里l即上面mid停留位置
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mountainArr.get(mid) == target) {
                return mid;
            } else if (mountainArr.get(mid) > target) {  //go left (
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        //Step 3. 上一步没找到，就继续在右半边递减区域找是否存在target
        left = l + 1;
        right = mountainArr.length() - 1;;  //这里l即上面mid停留位置
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mountainArr.get(mid) == target) {
                return mid;
            } else if (mountainArr.get(mid) > target) {  //go right (
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        //没结果，找不到
        return -1;
    }
}
