package BinarySearch.BSA.MIN_MAX_ProblemSeries;

import java.util.*;


/**
 init:[0,0]
 goal:[len - 1, len - 1]
 实质：找一条init到goal node path，使得path上的最大值能够最小，这个值就是min time
 */

/**Sol1.BFS2
 * 典型的graph权重全正，求path的最小的最大值 -->BFS2 Dijkstra可解
 TC：O(ElogV) => O(m*n * log(m*n)) =>  因为m~n,并且log是以2为底
 O(n^2 * logn)
 * */
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


/**Sol2.BSA
 * 因为是MINMAX问题，很自然想到使用BSA
 * */
/**
 * 这是一个典型的最大值最小化问题(保证："path中最大值""全局最小")。通常这类问题要先考虑用Binary Search
 * */

/**
 step 1：找固定区间
 left: 0
 right: N*N-1 (根据题意，这是矩阵中会出现的最大数字)
 mid: left + (right - left) / 2;

 step 2: 找mid判断的移动规则
 （important！！！BSA中即找helper function逻辑：
 将mid类比成”实际res“，mid与实际res大：左移区间； mid与实际res小：右移区间）

 猜测一个最小的时间t（即mid），在t时刻时所有位置的水面的高度都是t，这时能从左上角的位置到达右下角。
 对于每一个猜测的最小的时间t，使用DFS或者BFS进行搜索此时是否有path从init能到goal node。

 --》BS helper function:DFS/BFS
 假设现在最小时间为t，如果计算出来：
 path存在,说明T还有继续缩小的空间，左移区间
 path不存在，说明T小了，右移区间

 最终找到的T就是我们想要的结果
 */
class SwimInRisingWaterSol2 {
    private static final int[][] DIRS = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int swimInWater(int[][] grid) {
        //sanity check
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        //BSA
        int left = 0;
        int right = n * n - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (findPath(0, 0, mid, grid, visited)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (findPath(0, 0, left, grid, visited)) {
            return left;
        }
        return right;
    }

    //bsa helper: dfs to find path
    private boolean findPath(int x, int y, int T, int[][] grid, boolean[][] visited) {
        int n = grid.length;
        // base case
        if(x < 0 || x >= n || y < 0 || y >= n || visited[x][y] || grid[x][y] > T) {
            return false;
        }
        if(x == n - 1 && y == n - 1) {   //check res
            return true;
        }
        visited[x][y] = true;
        for(int[] dir : DIRS){
            int newX = x + dir[0];
            int newY = y + dir[1];
            if(findPath(newX, newY, T, grid, visited)) {
                return true;
            }
        }
        return false;
    }
}
