package Graph.UnionFind;

/**Union Find介绍
 *
 * 使用场景：
 * 1.需要查询graph的connectivity状况的问题
 * 2.需要支持快速merge两个集合的问题
 *
 * 时间复杂度 union O(1), find O(1)
 * 空间复杂度 O(n)
 *
 * LintCode 1070. 账号合并
 * https://www.lintcode.com/problem/accounts-merge/
 LintCode 1014. 打砖块
 https://www.lintcode.com/problem/bricks-falling-when-hit/
 LintCode 1813. 构造二叉树
 https://www.lintcode.com/problem/construct-binary-tree/
 * */

import java.util.HashMap;
import java.util.Map;

/**Union Find模板*/
/**集合 《=》 one connected area in graph*/
public class UnionFind {
    private Map<Integer, Integer> father;
    private Map<Integer, Integer> sizeOfSet;
    private int numOfSet = 0;
    public UnionFind() {
        //初始化父指针，集合大小，集合数量
        father = new HashMap<Integer, Integer>();  //管理graph中所有集合的root节点 <集合的某个node, 该node的father node>
        sizeOfSet = new HashMap<Integer, Integer>();  //graph中各个集合的大小 map<集合的root， 集合的size>
        numOfSet = 0;  //graph中共有多少个集合
    }

    /**add: 新的graph node : x 加入 --》 作为一个新的集合加入*/
    public void add(int x) {
        /**点如果已经存在，则操作无效*/
        if (father.containsKey(x)) {
            return;
        }
         /**初始化点的父亲为 空对象null*/
         /**初始化该点所在集合大小为 1*/
         /**集合数量增加 1*/
        father.put(x, null);
        sizeOfSet.put(x, 1);
        numOfSet++;
    }

    /**Union：将两个集合合并在一起*/
    /**
     * input : x, y为需要合并的两个connected area中任意两个graph node
     * */
    public void union(int x, int y) {
        /**找到两个节点的根*/
        int rootX = find(x);
        int rootY = find(y);
        /**如果根不是同一个，则进行Union*/
        if (rootX != rootY) {
            /**将一个点的根变成新的根[将node数少的集合，并入node数多的]*/
            /** 集合数量减少 1 */
            /** 计算新的根所在集合大小 */
            father.put(rootX, rootY);  //此时rootX所在集合size较小，将rootX的父节点改成rootY，即：将rootX所在集合挂在了rootY集合的下面
            numOfSet--;
            sizeOfSet.put(rootY, sizeOfSet.get(rootX) + sizeOfSet.get(rootY));
        }
    }

    /**
     * input: 某个集合中的node x的val
     * output：该集合的root node val
     * */
    public int find(int x) {
        /** 指针 root 指向被查找的点 x */
        /** 不断找到 root 的父亲 */
        /** 直到 root 指向 x 的根节点 */
        int root = x;
        while (father.get(root) != null) {
            root = father.get(root);       //while结束此时root为：该集合的root节点
        }
        /**将路径上所有点指向根节点 root. 这一步目的：将该集合所有node都直接挂到root节点下面，方便操作*/
        while (x != root) {   //只要还没有退回到root节点
            /** 暂存 x 原本的父亲 */
            /** 将 x 指向根节点 */
            /** x 指针上移至 x 的父节点 */
            int originalFather = father.get(x);
            father.put(x, root);  //用x iterative往上遍历所有节点，将一路上遇到的所有节点，全部直接挂在root下面
            x = originalFather;
        }
        return root;
    }

    /** 两个节点连通 等价于 两个节点的根相同 */
    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }

    /**获得集合数量*/
    public int getNumOfSet() {
        return numOfSet;
    }

    /**获得某个点所在集合大小*/
    public int getSizeOfSet(int x) {
        return sizeOfSet.get(find(x));
    }
}


