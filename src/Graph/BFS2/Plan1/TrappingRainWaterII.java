package Graph.BFS2.Plan1;
import java.util.*;

public class TrappingRainWaterII {
    private static final int[][] DIRS = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) {
            return 0;
        }
        PriorityQueue<Element> minHeap = new PriorityQueue<>();
        boolean[][] visited = new boolean[heightMap.length][heightMap[0].length];
        initializeState(minHeap, visited, heightMap); /**init root: 将边界点都作为root加入*/
        int result = 0;
        while (!minHeap.isEmpty()) {
            Element cur = minHeap.poll();
            for (int[] dir : DIRS) {
                int neiX = cur.x + dir[0];
                int neiY = cur.y + dir[1];
                if (isValid(heightMap, neiX, neiY) && !visited[neiX][neiY]) {
                    result += Math.max(cur.height - heightMap[neiX][neiY], 0);
                    /**有四周向中心包围，自己和下一个要遍历的nei，谁高，谁就是接下来能储水的边界*/
                    minHeap.offer(new Element(neiX, neiY, Math.max(cur.height, heightMap[neiX][neiY])));
                    visited[neiX][neiY] = true;
                }
            }
        }
        return result;
    }

    private boolean isValid(int[][] matrix, int i, int j) {
        return i >= 0 && i < matrix.length && j >= 0 && j < matrix[0].length;
    }

    private void initializeState(PriorityQueue<Element> minHeap, boolean[][] visited, int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            minHeap.offer(new Element(i, 0, matrix[i][0]));
            minHeap.offer(new Element(i, n - 1, matrix[i][n - 1]));
            visited[i][0] = true;
            visited[i][n - 1] = true;
        }
        for (int j = 0; j < n; j++) {
            minHeap.offer(new Element(0, j, matrix[0][j]));
            minHeap.offer(new Element(m - 1, j, matrix[m - 1][j]));
            visited[0][j] = true;
            visited[m - 1][j] = true;
        }
    }

    static class Element implements Comparable<Element> {
        int x;
        int y;
        int height;
        Element(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }
        @Override
        public int compareTo(Element other) {
            return Integer.compare(this.height, other.height);
        }
    }
}

