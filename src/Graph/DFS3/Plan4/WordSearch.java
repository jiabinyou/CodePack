package Graph.DFS3.Plan4;


/**Sol.dfs3
 * 这是一个graph问题，board上每个位置是一个graph node，每个点之间，凡是可以走的，上下左右的四个方向，都是edge
 * To solve this problem, we can use depth first search to find if the given word is a path in the graph.
 * DEF
 * level：word上，每走一步的index是一层level
 * branch：四个方向，有四个branch
 * base case：当index == word.length - 1,说明找到了
 *
 * TC:O(b^v),   b: 4,v: word length(assume K)
 * -->O(4^K)
 * SC:
 * 1.recursion call stack: >O(word length) = O(K)
 * 2.mark visit: O(M*N)
 * 谁大取谁
 * */
public class WordSearch {
    private static final int[][] DIRS = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public boolean exist(char[][] board, String word) {
        //sanity check
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if(backTracking(board, visited, i, j, 0, word)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean backTracking(char[][] board, boolean[][] visited, int r, int c, int index, String word) {
        //base case
        if (index == word.length()) {
            return true;
        }
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length) {
            return false;
        }
        if (visited[r][c]) {
            return false;
        }
        if (word.charAt(index) != board[r][c]) {
            return false;
        }
        //recursion
        visited[r][c] = true; /**mark visit*/
        for (int[] dir : DIRS) {
            int newR = r + dir[0];
            int newC = c + dir[1];
            if (backTracking(board, visited, newR, newC, index + 1, word)) {  /**一个是true，就可返回true*/
                return true;
            }
        }
        visited[r][c] = false; /**recover*/
        return false;
    }
}


