package Graph.BFS2.Plan2;
import java.util.*;
/**
 * Sol1. DFS1'V'
 * 这道题有一条path存在即可，是traverse vertex问题，并且grapg为4d[][], acylic --》 DFS
 * need V ?
 * 需要：如果不在原输入上直接进行自改，就需要额外的mark visited ds来保证走过的0不重复遍历
 */
public class TheMaze {
    private static final int[][] DIRS = new int[][] {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        //sanity check
        if (maze == null || maze.length == 0) {
            return false;
        }
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        return dfs(maze, start, destination, visited);
    }

    private boolean dfs(int[][] maze, int[] cur, int[] des, boolean[][] visited) {
        //base case
        if (visited[cur[0]][cur[1]]) {
            return false;
        }
        if (cur[0] == des[0] && cur[1] == des[1]) {
            return true;
        }
        visited[cur[0]][cur[1]] = true; //mark visited at expansion
        for (int[] dir : DIRS) {
            int newR = cur[0] + dir[0];
            int newC = cur[1] + dir[1];
            while (isValid(maze, newR, newC) && maze[newR][newC] == 0) {
                newR += dir[0];
                newC += dir[1];
            }
            newR -= dir[0];  /**关键：要退回障碍之前最后一个能停留的0，只有能停留的位置才能算作最后结果*/
            newC -= dir[1];
            if (!visited[newR][newC]) {
                if (dfs(maze, new int[] {newR, newC}, des, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValid(int[][] maze, int i, int j) {
        return i >= 0 && i < maze.length && j >= 0 && j < maze[0].length;
    }
}

/**
 * Sol2.BFS
 */
class SolTM2 {
    private static final int[][] DIRS = new int[][] {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        //sanity check
        if (maze == null || maze.length == 0) {
            return false;
        }
        /**Graph.BFS1'V'*/
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        queue.offer(start);    //INIT ROOT
        visited[start[0]][start[1]] = true; /**单独mark visited root*/
        while (!queue.isEmpty()) {
            /**expansion*/
            int[] cur = queue.poll();
            /**generation*/
            for (int[] dir : DIRS) {
                int newR = cur[0];
                int newC = cur[1];
                while (isValid(maze, newR, newC) && maze[newR][newC] == 0) {
                    newR += dir[0];
                    newC += dir[1];
                }
                newR -= dir[0];
                newC -= dir[1];
                if (newR == destination[0] && newC == destination[1]) {
                    return true;
                }
                if (!visited[newR][newC]) {
                    queue.offer(new int[]{newR, newC});
                    visited[newR][newC] = true;   /**mark visited at generation*/
                }
            }
        }
        return false;
    }

    private boolean isValid(int[][] maze, int i, int j) {
        return i >= 0 && i < maze.length && j >= 0 && j < maze[0].length;
    }
}


/**
 * Sol2b
 * 稍微换一下写法，不需要后退
 */
class SolTM2b {
    private static final int[][] DIRS = new int[][] {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        //sanity check
        if (maze == null || maze.length == 0) {
            return false;
        }
        /**Graph.BFS1'V'*/
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        queue.offer(start);    //INIT ROOT
        visited[start[0]][start[1]] = true; /**单独mark visited root*/
        while (!queue.isEmpty()) {
            /**expansion*/
            int[] cur = queue.poll();
            /**generation*/
            for (int[] dir : DIRS) {
                int newR = cur[0];  /**注意不可重复加*/
                int newC = cur[1];
                while (isValid(maze, newR + dir[0], newC + dir[1]) && maze[newR + dir[0]][newC + dir[1]] == 0) {
                    newR += dir[0];
                    newC += dir[1];
                }
                if (newR == destination[0] && newC == destination[1]) {
                    return true;
                }
                if (!visited[newR][newC]) {
                    queue.offer(new int[]{newR, newC});
                    visited[newR][newC] = true;   /**mark visited at generation*/
                }
            }
        }
        return false;
    }

    private boolean isValid(int[][] maze, int i, int j) {
        return i >= 0 && i < maze.length && j >= 0 && j < maze[0].length;
    }
}


