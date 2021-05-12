package Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Sol.sort the two people's time slot by start time, then iterate their sorted time slots,
 * find the first overlapped slots which has overlap length > = duration
 *
 * overlapLen = min(slots1[i][1], slots2[j][1]) - max(slots1[i][0], slots2[j][0])
 * case 1: has overlap && overlapLen >= duration
 *         -->find res,
 *            start time: max(slots1[i][0], slots2[j][0])),
 *            end time: start time + duration
 * case 2: has overlap but < duration || no overlap
 *             update pointer
 *
 * TC: O(NLOGN + MLOGM)
 * SC: O(1)
 *
 *e.g.
 * slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
 * sort:
 * slots1 = [[10,50],[60,120],[140,210]]       slot1 start      slot2 start          res
 *             i                                   10               0           overlap, len < duration, j++
 *             i                                   10               60          no overlap, i++
 *                     i                           60               60          overlap length > duration, find res
 * slots2 = [[0,15],[60,70]]
 *             j
 *                    j
 *                    j
 *
 * */
public class MeetingScheduler {
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        List<Integer> res = new ArrayList<>();
        //sanity check
        if (slots1 == null || slots1.length == 0 || slots2 == null || slots2.length == 0) {
            return res;
        }
        //step 1 : sort by start time
        Arrays.sort(slots1, (a, b) -> a[0] - b[0]);
        Arrays.sort(slots2, (a, b) -> a[0] - b[0]);
        //step2 : iterate
        int i = 0;  //iterate slots 2
        int j = 0;  //iterate slots 1
        while (i < slots1.length && j < slots2.length) {
            int overlapLen = Math.min(slots1[i][1], slots2[j][1]) - Math.max(slots1[i][0], slots2[j][0]);
            //has overlap && overlapLen >= duration
            if (overlapLen >= duration) {
                res.add(Math.max(slots1[i][0], slots2[j][0]));
                res.add(Math.max(slots1[i][0], slots2[j][0]) + duration);
                return res;
            } else {
                //has overlap but < duration || no overlap
                if (slots1[i][0] < slots2[j][0]) {
                    i++;
                } else {
                    j++;
                }
            }
        }
        return res;
    }
}


