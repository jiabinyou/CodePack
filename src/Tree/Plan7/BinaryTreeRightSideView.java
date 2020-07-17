package Tree.Plan7;

import Tree.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 Sol1.BFS
 利用 l to r BFS, 每层最后一个是target性质
 */
public class BinaryTreeRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        //sanity check
        if (root == null) {
            return res;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                if (i == size - 1) {
                    res.add(cur.val);
                }
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
        }
        return res;
    }
}

/**
 Sol1b.BFS
 利用 r to l BFS,每层第一个是target性质
 */
class SolBTRS1b {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        //sanity check
        if (root == null) {
            return res;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                if (i == 0) {
                    res.add(cur.val);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
                if (cur.left != null) {
                    q.offer(cur.left);
                }
            }
        }
        return res;
    }
}

/**
 * sol2.DFS
 * 利用l to r DFS, update tail性质
 */
class SolBTRS2 {
    public List<Integer> rightSideView(TreeNode root) {
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
        if (res.size() <= level) {
            res.add(root.val);
        } else {
            res.set(level, root.val);
        }
        level += 1;
        //recursion
        backTracking(root.left, level, res);
        backTracking(root.right, level, res);
    }
}

/**最快的解法！！！
 * sol2b.DFS
 * 利用r to l DFS, 每个level遇到的第一个node一定是rightMost node性质
 */
class SolBTRS2b {
    public List<Integer> rightSideView(TreeNode root) {
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
        if (res.size() <= level) {
            res.add(root.val);
        }
        level += 1;
        //recursion
        backTracking(root.right, level, res);
        backTracking(root.left, level, res);
    }
}