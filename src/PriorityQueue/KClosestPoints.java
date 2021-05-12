package PriorityQueue;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 和top k实质是一样的题目
 * 找距离原点最近的k个point，可以维护SIZE = K的maxHeap，距离离原点最大的先被poll出来。
 * 当maxHeap size大于k的时候，每次就offer一个，poll一个。最后全部offer完成，剩下的就是距离原点最近的k个点，最后反向输出即可
 *
 * TC:假设共n个元素，o(nlogk)
 * sc:o(k)
 * */
public class KClosestPoints {
    public int[][] kClosest(int[][] points, int K) {
        //sanity check
        if (points == null || points.length == 0) {
            return new int[0][0];
        }
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(K, new Comparator<int[]>() {
            @Override
            public int compare(int[] i1, int[] i2) {
                long dis1 = getDis(i1);
                long dis2 = getDis(i2);
                if (dis2 == dis1) {
                    return 0;
                }
                return dis2 < dis1 ? -1 : 1;
            }
        });

        //init maxHeap
        for (int i = 0; i < points.length; i++) {
            maxHeap.offer(points[i]);
            if (maxHeap.size() > K) {
                maxHeap.poll();
            }
        }
        //return res
        int[][] res = new int[K][2];
        for (int i = K - 1; i >= 0; i--) {
            res[i] = maxHeap.poll();
        }
        return res;
    }

    private long getDis(int[] arr) {
        long dis = arr[0] * arr[0] + arr[1] * arr[1];
        return dis;
    }
}
