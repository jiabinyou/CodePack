package Graph.UnionFind.Plan1;
import java.util.*;
/**
 * SOL:UF
 * 实质：相邻大小为1的node互为connected，求max size of connected area
 */
public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        UnionFind uf = new UnionFind(nums.length);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                continue;
            }
            map.put(nums[i], i);
            //union比nums[i]小1的nei
            Integer adjacent = map.get(nums[i] - 1);
            if (adjacent != null) {
                uf.union(i, adjacent);
            }
            //union比nums[i]大1的nei
            adjacent = map.get(nums[i] + 1);
            if (adjacent != null) {
                uf.union(i, adjacent);
            }
        }
        return uf.getMaxsize();
    }

    static class UnionFind {
        int[] roots;
        int[] sizes;
        private int maxSize;
        UnionFind(int n) {
            this.roots = new int[n];
            this.sizes = new int[n];
            this.maxSize = 1;
            for (int i = 0; i < n; i++) {
                roots[i] = i;
                sizes[i] = 1;
            }
        }
        private int getRoot(int a) {
            if (roots[a] != a) {
                roots[a] = getRoot(roots[a]);
            }
            return roots[a];
        }
        void union(int a, int b) {
            int roota = getRoot(a);
            int rootb = getRoot(b);
            if (roota == rootb) {
                return;
            }
            if (sizes[roota] > sizes[rootb]) {
                sizes[roota] += sizes[rootb];
                roots[rootb] = roota;
                maxSize = Math.max(maxSize, sizes[roota]);
            } else {
                sizes[rootb] += sizes[roota];
                roots[roota] = rootb;
                maxSize = Math.max(maxSize, sizes[rootb]);
            }
        }
        int getMaxsize() {
            return this.maxSize;
        }
    }
}

