package SW.Plan0;

import java.util.LinkedList;
import java.util.Queue;
        /**这道题让我们设计一个点击计数器，能够返回五分钟内的点击数，提示了有可能同一时间内有多次点击。由于操作都是按时间顺序的，下一次的时间戳都会大于等于本次的时间戳，那么最直接的方法就是用一个队列queue，每次点击时都将当前时间戳加入queue中，然后在需要获取点击数时，我们从队列开头开始看，如果开头的时间戳在5分钟以外了，就删掉，直到开头的时间戳在5分钟以内停止，然后返回queue的元素个数即为所求的点击数

        思路: 要求保证时间顺序，可以用一个queue将每次点击的timestamp放入queue中。
        getHits: 可以从queue的头开始看， 如果queue开头的时间在范围外，就poll掉。最后返回queue的size!!。
        */
public class DesignHitCounter {
    Queue<Integer> q;
    /** Initialize your data structure here */
    public void HitCounter() {
        q = new LinkedList<>();
    }

     /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity).
     */
    public void hit(int timestamp) {
        q.offer(timestamp);
    }

     /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity).
     */
    public int getHits(int timestamp) {
        while (!q.isEmpty() && timestamp - q.peek() >= 300) {
            q.poll();
        }
        return q.size();
    }
}

