package Graph.BFS2.Plan1;
import java.util.*;

/**
 * 和上一体相同，但是这个解法在LC是TLE
 */
public class SuperUglyNumber   {
    public int nthSuperUglyNumber(int n, int[] primes) {
        PriorityQueue<Long> minHeap = new PriorityQueue<>();
        Set<Long> visited = new HashSet<>();
        minHeap.offer(1L);
        visited.add(1L);
        long cur = 1L;
        for (int i = 0; i < n; i++) {
            cur = minHeap.poll();
            for (int j = 0; j < primes.length; j++) {
                if (visited.add(primes[j] * cur)) {
                    minHeap.offer(primes[j] * cur);
                }
            }
        }
        return (int)cur;
    }
}

/**
 * Sol1b. Graph.BFS2  (在LC中TLE)
 * TreeSet
 * 使用TreeSet的优点是：treeSet不允许duplicate，所以自然而然不需要进行mark visited，
 * 因为那些计算出来重复的num，是无法加入treeSet中去的
 */
class SolSUN1b {
    public int nthSuperUglyNumber(int n, int[] primes) {
        TreeSet<Long> res = new TreeSet<>();
        res.add(1L);
        for (int i = 0; i < n - 1; ++i) {
            long first = res.pollFirst();
            for (int j = 0; j < primes.length; j++) {
                res.add(first * primes[j]);
            }
        }
        return res.first().intValue();
    }
}
