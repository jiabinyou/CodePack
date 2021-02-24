package Graph.GraphTraversal.Plan2;

/**
 * 这个plan开始，比Plan1单纯找到connected area要更难一些，比如这道题目，
 * 与边界相连的不算"被包围"
 * 这一系列题目，要求我们在边界条件上玩出更多地花样
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * Sol1.DFS1
 * 思路：
 * 这道题的难点就在，与边界相连的"O"invalid，我们可以反其道而行之，
 * 从四个边界出发，按照寻找dfs conncted area的方式，找到所有与边界相邻，connected rea的"0"，
 * 用特殊字符标记他是invalid。
 * 将所有invalid "0"标记出来后，剩下的"0"就都是符合条件的，无脑变成"X"即可
 * 本质：可以想象成invalid 0， 边界越界，遇到边界是"X"，都开当成是base case先处理掉
 *
 * need V ds？
 * 这道题是4d[][] graph，可以在graph本身做改动，完成mark visited，不需要额外ds
 */
public class SurroundedRegions {
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public void solve(char[][] board) {
        //sanity check
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        /**dfs 标记出，与上下左右相邻的"0"connected area， 标记成invalid*/
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                dfs(board, i, 0);
            }
        }
        for (int i = 0; i < m; i++) {
            if (board[i][n - 1] == 'O') {
                dfs(board, i, n - 1);
            }
        }
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') {
                dfs(board, 0, j);
            }
        }
        for (int j = 0; j < n; j++) {
            if (board[m - 1][j] == 'O') {
                dfs(board, m - 1, j);
            }
        }
        /**遍历矩阵，将剩下的'0'反转成‘X’， 将invalid signal翻转回'0'*/
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void dfs(char[][] board, int i, int j) {
        //base case
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return;
        }
        if (board[i][j] != 'O') {
            return;
        }
        //mark visit at generation
        board[i][j] = '#';
        //recursion
        for (int[] dir : DIRS) {
            int newR = dir[0] + i;
            int newC = dir[1] + j;
            dfs(board, newR, newC);
        }
    }
}

/**
 * Sol2. BFS
 */
class SolSR2 {
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public void solve(char[][] board) {
        //sanity check
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        /**dfs 标记出，与上下左右相邻的"0"connected area， 标记成invalid*/
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                bfs(board, i, 0);
            }
        }
        for (int i = 0; i < m; i++) {
            if (board[i][n - 1] == 'O') {
                bfs(board, i, n - 1);
            }
        }
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') {
                bfs(board, 0, j);
            }
        }
        for (int j = 0; j < n; j++) {
            if (board[m - 1][j] == 'O') {
                bfs(board, m - 1, j);
            }
        }
        /**遍历矩阵，将剩下的'0'反转成‘X’， 将invalid signal翻转回'0'*/
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void bfs(char[][] board, int i, int j) {
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(i, j));  //init root
        board[i][j] = '#';    //单独mark visited root
        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            for (int[] dir : DIRS) {
                int newR = cur.x + dir[0];
                int newC = cur.y + dir[1];
                if (isValid(board, newR, newC) && board[newR][newC] == 'O') {
                    queue.offer(new Pair(newR, newC));
                    board[newR][newC] = '#';  //mark visited at generation
                }
            }
        }
    }

    private boolean isValid(char[][] board, int i, int j) {
        return i >= 0 && i < board.length && j >= 0 && j < board[0].length;
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

