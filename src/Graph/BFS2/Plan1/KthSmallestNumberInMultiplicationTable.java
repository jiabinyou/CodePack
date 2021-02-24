package Graph.BFS2.Plan1;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Sol.Graph.BFS2  （但这个解法在LC TLE）
 * 1	2	3
 * 2	4	6
 * 3	6	9
 * multiplication table定义：val = （rIdx + 1） * （cIdx + 1）
 */
public class KthSmallestNumberInMultiplicationTable {
    private static final int[][] DIRS = new int[][]{{1, 0}, {0, 1}};/**关键：这里我们只往右边，下面走*/
    public int findKthNumber(int m, int n, int k) {
        //sanity check
        // if (m == 0 || n == 0) {
        //     return Integer.MAX_VALUE;
        // }
        /*自定义PQ*/
        PriorityQueue<Element> minHeap = new PriorityQueue<>();
        /**Graph.BFS2 find Kth smallest element*/
        minHeap.offer(new Element(0, 0, 1));
        Set<Element> visited = new HashSet<>();
        Element cur = null;
        for (int i = 0; i < k && !minHeap.isEmpty(); i++) {
            cur = minHeap.poll();
            for (int[] dir : DIRS) {
                int newR = cur.x + dir[0];
                int newC = cur.y + dir[1];
                if (newR < m && newC < n) { /**起点是(0, 0),且只往右，下走，只需check变大的越界条件*/
                    Element nei = new Element(newR, newC, (newR + 1) * (newC + 1));
                    if (visited.add(nei)) {
                        minHeap.offer(nei);
                    }
                }
            }
        }
        return cur.val;
    }

    static class Element implements Comparable<Element> {
        int x;
        int y;
        int val;

        public Element(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }

        @Override
        public int compareTo(Element other) {
            return Integer.compare(this.val, other.val);
        }

        /**
         * 重写，定义针对Element class的equals
         */
        @Override
        public boolean equals(Object other) {
            if (this == other) {               //比较地址，即refenrece，如果地址相等，直接返回true
                return true;
            }
            //如果地址不等，再进行下面的比较
            if (!(other instanceof Element)) {  //比较类型是否是Element
                return false;
            }
            Element other1 = (Element) other;   //将other转换成统一的Element类型
            return this.x == other1.x && this.y == other1.y;  //最后比较x，y坐标，相同的话就也定义成equals
        }

        /**
         * default equals常根据reference生成equals，所以实质变成refenrence决定是否equal
         * 我们自定义了equals以后，就代表不一定是reference决定是否equals，所以自然要定义自己的hashCode
         * 所以：要是override equals()，就必须override hashCode()!!
         */
        @Override
        public int hashCode() {
            return x * 31 + y;
        }
    }
}

/**
 * Sol2.Binary Search
 * 找m*n乘法表中第K小的。--> 固定范围找第K大/小用binary search。
 * 二分的范围为给定乘法表取值范围[1, m * n]。每次对候选mid找乘法表中小于mid的个数，如果比K小说明mid还可以往前走。
 */
class SolKSNIMT2 {
    public int findKthNumber(int m, int n, int k) {
        int left = 1;
        int right = m * n + 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            int num = count(mid, m, n);  /**找到原输入中小于mid的数的个数*/
            if (num >= k) {  /**如果num比k大，说明mid需要往小方向走，取左半部分*/
                right = mid;
            } else {
                left = mid + 1; /**如果num比K小，说明mid还能往前走，取右半部分*/
            }
        }
        return left; //最后left right指针停留同一位置，一定是结果，输出left/right都可以
    }

    private int count(int val, int m, int n) {
        int count = 0;
        for (int i = 1; i <= m; i++) {
            int temp = Math.min(val / i , n);
            count += temp;
        }
        return count;
    }
}

