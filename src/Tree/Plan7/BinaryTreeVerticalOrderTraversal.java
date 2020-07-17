package Tree.Plan7;

/**
 Sol1.BFS
 记录“重复col信息”
 同一个col按照从上到下顺序放入res list即可
 */

import Tree.TreeNode;

import java.util.*;

/**
 难点：需要一个二维数据结构，能够随时在head或者tail区“特定index”的list，deque办不到，只能用map
 并且！！！最后还要按照从小到大排序
 所以--》TreeMap
 */
public class BinaryTreeVerticalOrderTraversal {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        //sanity check
        if (root == null) {
            return res;
        }
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 0));
        while (!q.isEmpty()) {
            //expand
            Pair cur = q.poll();
            map.putIfAbsent(cur.col, new ArrayList<>());  //额外事情
            map.get(cur.col).add(cur.node.val);  //额外事情
            //generate
            if (cur.node.left != null) {
                q.offer(new Pair(cur.node.left, cur.col - 1));
            }
            if (cur.node.right != null) {
                q.offer(new Pair(cur.node.right, cur.col + 1));
            }
        }

        //遍历map取结果
        for (Integer key : map.keySet()) {
            res.add(new ArrayList<>(map.get(key)));
        }
        //return
        return res;
    }
}

class Pair {
    TreeNode node;
    int col;
    public Pair(TreeNode node, int col) {
        this.node = node;
        this.col = col;
    }
}
