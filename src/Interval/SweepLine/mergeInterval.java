package Interval.SweepLine;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**Sol1,GREEDY
 * 将intervals数组排序，左边界start越小越优先，如果左边界start一样，再按照右边界end排序
 * merge规则：
 * case 1： 前一个区间的右边界在后一个区间的左边界之后 == 两区间相交，所以当前一个区间的右边界在后一个区间的左边界之后，则合并两个区间
 * else：
 * case 2： 新区间和前一个没有overlap，直接加入res即可
 *
 * TC:排序所需时间O(n*logn)，遍历数组并计算O(n) 所以时间复杂度均为O(n * logn)
 * SC:空间复杂度O(n)
 * */

//用的interval class：
//class Interval {
//    int start;
//    int end;
//    Interval(int start, int end) {
//        this.start = start;
//        this.end = end;
//    }
//}

public class mergeInterval {
    public List<Interval> merge(List<Interval> intervals) {
        /**Sweep Line*/
        Comparator<Interval> comparator = new Comparator<Interval>(){
            public int compare(Interval a, Interval b){
                if (a.start == b.start) {
                    return a.end - b.end;
                }
                return a.start - b.start;
            }
        };
        Collections.sort(intervals, comparator);

        //calculate res
        List<Interval> result = new LinkedList<>();
        for (Interval interval : intervals) {
            if (result.size() == 0 || result.get(result.size() - 1).end < interval.start) {  //当前interval是全新，不需要merge
                result.add(interval);
            } else {
                result.get(result.size() - 1).end = Math.max(result.get(result.size() - 1).end, interval.end); //需要merge，根据end决定
            }
        }
        return result;
    }
}

/**
 * Sol2.SweepLine
 * step 1.使用扫描线思想，将每个边界标记为左边界start，或右边界end
 * step 2.将所有的边界都放到一个list中排序(start,end都要排序)
 * step 3(根据题目不同采用不同处理).
 *        e.g.merge interval处理：扫描排序后的数组，找到一个左边界，不断更新当前区间可能的右边界位置，如果右边界不可能延伸，则终止当前区间的计算
 *
 * TC:排序所需时间O(n*logn)，遍历数组并计算O(n) 所以时间复杂度均为O(n * logn)
 * SC:空间复杂度O(n)
 * */

/**精髓：使用isMatched来表示当前手里的right是否能够继续扩展（isMatched += type）
 * e.g.[[1, 5],[6,12],[16,25],[1,10]]
 * sweep line排序后
 * Boudary Class：
 * num                1     1      5     6     10     12    16     25
 * type              -1    -1      1     -1     1     1     -1      1
 * isMatched     0   -1    -2     -1     -2    -1     0     -1      0
 * left               1                               12
 * right                                              12            25
 * res                                               [1,12]        [12,25]
 *
 * 我们手里始终拿着left，right两个正在处理的边界
 * 关键判断isMatched是否为0，isMatched == 0有两种情况：
 * 1.res == null，刚开始
 * 2.当前的right无法再往右扩展了，走完了，再往下一个是与当前的[left, right]无overlap的interval(比当前的end值更大的start)
 * 这两个位置要对left，right赋值，并且将当前[left, right]加入res，其他时候都说明interval有overlap，
 * 以left为起点，跟着我们用sweepLine sort好的list扩展right即可。
 * */



//class Interval {
//    int start;
//    int end;
//    Interval(int start, int end) {
//        this.start = start;
//        this.end = end;
//    }
//}

//class Boundary {
//    int num;
//    int type;
//    public Boundary(int num, int type) {
//        this.num = num;
//        this.type = type;
//    }
//}

class mergeIntervalSol2 {
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
