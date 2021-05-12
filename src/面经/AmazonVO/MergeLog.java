package 面经.AmazonVO;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Log {
    int date;
    int time;
    String logID;
    String info;
    public Log(int date, int time, String logID, String info) {
        this.date = date;
        this.time = time;
        this.logID = logID;
        this.info = info;
    }
}
public class MergeLog {
    public List<Log> mergeLog(List<Log> l1, List<Log> l2) {
        List<Log> res = new ArrayList<>();
        //sanity check
        if (l1 == null && l2 == null) {
            return res;
        } else if (l2 == null) {
            return l1;
        } else if (l1 == null) {
            return l2;
        }

        PriorityQueue<Log> minHeap = new PriorityQueue<>(11, new Comparator<Log>() {
            @Override
            public int compare(Log l1, Log l2) {
                if (l1.date == l2.date) {
                    return l1.time - l2.time;
                }
                return l1.date - l2.date;
            }
        });
        //put input into minHeap
        for (int i = 0; i < l1.size(); i++) {
            minHeap.offer(l1.get(i));
        }
        for (int i = 0; i < l2.size(); i++) {
            minHeap.offer(l2.get(i));
        }
        //build res
        while (!minHeap.isEmpty()) {
            res.add(minHeap.poll());
        }
        return res;
    }
}
