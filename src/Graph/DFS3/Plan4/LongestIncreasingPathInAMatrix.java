package Graph.DFS3.Plan4;

/**解法：backtracking
 * this is a graph problem. The vertex is each element in the given matrix.
 * There edge of each element depends on how many direction that it can go. On the edge there are 2 edges, in the middle there are 4 edges.
 * To solve this problem:
 * 我们可以使用backtracking找到所有的increasing path的长度，再从所有长度中取出glbMax Len即可。
 * tip：
 * 1.因为所求的是strictly increasing path，所有在同一条path上，不可能成环，也不可能revisit the same element--》所以实际上不需要mark visited！
 * 2.可优化：因为我们是take each element as start point，去找所有的increasing path，得到以该element为起点，最长increasing path的长度"maxCur"
 * 实际上从每个element出发，最多只能走四个方向，
 *   那个该path的最长increasing长度一定出自这几条path中的一个结果
 *   --》所以"以每个element为起点，得到的maxCur"实际上是定值，
 *   这就说明这道题可以简化成使用pure recursion + memo的形式，使用maxCur[i][j]来记录以（i,j)为起点，能得到的最长increasing path的长度
 *   以(i,j)开头的pure recursion过程结束，更新maxCur[i][j]即可
 *
 *   TC:O(M*N), M*N SIZE MATRIX->因为是pure recursion traverse
 *   SC:O(M*N)
 *
 *
 * */
public class LongestIncreasingPathInAMatrix {
    public static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int longestIncreasingPath(int[][] matrix) {
        if(matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] maxCur = new int[m][n];
        int glbMax = 1;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int len = dfs(matrix, i, j, m, n, maxCur);
                glbMax = Math.max(glbMax, len);
            }
        }
        return glbMax;
    }

    //return val: cur path length
    public int dfs(int[][] matrix, int i, int j, int m, int n, int[][] maxCur) {
        if(maxCur[i][j] != 0) return maxCur[i][j];
        int glbMax = 1;
        for(int[] dir: dirs) {
            int x = i + dir[0], y = j + dir[1];
            if(x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] <= matrix[i][j]) continue;
            int len = 1 + dfs(matrix, x, y, m, n, maxCur);
            glbMax = Math.max(glbMax, len);
        }
        maxCur[i][j] = glbMax;
        return glbMax;
    }
}
