package Graph.BFS1.Plan1;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Sol1. all edge equal--> 可用BFS1求shortest distance
 * 初步思路：
 * 可以将输入矩阵的每一位1作为出发点，跑一遍BFS1，找到该位置离最近的0的距离.(输入为0的，shortest distance直接就是0)
 *
 * 难点！！
 * 这是一道all(1) to any one(0)，init是1，goal是0，按照初步思路，应该是做多次bfs，每次找到最近的0，
 * 观察是否能够reverse？
 * 可以，可以转化成any one(0) to all(1), 这样就可以使用KBFS, init是0， goal是1，一开始放入所有的0，等找到goal 1的时候，
 * 出发点0到1的距离即为shortest distance
 *
 * need V ds?！！难点！！
 * need V ds?！！！
 * 需要。这道题因为虽然我们会更改走过的goal node的值，但是原来是1,改完的很可能还是1，分辨不出来
 * 所以4d[][] graph上的kbfs，需要额外的mark visited ds
 */
public class ZeroOneMatrix {
    private static final int[][] DIRS = new int[][] {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return matrix;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<Pair> queue = new ArrayDeque<>();
        /**init all root (init: 0) */
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new Pair(i, j));
                    visited[i][j] = true; /**单独mark visited all root*/
                }
            }
        }

        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                //expansion
                Pair cur = queue.poll();
                //generation
                for (int[] dir : DIRS) {
                    int neiX = cur.x + dir[0];
                    int neiY = cur.y + dir[1];
                    if (isValid(matrix, neiX, neiY) && !visited[neiX][neiY] && matrix[neiX][neiY] == 1) { /**goal: 1*/
                        matrix[neiX][neiY] = level + 1;
                        visited[neiX][neiY] = true;  /**mark visited at generation*/
                        queue.offer(new Pair(neiX, neiY));
                    }
                }
            }
            level++;
        }
        return matrix;
    }

    private boolean isValid(int[][] matrix, int i, int j) {
        return i >= 0 && i < matrix.length && j >= 0 && j < matrix[0].length;
    }

    static class Pair {
        int x;
        int y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

