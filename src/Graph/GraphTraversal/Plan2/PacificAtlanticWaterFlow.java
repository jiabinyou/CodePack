package Graph.GraphTraversal.Plan2;
import java.util.*;

/**
 * Sol1.DFS1
 * 思路：类似surrounded region
 * 反其道而行之，从四个边界出发，按照寻找dfs conncted area的方式，分别找到所有pacific,atlantic能到达的位置
 *最后遍历一遍，所有pacific & atlantic都能到达的位置，就是最终结果
 *
 * need V ds？
 * 这道题是4d int[][] graph，但是graph本身数字就是int，即使改变数字，也无法携带visit信息
 * 完成mark visited，需要额外ds
 * 方式一：用一个int[][] visited, 其中0：unvisited, 1:can visit by pacific, 2: can visit by pacific & atlantic
 * 方式二: boolean[][] pVisited, boolean aVisited -->这种语义更加清晰
 */
public class PacificAtlanticWaterFlow {
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        //sanity check
        if (matrix == null || matrix.length == 0) {
            return res;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        //dfs find all pos pacific can reach
        boolean[][] pVisited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            dfs(matrix, i, 0, pVisited);
        }
        for (int j = 0; j < n; j++) {
            dfs(matrix, 0, j, pVisited);
        }

        //dfs find all pos atlantic can reach
        boolean[][] aVisited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            dfs(matrix, i, n - 1, aVisited);
        }
        for (int j = 0; j < n; j++) {
            dfs(matrix, m - 1, j, aVisited);
        }

        //find common
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pVisited[i][j] && aVisited[i][j]) {
                    res.add(new ArrayList<>(Arrays.asList(i, j)));
                }
            }
        }
        return res;
    }

    private void dfs(int[][] matrix, int i, int j, boolean[][] visited) {
        //base case
        if (!isValidPos(matrix, i, j)) {
            return;
        }
        if (visited[i][j]) {
            return;
        }
        //mark visited at expansion
        visited[i][j] = true;
        //recursion
        for (int[] dir : DIRS) {
            int newR = dir[0] + i;
            int newC = dir[1] + j;
            if (isValidPos(matrix, newR, newC) && matrix[newR][newC] >= matrix[i][j]) { /**如果需要对newR，newC操作，必须先检查越界*/
                dfs(matrix, newR, newC, visited);
            }
        }
    }

    private boolean isValidPos(int[][] matrix, int i, int j) {
        return i >= 0 && i < matrix.length && j >= 0 && j < matrix[0].length;
    }
}

/**
 * Sol2.BFS
 */
class Solution {
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        //sanity check
        if (matrix == null || matrix.length == 0) {
            return res;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        //dfs find all pos pacific can reach
        boolean[][] pVisited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            bfs(matrix, i, 0, pVisited);
        }
        for (int j = 0; j < n; j++) {
            bfs(matrix, 0, j, pVisited);
        }

        //dfs find all pos atlantic can reach
        boolean[][] aVisited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            bfs(matrix, i, n - 1, aVisited);
        }
        for (int j = 0; j < n; j++) {
            bfs(matrix, m - 1, j, aVisited);
        }

        //find common
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pVisited[i][j] && aVisited[i][j]) {
                    res.add(new ArrayList<>(Arrays.asList(i, j)));
                }
            }
        }
        return res;
    }

    private void bfs(int[][] matrix, int i, int j, boolean[][] visited) {
        Queue<Pair> queue = new ArrayDeque<>();
        queue.offer(new Pair(i, j));  //init root
        visited[i][j] = true;   //单独mark visit root
        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            for (int[] dir : DIRS) {
                int newR = cur.x + dir[0];
                int newC = cur.y + dir[1];
                if (isValidPos(matrix, newR, newC) && !visited[newR][newC] && matrix[newR][newC] >= matrix[cur.x][cur.y]) {
                    queue.offer(new Pair(newR, newC));
                    visited[newR][newC] = true;  //mark visit at generation
                }
            }
        }
    }

    private boolean isValidPos(int[][] matrix, int i, int j) {
        return i >= 0 && i < matrix.length && j >= 0 && j < matrix[0].length;
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

/**
 * Sol3. KBFS
 * 从多个方向同时开始BFS
 */
class SolPAW3 {
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        //sanity check
        if (matrix == null || matrix.length == 0) {
            return res;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        //dfs find all pos pacific can reach
        //dfs find all pos atlantic can reach
        boolean[][] pVisited = new boolean[m][n];
        boolean[][] aVisited = new boolean[m][n];
        bfs(matrix, pVisited, true);
        bfs(matrix, aVisited, false);

        //find common
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pVisited[i][j] && aVisited[i][j]) {
                    res.add(new ArrayList<>(Arrays.asList(i, j)));
                }
            }
        }
        return res;
    }

    //checkForPacific代表的是，决定kbfs加入的初始node是pacific的，还是atlatic的
    private void bfs(int[][] matrix, boolean[][] visited, boolean checkForPacific) {
        int m = matrix.length;
        int n = matrix[0].length;
        Queue<Pair> queue = new ArrayDeque<>();
        /**KBFS: init all root. 如果单独mark v需要，单独mark visit all root*/
        //add all pacifc init root
        if (checkForPacific) {
            for (int i = 0; i < m; i++) {
                queue.offer(new Pair(i, 0));
                visited[i][0] = true;
            }
            for (int j = 0; j < n; j++) {
                queue.offer(new Pair(0, j));
                visited[0][j] = true;
            }
        } else {
            //add all atlantic init root
            for (int i = 0; i < m; i++) {
                queue.offer(new Pair(i, n - 1));
                visited[i][n - 1] = true;
            }
            for (int j = 0; j < n; j++) {
                queue.offer(new Pair(m - 1, j));
                visited[m - 1][j] = true;
            }
        }

        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            for (int[] dir : DIRS) {
                int newR = cur.x + dir[0];
                int newC = cur.y + dir[1];
                if (isValidPos(matrix, newR, newC) && !visited[newR][newC] && matrix[newR][newC] >= matrix[cur.x][cur.y]) {
                    queue.offer(new Pair(newR, newC));
                    visited[newR][newC] = true;  //mark visit at generation
                }
            }
        }
    }


    private boolean isValidPos(int[][] matrix, int i, int j) {
        return i >= 0 && i < matrix.length && j >= 0 && j < matrix[0].length;
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



