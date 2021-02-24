package Graph.BFS2.Plan1;
import java.util.*;

/**
 * Sol.Graph.BFS2
 * 因为两个input array都是sorted的，所以可以以nums2[0]作为基准点，先与nums1中各个元素两两配对，放入pq中
 * pq是两个元素之和的minHeap
 * 再将pq中元素一一poll出来，以栈顶元素中nums1重元素为基准，将nums2中指针往右更新一位，以此找到下一位可能最小的pair
 * （类似谁小移谁的思想）
 *
 * 按照一维顺序遍历，不需要mark visited
 */
public class FindKPairsWithSmallestSums {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        //sanity check
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0 || k == 0) {
            return result;
        }
        /**自定义pq*/
        PriorityQueue<Pair> minHeap = new PriorityQueue<>();
        for (int i = 0; i < Math.min(nums1.length, k); i++) {
            minHeap.offer(new Pair(0, nums1[i], nums2[0]));
        }
        while (k > 0 && !minHeap.isEmpty()) {
            //expansion
            Pair cur = minHeap.poll();
            result.add(new ArrayList<>(Arrays.asList(cur.val1, cur.val2)));
            //generation
            if (cur.p2 + 1 < nums2.length) {
                minHeap.offer(new Pair(cur.p2 + 1, cur.val1, nums2[cur.p2 + 1]));
            }
            k--;
        }
        return result;
    }

    static class Pair implements Comparable<Pair>{
        int p2;
        int val1;
        int val2;
        int sum;
        Pair(int p2, int val1, int val2) {
            this.p2 = p2;
            this.val1 = val1;
            this.val2 = val2;
            this.sum = this.val1 + this.val2;
        }
        @Override
        public int compareTo(Pair other) {
            return Integer.compare(this.sum, other.sum);
        }
    }
}

