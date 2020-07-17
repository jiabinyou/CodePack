package Tree.Plan6;

import Tree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 Sol1.BFS
 经典分层BFS, 找出每一层val最大的node即可
 */
public class FindLargestValueInEachTreeRow {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        //sanity check
        if (root == null) {
            return res;
        }
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            int curMax = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                curMax = Math.max(curMax, cur.val);
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
            res.add(curMax);
        }
        return res;
    }
}


/**
 Sol2.DFS backTracking
 建立好res，每一个level：
 1.如果是全新的level就是直接添加
 2.如果已经有当前level信息，如果val更大，就update即可
 【类似尾部更新】
 */
class SolFLVETR2 {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        //sanity check
        if (root == null) {
            return res;
        }
        backTracking(root, 0, res);
        return res;
    }

    private void backTracking(TreeNode root, int level, List<Integer> res) {
        //base case
        if (root == null) {
            return;
        }
        //update path
        /**
         * 难点：需要先更新res，再更新level。因为level开始是0，level = 0的root信息
         *       在刚进入backTRacking时候还是未处理状态[语义：excluding cur level]
         */
        if (res.size() <= level) {
            res.add(root.val);
        } else {
            res.set(level, Math.max(res.get(level), root.val));
        }
        level += 1;
        //recursion
        backTracking(root.left, level, res);
        backTracking(root.right, level, res);
    }
}
