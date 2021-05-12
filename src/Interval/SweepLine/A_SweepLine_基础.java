package Interval.SweepLine;
import java.util.*;
/** 扫描线算法：使用一根假想的线在坐标轴上水平或垂直移动 像扫描一样经过数据并处理的算法
 * step 1.使用扫描线思想，将每个边界标记为左边界start，或右边界end
 * step 2.将所有的边界排序(start,end都要排序)
 * step 3(根据题目不同采用不同处理).
 *        e.g.merge interval处理：扫描排序后的数组，找到一个左边界，不断更新当前区间可能的右边界位置，如果右边界不可能延伸，则终止当前区间的计算
 *
 * TC:排序所需时间O(n*logn)，遍历数组并计算O(n) 所以时间复杂度均为O(n * logn)
 * SC:空间复杂度O(n)
 * */

/****************************************以merge interval为例的Sweep Line模板************************************************/
/**定义interval*/
class Interval {
    int start;
    int end;
    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

/**是为了把start，end拆解出来，全部放在一个结构中排序，type用来标记当前数据点是start还是end
 * start type:-1, end type: 1
 * 排序时候，按照num从小到大排列，当两个num相同时候，start在前end在后
 * */
class Boundary {
    int num;
    int type;
    public Boundary(int num, int type) {
        this.num = num;
        this.type = type;
    }
}

public class A_SweepLine_基础 {
    public List<Interval> merge(List<Interval> intervals) {
        /**Sweep Line*/
        /**准备comparator，为start，end排序做准备*/
        Comparator<Boundary> comparator = new Comparator<Boundary>(){
            public int compare(Boundary a, Boundary b){
                if (a.num == b.num) {
                    return a.type - b.type;
                }
                return a.num - b.num;
            }
        };
        /**将input的start和end拆解出来，放入一个List中，并用准备好的comparator进行sort*/
        List<Boundary> boundaries = new LinkedList<>();
        for (Interval interval : intervals) {
            boundaries.add(new Boundary(interval.start, -1));
            boundaries.add(new Boundary(interval.end, 1));
        }
        Collections.sort(boundaries, comparator);
        /**merge interval，build res*/
        List<Interval> result = new LinkedList<>();
        int isMatched = 0;    //非常巧妙，isMatched = 0表示现在res是null，或者处理完一个完整的interval(因为只有start-1+end1相加才为0)，到了判断下一个interval是否要merge的时候
        int left = 0;         //手里正在处理的左边界*/
        int right = 0;        //手里正在处理的右边界*/
        for (Boundary boundary : boundaries) {
            if (isMatched == 0) {  //此时res是null,或者上一个end结束,用当前在处理的bound的num来赋值left，表示开始下一轮merge
                left = boundary.num;
            }
            isMatched += boundary.type; //将当前处理的数据点type加入isMatched
            if (isMatched == 0) {  //如果此时isMatched == 0，说明上一个处理到的是end，用当前在处理的bound的num来赋值right，将这个范围加入res
                right = boundary.num;
                result.add(new Interval(left, right));
            }
        }
        return result;
    }
}
