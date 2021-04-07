package Interval;
/**
 * 算法一 前缀和
 * 在开始时间位置+1架飞机，在结束时间-1架飞机，求一遍前缀和，就是对应时间飞机的数量,
 * 前缀和算法涉及到了对时间离散化，所以这里更推荐扫描线
 * TC: O(Time) Time表示最大时间
 * */

/**
 * 解法2：interval sweep line扫描线
* 扫描线，把飞机按开始时间从小到大排序，如果开始时间相同，结束时间小的在前，
* 扫描一遍，当扫描到开始时间，就会多一架飞机,飞机数+1，当扫描到结束时间就少一架飞机，飞机数-1
* 答案取扫描过程中的最大飞机数
 *
 * TC:O(NlogN) N是飞机数量
 * */

import java.util.*;

/**写法一.直接写成array，再sort,在两个DS上做interval遍历*/
public class NumberOfAirplanesInTheSky {
    public int countOfAirplanes(List<Interval> airplanes) {
        // sanity check
        if (airplanes == null || airplanes.size() == 0) {
            return 0;
        }
        //sort start & end
        int size = airplanes.size();
        int[] start = new int[size];
        int[] end = new int[size];
        for (int i = 0; i < size; i++) {
            start[i] = airplanes.get(i).start;
            end[i] = airplanes.get(i).end;
        }
        Arrays.sort(start);
        Arrays.sort(end);
        //iterate start & end
        int count = 0;
        int endIdx = 0;  //end[]上一个还没结束的interval idx
        for (int i = 0; i < size; i++) {  //i --> cur idx in staart[]
            if (start[i] < end[endIdx]) {  /**难点：start[i] == end[endIdx]时候，按照一级结束了计算，所以不算做meeting同时发生，而应该更新endIdx*/
                count++;
            } else {
                endIdx++;
            }
        }
        return count;
    }
}

/**写法2.改成comparator，在一个DS上做interval遍历（更符合sweep line的物理意义）*/
/**
 * 具体做法：把interval的start，end全部抽取出来放在一起sort，其中一路上遇到的start就+1，遇到end就-1，什么时候累加值最大就是"同时最多的meeting数目
 * 这里我们可以使用class Point {int val, int flag}, val表示Interval中抽取的start/end的具体数值，flag标记该val是start(+1)还是end(-1)
 * */
class NumberOfAirplanesInTheSkySol2 {
    /**
     * @param airplanes: An interval array
     * @return: Count of airplanes are in the sky.
     */
    public int countOfAirplanes(List<Interval> airplanes) {
        // sanity check
        if (airplanes == null || airplanes.size() == 0) {
            return 0;
        }
        //build swwpLine
        List<Point> sweepLine = new ArrayList<>();
        for (Interval interval: airplanes) {
            sweepLine.add(new Point(interval.start, 1));
            sweepLine.add(new Point(interval.end, -1));
        }
        Collections.sort(sweepLine, new PointComparator());
        //iterate sweepLine
        int max = 0;
        int sum = 0;
        for (Point p : sweepLine) {
            sum += p.flag;
            max = Math.max(max, sum);
        }
        return max;
    }
}

class Point {
    int val;
    int flag;
    public Point(int val, int flag) {
        this.val = val;
        this.flag = flag;
    }
}

class PointComparator implements Comparator<Point> {
    public int compare(Point a, Point b) {
        if (a.val == b.val) {
            return a.flag - b.flag;
        }
        /**当va相同时候，此时不算做”同时发生的meeting“，所以end先计算，所以还是按照从小到大顺序，
         flag == -1的end interval放在前面 */
        return a.val - b.val;
    }
}

