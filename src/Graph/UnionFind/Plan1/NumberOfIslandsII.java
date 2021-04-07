package Graph.UnionFind.Plan1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**UnionFind算法准备：
 * 定义一个boolean[][],记录每个位置是否为island（即当前地图状态);
 * 定义初始含 n×m 个集合的并查集；
 * 一个计数器, 记录当前岛屿的个数（即集合数numOfSet）.

 步骤：
 对于operation中每一次操作：将(x, y)转化成island
 case 1： 如果（x, y)已经是island，那么res.add(现有的集合数)，在进入下一个操作即可；
 case 2： 如果（x, y)之前不是island，那么
          step 1. 先假设(x,y)成为新的单独的集合，add进去，并且mark (x,y) 成为island
          step 2. 依次遍历(x, y)的上，下，左，右四个坐标位置，查看如果它们是isLand，就与当前位置一一进行Union
          最后res.add(所有union操作结束后的集合数)

注意：并查集UnionFind的实现可以将二维坐标转换成一维的graphNode idx，即：
 graphNode idx = x * m + y; (m:总列数)
 .*/

/**
 这一题不需要求每个集合的size，所以Map<> sizeOfSet可以简化掉
 */
public class NumberOfIslandsII {
    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        List<Integer> res = new ArrayList<>();
        if (n == 0 || m == 0 || operators == null || operators.length == 0) {
            return res;
        }
        UnionFind uf = new UnionFind();
        boolean[][] isLand = new boolean[n][m];
        for (Point p : operators) {
            int i = p.x;
            int j = p.y;
            if (isLand[i][j]) {  //case 1
                res.add(uf.numOfSet);
                continue;
            }
            //else: case 2
            int idx = i * m + j;  //convert matrix pos to 1d graphNode Index
            //step 1
            uf.add(idx);
            isLand[i][j] = true;
            //step 2
            if (i - 1 >= 0 && isLand[i - 1][j]) {  //check 上
                uf.union(idx, idx - m);  //graphNode 1D idx: idx, idx - m
            }
            if (j - 1 >= 0 && isLand[i][j - 1]) { //check 左
                uf.union(idx, idx - 1);
            }
            if (i + 1 < n && isLand[i + 1][j]) { //check 下
                uf.union(idx, idx + m);
            }
            if (j + 1 < m && isLand[i][j + 1]) { //check 右
                uf.union(idx, idx + 1);
            }
            res.add(uf.numOfSet);
        }
        return res;
    }

    static class UnionFind {   /**推荐将class UnionFind写成static，放在大class内的用法，这样obj就可以直接调用class UnionFind的函数和field*/
        private Map<Integer, Integer> father;
        private int numOfSet = 0;
        public UnionFind() {
            father = new HashMap<Integer, Integer>();
            numOfSet = 0;
        }

        public void add(int x) {
            if (father.containsKey(x)) {
                return;
            }
            father.put(x, null);
            numOfSet++;
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                father.put(rootX, rootY);
                numOfSet--;
            }
        }

        public int find(int x) {
            int root = x;
            while (father.get(root) != null) {
                root = father.get(root);
            }
            while (x != root) {
                int originalFather = father.get(x);
                father.put(x, root);
                x = originalFather;
            }
            return root;
        }
    }
}

class Point {
      int x;
      int y;
      Point() { x = 0; y = 0; }
      Point(int a, int b) { x = a; y = b; }
}
