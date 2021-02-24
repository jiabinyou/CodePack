package Graph.GraphTraversal.Plan1;

import java.util.HashSet;
import java.util.Set;

public class NumberOfDistinctIslands {
    private final static int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int numDistinctIslands(int[][] grid) {
        ///sanity check
        if (grid == null || grid.length == 0 || grid[0].length== 0) {
            return 0;
        }
        Set<String> set = new HashSet<>(); /**存储所有island形状*/
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                StringBuilder sb = new StringBuilder();
                if (grid[i][j] == 1) {
                    dfs(grid, i, j, sb, 0, 0); /**以dfs的起点(i,j)为原点，记录island与原点相对位置，计入path sb中*/
                    if (set.add(sb.toString())) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private void dfs(int[][] grid, int r, int c, StringBuilder sb, int xPos, int yPos) {
        //base case
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) {
            return;
        }
        if (grid[r][c] != 1) {
            return;
        }
        //mark viisited at expansion
        grid[r][c] = 2;
        sb.append("" + xPos + yPos);  //process at expansion
        for (int[] dir : DIRS) {
            int newX = r + dir[0];
            int newY = c + dir[1];
            dfs(grid, newX, newY, sb, xPos + dir[0], yPos + dir[1]);
        }
    }
}


