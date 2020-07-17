package Tree.Plan6;

import Tree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 Sol1.BFS
 经典分层BFS,计算每一层的sum和#node即可
 */
public class AverageOfLevelsInBinaryTree {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        //sanity check
        if (root == null) {
            return res;
        }
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            double curSum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                curSum += cur.val;
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
            res.add(curSum / size);
        }
        return res;
    }
}

/**
 * Sol2.DFS
 * 也可以做，需要的信息是level和同一个level的#node，最后按照level从大到小（pureRecursion的话）
 * 计算出当前层结果，放入res
 *
 * DFS比BFS在这一题要麻烦一些
 */