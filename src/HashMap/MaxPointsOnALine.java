package HashMap;

import java.util.HashMap;
import java.util.Map;

/**思路：(不难，但是细节非常多)
 * clarify.问清楚input中是否可能存在重复的点坐标
 *
 on same line condition:同一条线上，任意两点之间连线have same slop: (y2 - y1) / (x2 - x1)
 所以问题可以转化成：
     求哪个slope有最多的pair of point，就能找到max point on a line

 Step1:使用两个指针i，j去找到all pairs of input，以i为基准进行分解，
        each round将point i作为第一个点，求出point pair(i, i+1.....end)的slop，使用HashMap<Slop, freq>存储：
        在point i作为第一个点的情况下，各个slop出现的freq。
        易错点：
        1.如果可能有duplicate point，那么在维度i时候，我们就要查i后面是否有和i重复的点，记下重复的次数dupNum，这个dupNum最后需要直接加入总次数
          1.1 corner case: 万一input是一对相同的点(0,0)(0,0)，此时输出结果应该是2，但如果我们此时对dup元素仅仅dupNum+1,但是map中不记录任何信息，
              最终得到的只有dupNum的1，所以答案变成了1（）此时map是空的
              所以在map中，一旦发现了一对重合点，我们为它留一个freq即可，这里的slope可以是0.00，freq设为1即可。
              这样后面遇到的所有，与i重合的j，就可以直接continue掉，不处理了
 e.g.
 * Input:(0,0),(0,0)
 *      i            j              slope            map                  dupNum       glbMax（初始值1）
 *      0            1                              [0.00,1]                1          max(1, 1+1) = 2
 * 如果map中不记录重合点的freq[0.00,1]  ，那么最终答案只能从dupNum中取到1，就错了

        2.对于每一个i，我们都需要全新的map，所以如果uxianglangfei空间，each round使用map.clear()将map清空使用。
        3.计算slope时候，注意如果用计算公式slop: (y2 - y1) / (x2 - x1)，当X1==X2出现，分母为0，会抛异常，就需要我们对分母为0的点提前检查，
          并且在map中为其记录一个特殊的key，可以是不会出现的slope，比如integer极值。（0也不行，是y1 == y2时候会出现的）
        4.由于slope用到了除法，不一定是整数，要用double才准确

 Step 2.使用glbMax记录
        每次map更新后，检查一下glbMax 与 （dupNum + freq)谁大，保留更大的

 TC:O(N^2)
 OC:最多O(N^2) -->all pairs slope different
 */

/**过例子
 * Input:(1,2),(3,6),(0,0),(1,3)
 *      i            j              slope            map                  dupNum       glbMax
 *      0            1              4/2=2            [2,2]                  0           1
 *                   2              -2/-1=2          [2, 3]                 0
 *                   3              1/0              [2, 3][MAX,2]          0          max(1,3) = 3
 *                                                   clear                clear
 *      1            2              -6/-3=2          [2,2]                  0
 *                   3              -3/-2=1.5        [2,2] [1.5,2]          0          max(2,3) = 3
 *                                                   clear                clear
 *      2            3              3/1=3            [3,2]                  0          max(2,3) = 3->finish
 *
 * Input:(0,0),(0,0)
 *      i            j              slope            map                  dupNum       glbMax
 *      0            1                              [0.00,1]                1          max(1, 1+1) = 2
 * */
public class MaxPointsOnALine {
    public int maxPoints(Point[] points) {
        // sanity check
        if (points == null || points.length == 0) {
            return 0;
        }
        int glbMax = 1; //因为最少都是一个点
        int dupNum = 0; //因为i点会在map中count进去，所以这里只能单独计算与i重复的j的个数，所以初始0
        Map<Double, Integer> map = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            map.clear(); //new round, map重置，dupNum重置
            dupNum = 0;

            Point one = points[i];
            for (int j = i + 1; j < points.length; j++) {
                Point two = points[j];
                //先check j是否和i重合
                if (one.x == two.x && one.y == two.y) {
                    map.putIfAbsent(0.00, 1);  /**corner case,这里很重要，处理input全部是相同的点*/
                    dupNum++;
                    continue;
                }
                //j与i不重合，则计算slop
                Double slope = getSlope(one, two);
                // map.put(slope, map.getOrDefault(slope, 2) + 1);  --》不能这样写，因为map中如果没有只能设置2，不能freq+1
                if(map.containsKey(slope)) {
                    map.put(slope, map.get(slope) + 1);
                }else{
                    map.put(slope, 2); ////初始一对是两个点，所以初始freq = 2
                }
            }
            //每个i计算结束，遍历map找到最大的freq
            for (Integer freq : map.values()) {
                glbMax = Math.max(glbMax, dupNum + freq);
            }
        }
        return glbMax;
    }

    private double getSlope(Point one, Point two) {
        //先检查分母是否为0，如果为0，则slope返回特殊值Double.
        if (one.x == two.x) {
            return Double. MAX_VALUE;
        }
        return (double)(one.y - two.y) / (double)(one.x - two.x);
    }
}

class Point {
    int x;
    int y;
    Point() { x = 0; y = 0; }
    Point(int a, int b) { x = a; y = b; }
 }

