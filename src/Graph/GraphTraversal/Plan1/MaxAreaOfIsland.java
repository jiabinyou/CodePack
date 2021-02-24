package Graph.GraphTraversal.Plan1;
import java.util.ArrayDeque;
import java.util.Queue;
/**
 * DFS1
 * #DFS: 因为是Traverse Vertex， 并且无环，使用DFS1足够
 * mark visited：这道题可以在graph本身做出改变，graph就能代表是否visit信息，
 *               所以不需单独准备mark visited ds
 */

/**
 * DFS1.
 * V at expansion
 */
public class MaxAreaOfIsland {
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    public int maxAreaOfIsland(int[][] grid) {
        //sanity check
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int glbMax = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    glbMax = Math.max(glbMax, dfs(grid, i, j));
                }
            }
        }
        return glbMax;
    }

    private int dfs(int[][] grid, int i, int j) {
        //base case
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return 0;
        }
        if (grid[i][j] != 1) {
            return 0;
        }
        //mark visited at expansion
        grid[i][j] = 2;
        //recurison
        int count = 1;
        for (int[] dir : DIRS) {
            int newR = dir[0] + i;
            int newC = dir[1] + j;
            count += dfs(grid, newR, newC);
        }
        return count;
    }
}

/**BFSV: -->这种方法最快
 init root
 V at generation -->单独mark visited root
 */
class SolMNI2 {
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    public int maxAreaOfIsland(int[][] grid) {
        //sanity check
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int glbMax = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    glbMax = Math.max(glbMax, bfs(grid, i, j));
                }
            }
        }
        return glbMax;
    }

    private int bfs(int[][] grid, int i, int j) {
        Queue<Cell> q = new ArrayDeque<>();
        q.offer(new Cell(i, j)); //init root
        grid[i][j] = 2; //mark visit root
        int count = 1;   //单独process root
        while (!q.isEmpty()) {
            //expansion
            Cell cur = q.poll();
            //generation
            for (int[] dir : DIRS) {
                int newR = dir[0] + cur.row;
                int newC = dir[1] + cur.col;
                if (isValid(grid, newR, newC) && grid[newR][newC] == 1) {
                    q.offer(new Cell(newR, newC));
                    count++;                       //process at generation
                    grid[newR][newC] = 2;        //mark visited at generation
                }
            }
        }
        return count;
    }

    private boolean isValid(int[][] image, int i, int j) {
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


