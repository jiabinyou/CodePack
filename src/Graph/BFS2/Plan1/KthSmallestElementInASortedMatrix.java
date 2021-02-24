package Graph.BFS2.Plan1;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Sol. Graph.BFS2
 *        1  1  5  7
 *        2  3  7  8
 *        3  4  8  9
 *  观察一下，会发现第一小出自：（0，0）
 *                第二小出自：（0， 1） （1， 0）
 *                ....以此类推，是一个按照BFS算法，从左上角，向右下角蔓延的过程
 * 可以维护一个size = k minHeap，每次pop出栈顶元素（当前最小），再把poll出的元素，下面/右边更小者offer进去
 * 这样反复操作k次，那么此时的栈顶元素就是第k小的
 * 时间复杂度是O(klog2k)
 *
 * need extra V ds？
 * 需要，因为在遍历过程中，会选择到之前重复的元素位置，就需要mark visit，不能重复走了
 *
 * 制作Element class：
 * 因为过程中，我们需要找到栈顶元素下面，右边元素，所以坐标位置需要记录；
 * 又因为要找到下面，右边元素中值更小的，所以要按照val排序
 * --》自定义priorityQueue，记录x, y, val, 按照val从小到大排序
 */
public class KthSmallestElementInASortedMatrix {
    private static final int[][] DIRS = new int[][]{{1, 0}, {0, 1}};/**关键：这里我们只往右边，下面走*/
    public int kthSmallest(int[][] matrix, int k) {
        //sanity check
        if (matrix == null || matrix.length == 0) {
            return Integer.MAX_VALUE;
        }
        /*自定义PQ*/
        PriorityQueue<Element> minHeap = new PriorityQueue<>();
        /**Graph.BFS2 find Kth smallest element*/
        minHeap.offer(new Element(0, 0, matrix[0][0]));
        Set<Element> visited = new HashSet<>();
        Element cur = null;
        for (int i = 0; i < k && !minHeap.isEmpty(); i++) {
            cur = minHeap.poll();
            for (int[] dir : DIRS) {
                int newR = cur.x + dir[0];
                int newC = cur.y + dir[1];
                if (newR < matrix.length && newC < matrix[0].length) { /**起点是(0, 0),且只往右，下走，只需check变大的越界条件*/
                    Element nei = new Element(newR, newC, matrix[newR][newC]);
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
