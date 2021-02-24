package Graph.DFS3.Plan4;

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


