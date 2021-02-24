package Graph.BFS2.Plan2;
import java.util.*;
/**
 * Sol.Graph.BFS2
 * 在the maze II的基础上，不仅要找到到hole的shortest distance，还要讲path还原出来
 */
public class TheMazeIII {
    private static final int[][] DIRS = new int[][] {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    private static final char[] DIRECTION = new char[] {'d', 'l', 'r', 'u'};
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        //sanity check
        if (maze == null || maze.length == 0 || maze[0].length == 0) {
            return "impossible";
        }
        /**Graph.BFS2'V'*/
        PriorityQueue<Element> minHeap = new PriorityQueue<>();
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        minHeap.offer(new Element(ball[0], ball[1], 0, "")); //init root
        while (!minHeap.isEmpty()) {
            /**expansion*/
            Element cur = minHeap.poll();
            if (visited[cur.x][cur.y]) {
                continue;
            }
            visited[cur.x][cur.y] = true; /**mark visited at expansion*/
            if (cur.x == hole[0] && cur.y == hole[1]) {
                return cur.path;
            }
            /**generation*/
            for (int i = 0; i < 4; i++) { /**难点：为了recover path，我们需要i信息，需要这样写*/
                int newR = cur.x;
                int newC = cur.y;
                int neiDis = cur.distance;
                while (isValid(maze, newR + DIRS[i][0], newC + DIRS[i][1]) && maze[newR + DIRS[i][0]][newC + DIRS[i][1]] == 0) {
                    newR += DIRS[i][0];
                    newC += DIRS[i][1];
                    neiDis++;
                    if (newR == hole[0] && newC == hole[1]) {
                        break;
                    }
                }
                if (!visited[newR][newC]) {
                    minHeap.offer(new Element(newR, newC, neiDis, cur.path + DIRECTION[i]));
                }
            }
        }
        return "impossible";
    }

    private boolean isValid(int[][] maze, int i, int j) {
        return i >= 0 && i < maze.length && j >= 0 && j < maze[0].length;
    }

    static class Element implements Comparable<Element> {
        int x;
        int y;
        int distance;
        String path;
        Element(int x, int y, int distance, String path) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.path = path;
        }
        @Override
        public int compareTo(Element other) {
            if (this.distance == other.distance) {
                return this.path.compareTo(other.path);
            }
            return Integer.compare(this.distance, other.distance);
        }
    }
}

