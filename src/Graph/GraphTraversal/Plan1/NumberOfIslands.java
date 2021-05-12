package Graph.GraphTraversal.Plan1;
import java.util.ArrayDeque;
import java.util.Queue;
/**
 * Traverse Vertex, DFS, BFS皆可
 */

/**
 * DFS1
 * #DFS: 因为是Traverse Vertex， 并且无环，使用DFS1足够
 * mark visited：这道题可以在graph本身做出改变，graph就能代表是否visit信息，
 *               所以不需单独准备mark visited ds
 *题目本质：想想看，这道题其实就是FloodFill，只不过那道题必须要求在graph本身做出改变，就相当于在graph本身mark visited了
 *         所以不需要独立的V ds
 *         这道题，也可以做boolean[][] visited，但是其实没有必要浪费空间，因为也完全可以利用自身mark visited
 *
 * TC:O(m*n)
 * SC:WORST CASE O(m*n)
 */
public class NumberOfIslands {
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int numIslands(char[][] grid) {
        //sanity check
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        //base case
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return;
        }
        if (grid[i][j] != '1') {
            return;
        }
        //mark visited at expansion
        grid[i][j] = '2';
        //recursion
        for (int[] dir : DIRS) {
            int newR = dir[0] + i;
            int newC = dir[1] + j;
            dfs(grid, newR, newC);
        }
    }
}

/**BFSV: -->这种方法最快
 init root
 V at generation -->单独mark visited root
 */
class SolNOI2 {
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int numIslands(char[][] grid) {
        //sanity check
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    bfs(grid, i, j);
                }
            }
        }
        return count;
    }

    private void bfs(char[][] grid, int i, int j) {
        Queue<Cell> q = new ArrayDeque<>();
        q.offer(new Cell(i, j)); //init root
        grid[i][j] = '2'; //mark visit root

        while (!q.isEmpty()) {
            //expansion
            Cell cur = q.poll();
            //generation
            for (int[] dir : DIRS) {
                int newR = dir[0] + cur.row;
                int newC = dir[1] + cur.col;
                if (isValid(grid, newR, newC) && grid[newR][newC] == '1') {
                    q.offer(new Cell(newR, newC));
                    grid[newR][newC] = '2';          //mark visited at generation
                }
            }

        }
    }

    private boolean isValid(char[][] image, int i, int j) {
        return i >= 0 && i < image.length && j >= 0 && j < image[0].length;
    }

    static class Cell {
        int row;
        int col;
        Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}


