package Graph.BFS2.Plan1;
import java.util.*;

public class SwimInRisingWater {
    private static final int[][] DIRS = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int swimInWater(int[][] grid) {
        //sanity check
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        /**Graph.BFS2'V'*/
        PriorityQueue<Element> minHeap = new PriorityQueue<>();
        minHeap.offer(new Element(0, 0, grid[0][0]));  //init root
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        visited[0][0] = true;  //单独mark visited root
        while (!minHeap.isEmpty()) {
            //expansion
            Element cur = minHeap.poll();
            //generation
            for (int[] dir : DIRS) {
                int neiX = cur.x + dir[0];
                int neiY = cur.y + dir[1];
                if (isValid(grid, neiX, neiY) && !visited[neiX][neiY]) {
                    int neiCost = Math.max(cur.cost, grid[neiX][neiY]);
                    if (neiX == grid.length - 1 && neiY == grid[0].length - 1) {
                        return neiCost;
                    }
                    minHeap.offer(new Element(neiX, neiY, neiCost));
                    visited[neiX][neiY] = true;  //mark visited at generation
                }
            }
        }
        return 0;
    }
    private boolean isValid(int[][] grid, int i, int j) {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length;
    }
    static class Element implements Comparable<Element> {
        int x;
        int y;
        int cost;
        Element(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
        @Override
        public int compareTo(Element other) {
            return Integer.compare(this.cost, other.cost);
        }
    }
}

