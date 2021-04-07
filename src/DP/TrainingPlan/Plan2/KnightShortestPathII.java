package DP.TrainingPlan.Plan2;

import java.util.LinkedList;
import java.util.Queue;

/**Sol1: BFS1
 graph --> shortest path问题，每走一步cost 1权重相同-->BFS1
 要mark visited -->input本身就是graph，可在input上mark visit成1（true）
 init(0,0)
 goql(m - 1,n - 1)
 易错点：inpur是boolean matrix，不要看错了
 */
public class KnightShortestPathII {
    private static final int[][] DIREC = new int[][]{{1,2}, {-1,2}, {2,1}, {-2,1}};
    public int shortestPath2(boolean[][] grid) {
        // sanity check
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        if (grid[m - 1][n - 1]) {
            return -1;
        }
        //BFS1
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(0, 0));
        grid[0][0] = true;  //单独mark visit root
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Pair cur = q.poll();
                //generation
                for (int[] dir : DIREC) {
                    int newX = cur.x + dir[0];
                    int newY = cur.y + dir[1];
                    //check Res
                    if (newX == m - 1 && newY == n - 1) {
                        return step + 1;
                    }
                    if (isValidPos(m, n, newX, newY) && !grid[newX][newY]) {
                        grid[newX][newY] = true;  //mark visited at generation
                        q.offer(new Pair(newX, newY));
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private boolean isValidPos(int m, int n, int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}

class Pair {
    int x;
    int y;
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

/**
 * Sol2.
 * */
