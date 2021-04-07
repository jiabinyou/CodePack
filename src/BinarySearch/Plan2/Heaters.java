package BinarySearch.Plan2;

/**解法：Binary Search*/

import java.util.Arrays;

/**这道题是套了马甲的。仔细思考：
 * 这道题因为要求所有的heaters的半径都是一样的，所以我们找出"最小"能满足条件的一个heaters半径即可。
 * 在一维空间上，如果想让heaters能够覆盖所有的houses，是不是就是找出每个房子左右两边， closest heaters，记录下所需要的半径Ri。
 * 而对于所有房子而言，为了保证能够全覆盖，最终取MAX(Ri)即可
 * 而找每个房子的closest heaters，使用BS优化即可
 *
 * 假设m个房子，n个heater，tc：O（MlogN）
 */
public class Heaters {
    public int findRadius(int[] houses, int[] heaters) {
        // sanity check
        if (houses == null || houses.length == 0) {
            return 0;
        }
        if (heaters == null || heaters.length == 0) {
            return Integer.MAX_VALUE;
        }

        Arrays.sort(heaters); /**note:保证被BS的data必须是sorted的*/
        int glbRadius = 0;
        for (int house : houses) {
            int singleRadius = binarySearch(house, heaters);
            glbRadius = Math.max(glbRadius, singleRadius);
        }
        return glbRadius;
    }

    //return closest distance
    private int binarySearch(int target, int[] heaters) {
        int left = 0;
        int right = heaters.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if(heaters[mid] == target) {
                return 0;
            } else if (heaters[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (Math.abs(heaters[left] - target) < Math.abs(heaters[right] - target)) {
            return Math.abs(heaters[left] - target);
        } else {
            return Math.abs(heaters[right] - target);
        }
    }
}
