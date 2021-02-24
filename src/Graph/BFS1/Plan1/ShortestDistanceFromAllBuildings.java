package Graph.BFS1.Plan1;
import java.util.*;
/**
 * Solution 1:可用BFS1 find SD (因为有前提：all edge weights equal)
 *
 * 思路，从矩阵上的每个位置出发一遍，使用bfs找到该位置到所有可到达的building的累计distance。
 * 最后再次遍历矩阵上的所有位置，找到一个原本是empty land 0，并且能够到达所有building，且distance最小的，
 * 这个memo下来的distance就是结果
 */
public class ShortestDistanceFromAllBuildings {
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int shortestDistance(int[][] grid) {
        int[][] distance = new int[grid.length][grid[0].length]; //new matrix save all 0 position dis
        int[][] reachNum = new int[grid.length][grid[0].length]; //how many building the pos(i, j) can reach
        int houseNum = 0;  //toal num of house (the num we need to reach)
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    distance[i][j] = Integer.MAX_VALUE;
                }
                if (grid[i][j] == 1) {
                    houseNum++;
                    distance[i][j] = Integer.MAX_VALUE;
                    bfs(grid, distance, reachNum, i, j);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < distance.length; i++) {
            for (int j = 0; j < distance[0].length; j++) {
                if (reachNum[i][j] == houseNum) {
                    min = Math.min(min, distance[i][j]);
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    class Node {
        int x;
        int y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private  void  bfs(int[][] grid, int[][] distance, int[][] reachNum, int x, int y) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(x, y)); //init root
        int level = 0;
        visited[x][y] = true;  //单独mark visited root
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                //axpansion
                Node cur = queue.poll();
                distance[cur.x][cur.y] += level;
                // generation
                for (int[] dir : DIRS) {
                    int newX = cur.x + dir[0];
                    int newY = cur.y + dir[1];
                    Node nei = new Node(newX, newY);
                    if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length
                            && grid[newX][newY] == 0 && !visited[newX][newY]) {
                        reachNum[newX][newY]++;
                        visited[newX][newY] = true;
                        queue.offer(nei);
                    }
                }
            }
            level++;
        }
    }
}

