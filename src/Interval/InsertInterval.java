package Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        //step 1: sort by start
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] i1, int[] i2) {
                if (i1[0] == i2[0]) {
                    return 0;
                }
                return i1[0] < i2[0] ? -1 : 1;
            }
        });
        //step 2: merge intervals
        boolean inserted = false;
        for (int i = 0; i < intervals.length; i++) {
            int[] cur = intervals[i];
            //case 1
            if (cur[1] < newInterval[0]) {
                res.add(cur);
                //case 2
            } else if (cur[0] > newInterval[1]) {
                if (!inserted) {
                    res.add(newInterval);
                    inserted = true;
                }
                res.add(cur);
                //case 3
            } else {
                newInterval[0] = Math.min(cur[0], newInterval[0]);
                newInterval[1] = Math.max(cur[1], newInterval[1]);
            }
        }
        //post-processing
        if (!inserted) {
            res.add(newInterval);
        }
        return res.toArray(new int[res.size()][]);
    }
}
