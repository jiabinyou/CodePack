package SW.Plan0;

import java.util.LinkedList;
import java.util.Queue;

/**
 破题关键：
 1.FS - SW
 2.input是stream，不能用idx，需找到合适preSum Ds：queue
 */
public class MovingAverageFromDataStream {
    Queue<Integer> q;
    int maxSize;
    double preSum;
    /** Initialize your data structure here. */
    private void MovingAverage(int size) {
        q = new LinkedList<>();
        maxSize = size;

    }

    public double next(int val) {
        //add fast
        q.offer(val);
        preSum += val;
        //remove slow
        if (q.size() > maxSize) {
            preSum -= q.peek();
            q.poll();
        }
        return preSum / q.size();
    }
}
