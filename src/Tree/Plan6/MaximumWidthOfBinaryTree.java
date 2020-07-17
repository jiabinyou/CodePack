package Tree.Plan6;

import Tree.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 Sol1.BFS
 因为width定义是两个end node距离，包括null，我们需要“不重叠col信息”
 */

/**
 “不重叠col信息”：
 leftChild col = 2 * parent col
 rightChild col = 2 * parent col + 1
 */
public class MaximumWidthOfBinaryTree {
    public int widthOfBinaryTree(TreeNode root) {
        int glbMaxWidth = 0;
        //sanity check
        if (root == null) {
            return 0;
        }
        Deque<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 0));
        while (!q.isEmpty()) {
            int size = q.size();
            int headPos = q.peek().col;
            for (int i = 0; i < size; i++) {
                //expand
                Pair cur = q.poll();
                if (cur.node.left != null) {
                    q.offer(new Pair(cur.node.left, 2 * cur.col));
                }
                if (cur.node.right != null) {
                    q.offer(new Pair(cur.node.right, 2 * cur.col + 1));
                }
                glbMaxWidth = Math.max(glbMaxWidth, cur.col - headPos + 1);
            }
        }
        return glbMaxWidth;
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

/**
 * Sol2.DFS
 * ，使用一个二维list，每个index对应的是每一个level的minCol和maxCol
 * index    minCol   maxCol
 *   0
 *   1
 *   ......
 *
 *   在recursion过程中更新这个list即可，最后遍历一遍list拿到最终的结果
 */

/**
 * Tips:
 * 初始化list的方法：
 * 1.
 *             List<Integer> newList = new ArrayList<>();
 *             newLevel.add(a);  //min col
 *             newLevel.add(b);  //max col
 *             list.add(level, new ArrayList<>(newList));
 * 2.
 *              list.add(level, new ArrayList<>(Arrays.asList(a, b)));
 */
class SolMWBT2 {
    public int widthOfBinaryTree(TreeNode root) {
        //<level, List<>(minCol, maxCol)>一个level的min，max col
        List<List<Integer>> list = new ArrayList<>();
        //sanity check
        if (root == null) {
            return 0;
        }
        //recursion更新list
        backTracking(new Pair(root, 0), 0, list);
        //遍历list拿到结果
        int glbMaxWidth = 0;
        for (int i = 0; i < list.size(); i++) {
            glbMaxWidth = Math.max(glbMaxWidth, list.get(i).get(1) - list.get(i).get(0) + 1);
        }
        return glbMaxWidth;
    }

    private void backTracking(Pair root, int level, List<List<Integer>> list) {
        //base case
        if (root.node == null) {
            return;
        }
        //update path
        if (list.size() <= level) {
            List<Integer> newLevel = new ArrayList<>();
            newLevel.add(root.col);  //min col
            newLevel.add(root.col);  //max col
            list.add(level, new ArrayList<>(newLevel));
        } else {
            int min = Math.min(list.get(level).get(0), root.col);
            list.get(level).set(0, min);
            int max = Math.max(list.get(level).get(1), root.col);
            list.get(level).set(1, max);
        }
        level += 1;
        //recursion
        backTracking(new Pair(root.node.left, 2 * root.col), level, list);
        backTracking(new Pair(root.node.right, 2 * root.col + 1), level, list);
    }
}

/**
 * 简化一下backTracking的代码
 */

//private void backTracking(Pair root, int level, List<List<Integer>> list) {
//    //base case
//    if (root.node == null) {
//        return;
//    }
//    //update path
//    if (list.size() <= level) {
//        list.add(level, new ArrayList<>(Arrays.asList(root.col, root.col)));
//    } else {
//        list.get(level).set(0, Math.min(list.get(level).get(0), root.col));
//        list.get(level).set(1, Math.max(list.get(level).get(1), root.col));
//    }
//    level += 1;
//    //recursion
//    backTracking(new Pair(root.node.left, 2 * root.col), level, list);
//    backTracking(new Pair(root.node.right, 2 * root.col + 1), level, list);
//}