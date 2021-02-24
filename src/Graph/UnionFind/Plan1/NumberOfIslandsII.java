package Graph.UnionFind.Plan1;
import java.util.*;
/**
 * SOL:UF
 * 这是典型的动态连接问题，根据input新建小岛，并和原来的连接在一起(UNION)
 * 每union操作一次后，找到# connected area （FIND相关）
 */
public class NumberOfIslandsII {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> result = new ArrayList<>();
        //sanity check
        if (m <= 0 || n <= 0 || positions == null || positions.length == 0 || positions[0].length == 0) {
            return result;
        }
        UnionFind uf = new UnionFind(m * n);
        boolean[][] isLand = new boolean[m][n];
        for (int[] p : positions) {
            int i = p[0];
            int j = p[1];
            if (isLand[i][j]) {
                result.add(uf.count);
                continue;
            }
            /**原本不是land，需调用UF进行union*/
            uf.count++;
            isLand[i][j] = true;

            int id = i * n + j; /**关键！！input是二维，转换成UF中的一维展开idx*/
            if (i - 1 >= 0 && isLand[i - 1][j]) {   //查上面，id - n是id在一维idx中上面一个的idx，下面以此类推
                uf.union(id, id - n);
            }
            if (j - 1 >= 0 && isLand[i][j - 1]) {   //查左边
                uf.union(id, id - 1);
            }
            if (i + 1 < m && isLand[i + 1][j]) {    //查下面
                uf.union(id, id + n);
            }
            if (j + 1 < n && isLand[i][j + 1]) {    //查右边
                uf.union(id, id + 1);
            }
            result.add(uf.count);
        }
        return result;
    }

    /**UF工具class，包括getRoot和Union两个helper method*/
    static class UnionFind {
        int[] roots;   //所有node的root idx
        int[] sizes;   //每个node连接的connected area的size
        int count; //graph中# connected area
        UnionFind(int n) {  /**n -- # graph node*/
            this.roots = new int[n];
            this.sizes = new int[n];
            for (int i = 0; i < n; i++) {   //init所有node的初始root idz
                roots[i] = i;
                sizes[i] = 1;
            }
        }

        int getRoot(int a) {
            if (a != roots[a]) {
                roots[a] = getRoot(roots[a]); /**如果不是初始idx，说明之前union过，用recursion找最上级的idx*/
            }
            return roots[a];
        }

        void union(int a, int b) {
            int roota = getRoot(a);
            int rootb = getRoot(b);
            if (roota == rootb) {
                return;
            }
            count--;
            /**将a与b所在两个connected area的全部rootIdx合并，关键！！size小的合并进size大的*/
            if (sizes[roota] >= sizes[rootb]) {
                roots[rootb] = roota;
                sizes[roota] += sizes[rootb];
            } else {
                roots[roota] = rootb;
                sizes[roota] += sizes[rootb];
            }
        }
    }
}
