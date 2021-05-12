package Interval.SweepLine;

/**
 * 把所有的start跟end依照时间排序, 每遇到start就得多开一个房间, 每遇到end就可以把一个房间给关了
 * count记录当前所需的meeting room数
 * maxCount记录count的最高值，即最多需要的room数量
 *
 * Time Complexity: O(NlogN)
 *                  because all we are doing is sorting the two arrays for start timings and end timings individually
 *                  and each of them would contain N intervals.
 *
 * Space Complexity: O(N)
 *                  because we create list of size 2N, recording the start times the end times.
 * */

/**
 *  * e.g.[[1, 5],[6,12],[16,25],[1,10]]
 *  * sweep line排序后
 *  * Boudary Class：
 *  * num                1     1      5     6     10     12    16     25
 *  * type               1     1      -1    1     -1     -1     1      -1
 *  * count              1     2      1     2      1     0      1      0
   maxCount              1     2      2     2      2     2      2      2
 *  *
 * */
public class MeetingRoomsII {
}
