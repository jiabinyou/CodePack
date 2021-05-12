package Graph.BFS1.Plan1;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Sol. KBFS
 * 所有1的点实际上都是init point，从这些点出发做BFS， 数出steps。
 * 检查是否全部变成zoobie：检查最后matrix中是否还有0即可
 *
 * 代码思路
 * 将所有僵尸的位置加入队列。
 * 每一轮遍历当前队顶位置，若该位置的上下左右存在能够被新感染的人类，就将该位置入队，并且步数+1
 * 知道没有新的被感染者或者人类都被感染，则退出循环。

 * 复杂度分析
 * 设地图的大小为N * M。
 * 时间复杂度：宽度优先搜索遍历整张图的时间复杂度为O(N * M)。
 * 空间复杂度：宽度优先搜索队列的开销为O(N * M)。
 * */
public class ZombieInMatrix {
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int zombie(int[][] grid) {
        // sanity check
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;

        Queue<int[]> q = new LinkedList<>();
        //init
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    q.offer(new int[]{i, j});
                }
            }
        }
        //BFS
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                //expansion
                int[] cur = q.poll();
                //Process
                int r = cur[0];
                int c = cur[1];
                for (int[] dir : DIRS) {
                    int newR = r + dir[0];
                    int newC = c + dir[1];
                    if (newR >= 0 && newR < m && newC >= 0 && newC < n && grid[newR][newC] == 0) {
                        grid[newR][newC] = 3; //mark visit at expansion
                        q.offer(new int[]{newR, newC});
                    }
                }
            }
            step++;
        }

        //checkres
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    return -1;
                }
            }
        }
        return step - 1; /**因为我们是在expansion记录step，相当于最后返回时候多走了一步*/
    }
}
