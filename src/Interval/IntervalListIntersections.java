package Interval;

import java.util.ArrayList;
import java.util.List;

/**
 * 可以使用两个指针i，j，分别放在A,B的index 0开始从左向右遍历
 * each round谁的end端点更小，那么加入res的interval的end端点就由谁决定
 *     再根据pointer所指的两个interval是否有iverlap决定放入res的interval的start端点是什么
 * */


class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> res = new ArrayList<>();
        //sanity check
        if (A == null || A.length == 0 || B == null || B.length == 0) {
            return new int[0][0];
        }
        //按照end分解，谁小移谁
        int i = 0;
        int j = 0;
        while (i < A.length && j < B.length) {
            //remove interval with smallest end point
            if (A[i][1] <= B[j][1]) {   //only process cases with overlap cases, 此时新加入res的interval的end一定是A[i][1]
                if (A[i][1] >= B[j][0]) {  //has overlap
                    res.add(new int[]{Math.max(A[i][0], B[j][0]), A[i][1]});
                }
                i++;
            } else {
                if (B[j][1] >= A[i][0]) { //only process cases with overlap cases, 此时新加入res的interval的end一定是B[j][1]
                    res.add(new int[]{Math.max(A[i][0], B[j][0]), B[j][1]});
                }
                j++;
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}

/**代码可以简化成*/
public class IntervalListIntersections {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> res = new ArrayList<>();
        //sanity check
        if (A == null || A.length == 0 || B == null || B.length == 0) {
            return new int[0][0];
        }
        //按照end分解，谁小移谁
        int i = 0;
        int j = 0;
        while (i < A.length && j < B.length) {
            //remove interval with smallest end point
            if (A[i][1] <= B[j][1]) {
                interToRes(A[i], B[j], res);
                i++;
            } else {
                interToRes(A[i], B[j], res);
                j++;
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    private void interToRes(int[] one, int[] two, List<int[]> res) {
        int start = Math.max(one[0],two[0]);
        int end = Math.min(one[1], two[1]);
        if (start <= end) {    //if has overlap
            res.add(new int[]{start, end});
        }
    }
}
