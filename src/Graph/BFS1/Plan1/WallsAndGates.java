package Graph.BFS1.Plan1;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Sol1. all edge equal--> 可用BFS1求shortest distance
 * 初步思路：
 * 求每个empty root，到任意一个最近的gate的距离
 * intutive：每个empty room INF作为出发点，跑一遍BFS1，找到该位置离最近的gate 0的距离.(输入为0的，shortest distance直接就是0)
 *
 * 难点！！
 * 这是一道all(INF) to any one(0)，init是INF，goal是0，按照初步思路，应该是做多次bfs，每次找到最近的0，
 * 观察是否能够reverse？
 * 可以，可以转化成any one(0) to all(INF), 这样就可以使用KBFS, init是0， goal是INF，一开始放入所有的0，等找到goal INF的时候，
 * 出发点0到INF的距离即为shortest distance
 *
 * need V ds?！！！
 * 不需要。这道题因为虽然我们会更改走过的goal node的值，并且原来是Integer.MAX_VALUE,改完的一定不会再是这个值了，所以不会重复走
 * 所以4d[][] graph上的kbfs，不需要额外的mark visited ds
 */
public class WallsAndGates {
    private static final int[][] DIRS = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) {
            return;
        }
        int m = rooms.length;
        int n = rooms[0].length;
        /**SD EDE: KBFS1*/
        Queue<Pair> queue = new ArrayDeque<>();
        /**init all root (init : 0)*/
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    queue.offer(new Pair(i, j));
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
                    if (isValid(rooms, neiX, neiY)  && rooms[neiX][neiY] == Integer.MAX_VALUE) {
                        rooms[neiX][neiY] = level + 1;
                        queue.offer(new Pair(neiX, neiY));
                    }
                }
            }
            level++;
        }
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

