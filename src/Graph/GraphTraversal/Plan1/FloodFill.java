package Graph.GraphTraversal.Plan1;
import java.util.LinkedList;
import java.util.Queue;
/**
 * Traverse Vertex, DFS, BFS皆可
 */

/**
 * DFS1
 * #DFS: 因为是Traverse Vertex， 并且无环，使用DFS1足够
 * mark visited：这道题在graph本身就要做出改变，graph就能代表是否visit信息，
 *               所以不需单独准备mark visited ds
 */
public class FloodFill {
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        //sanity check
        if (image == null || image.length == 0 || image[0].length == 0) {
            return image;
        }
        int oldColor = image[sr][sc];
        if (oldColor == newColor) {  //pruning
            return image;
        }
        dfs(image, sr, sc, oldColor, newColor);
        return image;
    }

    private void dfs(int[][] image, int r, int c, int oldColor, int newColor) {
        //base case
        if (r < 0 || r >= image.length || c < 0 || c >= image[0].length) {
            return;
        }
        if (image[r][c] != oldColor) {
            return;
        }
        //recursion
        image[r][c] = newColor;
        for (int[] dir : DIRS) {
            int newR = dir[0] + r;
            int newC = dir[1] + c;
            dfs(image, newR, newC, oldColor, newColor);
        }
    }
}

/**
 * Sl2. BFS
 */
class Solff2 {
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        //sanity check
        if (image == null || image.length == 0 || image[0].length == 0) {
            return image;
        }
        int oldColor = image[sr][sc];
        if (oldColor == newColor) {  //pruning
            return image;
        }
        /**BFS*/
        Queue<Cell> q = new LinkedList<>();
        /**process at generation, 所以root要单独处理*/
        q.offer(new Cell(sr, sc));
        image[sr][sc] = newColor;

        while (!q.isEmpty()) {
            //expansion
            Cell cur = q.poll();
            //generation
            for (int[] dir : DIRS) {
                int newR = dir[0] + cur.row;
                int newC = dir[1] + cur.col;
                if (isValid(image, newR, newC) && image[newR][newC] == oldColor) {
                    q.offer(new Cell(newR, newC));
                    image[newR][newC] = newColor;
                }
            }
        }
        return image;
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