package StackQueueDeque;

/**
 * Sol1.不建议这种写法，繁琐
 */

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

/**
 基本思路：
 使用两个epointer：r，c用来表示当前遍历到哪行哪列
 优先遍历row，row++，如果rwo到最后一行，则col+1，row重置为0

 难点1.此题输入的每行的列数不等
 这就导致出现下列情况：
 [1,2,3,B],
 [4,5,6,7],
 [8,9,A,C],
 如果走到了A的位置上，那么实际上是没有东西能打印的，我们的做法是：
 依然假设每一行的列数都是相同的，只是会检查每一行实际列数realCol，如果当前所在col > realCol，说明没东西了，
 那么就继续往下移动row++，直到找到下一个能打印出来的位置为止。
 当然和基本思路做法一样，如果发现走到了最后一行，就row重置0，col++

 难点2.怎样检查hasNext？
 实际上会发现可以用打印过的行数作为判断标准，如果所有行都打印过了，说明就没有元素了
 所以我们一开始记一下初始还没有被打印的行数remainRow，
 每打印完一行，row--（当出现col > realCol，即说明当前行打印完了）
 如果remainRow<=0,说明没有next element，都打印完了
 */
public class ZigzagIteratorII {
    /*
     * @param vecs: a list of 1d vectors
     */
    int remainRow = 0;
    int row = 0;
    int col = 0;
    List<List<Integer>> vecs;
    public ZigzagIteratorII(List<List<Integer>> vecs) {   /**constructor*/
        this.vecs = vecs;
        //pruning(因为有行可能一个元素都没有，所以可以先把这些空行从remainRow中剔除)
        int size = vecs.size();
        remainRow = vecs.size();
         for (List<Integer> list : vecs) {
             if (list.isEmpty()) {
                 remainRow--;
             }
         }
    }

    /*
     * @return: An integer
     */
    public int next() {
        //难点1: 当col  > realCol时候，说明当前位置没有值，要一行一行跳过没有值的位置，直到找到下一个有值的行
        int realCol = vecs.get(row).size();
        while (col >= realCol) {
            basicMove(row, col);
        }
        //此时row，col就是下一个有值位置的坐标，直接拿值
        int val = vecs.get(row).get(col);
        //难点2.要检查一下如果当前行走完，把当前行从remainRow中去掉
        if (col == vecs.get(row).size() - 1) {
            remainRow--;
        }
        //再按照基本思路，更新一次row，col，依然是往下走一行，如果row到底了就row置0，col++
        basicMove(row, col);
        //return value
        return val;
    }


    //基本移动，更每次往下走一行row++，如果row到底了就row置0，col++
    private void basicMove(int row, int col) {
        row++;
        if (row == vecs.size()) { //如果row到底了就row置0，col++
            //row = 0;
            row %= vecs.size();
            col++;
        }
    }

    /*
     * @return: True if has next
     */
    public boolean hasNext() {
        return remainRow > 0;
    }
}

/**
 * Sol2: queue + iterator （很巧妙的写法，推荐）
 * 补充知识：list.iterator()   --> 将list转换成iterator object
 * */

/**
 *思路：将每一行转换成一个iterator，再把所有的iterator放进queue中管理。
 */
class ZigzagIterator2Sol2 {
    Queue<Iterator<Integer>> queue;
    public ZigzagIterator2Sol2(List<List<Integer>> vecs) {  /**constructor*/
        queue = new ArrayDeque<>();
        for (int i = 0; i < vecs.size(); i++) {
            if (vecs.get(i).size() > 0) {
                queue.offer(vecs.get(i).iterator());
            }
        }
    }

    /**每次取下一行的值，只需要将queue中的第一个iterator object poll出来，取它的next()元素
     * 如果取完next元素，iterator还是有值，就再塞回queue里面去
     * */
    public int next() {
        Iterator<Integer> curRow = queue.poll();
        int num = curRow.next();
        if (curRow.hasNext()) {
            queue.offer(curRow);
        }

        return num;
    }

    /**如果queue空了，就说明全部处理完了*/
    public boolean hasNext() {
        return queue.size() > 0;
    }
}
