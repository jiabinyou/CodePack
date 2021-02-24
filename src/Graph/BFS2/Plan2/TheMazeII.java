package Graph.BFS2.Plan2;
import java.util.*;

/**
 * Sol.Graph.BFS2'V'
 * 目的：find the shortest distance for the ball to stop at the destination
 * 这道题实质：
 * 将开始滚动到停下来看成是一步，使用BFS2, 每次走滚动距离最短的那一步
 *
 * need V ?
 * 需要，因为是traverse vertex，并不好在源输入上直接修改，我们最好用extra ds做mark visited
 */
public class TheMazeII {
    private static final int[][] DIRS = new int[][] {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        //sanity check
        if (maze == null || maze.length == 0 || maze[0].length == 0) {
            return -1;
        }
        /**Graph.BFS2'V'*/
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        PriorityQueue<Element> minHeap = new PriorityQueue<>();
        minHeap.offer(new Element(start[0], start[1], 0));   //init root
        while (!minHeap.isEmpty()) {
            /**expansion*/
            Element cur = minHeap.poll();
            if (visited[cur.x][cur.y]) {
                continue;
            }
            visited[cur.x][cur.y] = true; /**mark visited at expansion*/
            /**generation*/
            for (int[] dir : DIRS) {
                int neiX = cur.x;
                int neiY = cur.y;
                int neiDis = cur.distance;
                while (isValid(maze, neiX, neiY) && maze[neiX][neiY] == 0) {
                    neiX += dir[0];
                    neiY += dir[1];
                    neiDis++;
                }
                neiX -= dir[0];
                neiY -= dir[1];
                neiDis--;
                /**捉着替换成下面：就不需要退一步了*/
                // while (isValid(maze, neiX + dir[0], neiY + dir[1]) && maze[neiX + dir[0]][neiY + dir[1]] == 0) {
                //     neiX += dir[0];
                //     neiY += dir[1];
                //     neiDis++;
                // }
                Element nei = new Element(neiX, neiY, neiDis);
                if (nei.x == destination[0] && nei.y == destination[1]) {
                    return nei.distance;
                }
                if (!visited[nei.x][nei.y]) {
                    minHeap.offer(nei);
                }
            }
        }
        return -1;
    }

    private boolean isValid(int[][] maze, int i, int j) {
        return i >= 0 && i < maze.length && j >= 0 && j < maze[0].length;
    }
    static class Element implements Comparable<Element> {
        int x;
        int y;
        int distance;
        Element(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
        @Override
        public int compareTo(Element other) {
            return Integer.compare(this.distance, other.distance);
        }
    }
}


