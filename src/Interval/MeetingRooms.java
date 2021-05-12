package Interval;



import java.util.Arrays;
import java.util.Comparator;

/**
 * Sort as interval start point
 * iterate each interval, once the interval has overlap, return false;(next[end] > cur[start] -> non-overlap)
 *
 * TC: O(N)
 * SC: O(NLOGN)->SORT
 *
 * 过例子：
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * sort：[5,10],[15,20],[0,30]     res
 *         i                        T
 *                i                 T
 *                        i         T
 *
 * */
public class MeetingRooms {
    public boolean canAttendMeetings(int[][] intervals) {
        //sanity check
        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) {
            return true;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] i1, int[] i2) {
                if (i1[0] == i2[0]) {
                    return 0;
                }
                return i1[0] < i2[0] ? -1 : 1;
            }
        });
        int[] cur = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] next = intervals[i];
            if (cur[1] > next[0]) {   //overlap
                return false;
            } else {
                cur = next;    //no overlap
            }
        }
        return true;
    }
}
