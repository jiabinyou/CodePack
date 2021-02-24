package Graph.BFS2.Plan1;
import java.util.*;
/**
 * Sol.Graph.BFS2
 * 使用pq
 */

/**
 * Java中，long数据类型范围内的所有整数称为long类型的整数字面量。long类型的整数常数总是以大写L或小写l结尾。
 * 比如： int 0
 *       Long 0L 或者 0l
 */
public class UglyNumberII {
    public int nthUglyNumber(int n) {
        PriorityQueue<Long> minHeap = new PriorityQueue<>();
        Set<Long> visited = new HashSet<>();
        minHeap.offer(1L); //1的Long type
        visited.add(1L);
        Long cur = 1L;  //cur初始值，这里随便填一个数即可，真正的cur是从pq中poll出来的
        for (int i = 0; i < n; i++) {
            //expansion
            cur = minHeap.poll();
            //generation
            if (visited.add(2 * cur)) {
                minHeap.offer(2 * cur);
            }
            if (visited.add(3 * cur)) {
                minHeap.offer(3 * cur);
            }
            if (visited.add(5 * cur)) {
                minHeap.offer(5 * cur);
            }
        }
        return cur.intValue();
    }
}

/**
 * Sol1b. Graph.BFS2
 * TreeSet
 * 使用TreeSet的优点是：treeSet不允许duplicate，所以自然而然不需要进行mark visited，
 * 因为那些计算出来重复的num，是无法加入treeSet中去的
 */
class SolUNII1b {
    public int nthUglyNumber(int n) {
        TreeSet<Long> res = new TreeSet<>();
        res.add(1L);
        for (int i = 0; i < n - 1; ++i) {
            long first = res.pollFirst();
            res.add(first * 2);
            res.add(first * 3);
            res.add(first * 5);
        }
        return res.first().intValue();
    }
}




