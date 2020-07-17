package Tree.Plan6;


/**
 Sol1.BFS遍历到最下面一行，找到leftMost node即可
 */

import Tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 技巧：使用R TO L BFS,那么遍历的最后一个node就是要输出的target
 */
public class FindBottomLeftTreeValue {
    public int findBottomLeftValue(TreeNode root) {
        TreeNode res = null;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            if (cur.right != null) {
                q.offer(cur.right);
            }
            if (cur.left != null) {
                q.offer(cur.left);
            }
            res = cur;
        }
        return res.val;
    }
}

/**
 * Sol2.DFS
 */
/**
 * 技巧：记录height，利用l to r的DFS,每到新的level，一定最先遍历当前level的leftMost node
 */

/**
 * note:pure recursion不好写，因为需要知道最深的level信息
 */
class Solution {
    int glbMaxLevel = 0;
    TreeNode res = null;
    public int findBottomLeftValue(TreeNode root) {
        backTracking(root, 0);
        return res.val;
    }

    private void backTracking(TreeNode root, int level) {
        //base case
        if (root == null) {
            return;
        }
        //update cur path
        level += 1;
        if (level > glbMaxLevel) {
            glbMaxLevel = level;
            res = root;
        }
        //recursion
        backTracking(root.left, level);
        backTracking(root.right, level);
    }
}