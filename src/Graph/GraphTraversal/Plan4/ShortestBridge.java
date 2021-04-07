package Graph.GraphTraversal.Plan4;
import java.util.*;

/**
        题意：1-》island， 0-》water
        2D matrix中有两个island，找到需要flip的最少的0的个数，使得两个island连在一起
*/

/**
 Sol.DFS1 + KBFS
 STEP 1.DFS
 先使用DFS找到第一个岛，将岛上的每一个点进行mark visited。
 STEP 2.KBFS
 从第一个岛的的每一个点出发，向外做BFS.
 因为每一步的cost是相同的（即步数+1）（此时BFS path<=>shortest path），
 所以当使用BFS第一次遇到unvisited的1，就是遇到了最近的1，
 此时返回的步数即为shortest path，即为需要flip的0的最小个数.
 */
public class ShortestBridge {
    private static final int[][] DIREC = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int ShortestBridge(int[][] A) {
        //sanity check
        if (A == null || A.length == 0) {
            return 0;
        }
        //step 1: find all node of first island using DFS
        /**难点:因为只能找一个island，不能每个node都遍历，可以设置一个boolean find,
         一旦找到了一个1，即第一个island，boolean标记true，后面就不再继续遍历*/
        boolean find = false;
        Queue<Cell> q = new LinkedList<>(); /**难点：dfs中就要为kbfs做准备*/
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                if (find) {
                    break;
                }
                if (A[i][j] == 1) {
                    find = true;  //第一个island找到了
                    dfs(A, i, j, q);
                    break;
                }
            }
        }

        //step 2: using KBFS
        //start from each node on the first island, record the path len until
        //find the next unvisited "1"
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                //expansion
                Cell cur = q.poll();
                int x = cur.x;
                int y = cur.y;
                //generation
                for (int[] dirs : DIREC) {
                    int newX = x + dirs[0];
                    int newY = y + dirs[1];
                    if (newX >= 0 && newX < A.length && newY >= 0 && newY < A[0].length
                            && A[newX][newY] != 2) { //只要不是遍历过的1，都可以往下走
                        if (A[newX][newY] == 1) {  //check res
                            return step;
                        }
                        A[newX][newY] = 2;  //mark visited at generation
                        q.offer(new Cell(newX, newY));
                    }
                }
            }
            step++;
        }
        //如果BFS联通不了，说明压根找不到
        return - 1;
    }

    private void dfs(int[][] A, int x, int y, Queue<Cell> q) {
        if (x < 0 || x >= A.length || y < 0 || y >= A[0].length) {
            return;
        }
        if (A[x][y] != 1) {  //check visited and 0
            return;
        }
        A[x][y] = 2; //mark visited
        q.offer(new Cell(x, y)); /**KBFS:第一个island上的点都是BFS起点*/
        //recursion
        for (int[] dirs : DIREC) {
            int newX = x + dirs[0];
            int newY = y + dirs[1];
            dfs(A, newX, newY, q);
        }
    }
}

class Cell {
    int x;
    int y;
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
