package Tree.Plan7;

import Tree.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FindBottomLeftTreeValue {
    /**
     BFS/DFS: find 1st node val at last level
     先想到：用一个list，put all 1st node at each level
     实际上：用int，不停覆盖即可
     */

    /**
     * Sol1.BFS
     * */
    public int findBottomLeftValue(TreeNode root) {
        // Assert root not null
        //List<Integer> leftMost = new LinkedList<>(); //put all 1st node at each level
        TreeNode firstNode = null;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                if (i == 0) {
                    firstNode = cur;
                }
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
        }
        return firstNode.val;
    }
}

/**
 * Sol2.DFS
 * */
class FindBottomLeftTreeValueSol2 {
    /**
     * @param root: a root of tree
     * @return: return a integer
     */

    public int findBottomLeftValue(TreeNode root) {
        // Assert root not null
        List<Integer> firstNode = new LinkedList<>();
        dfs(root, 0, firstNode);
        return firstNode.get(firstNode.size() - 1);
    }

    private void dfs(TreeNode root, int level, List<Integer> firstNode) {
        //base case
        if (root == null) {
            return;
        }
        //induction rule
        if (level == firstNode.size()) {
            firstNode.add(root.val);
        }
        //recursion
        dfs(root.left, level + 1, firstNode);
        dfs(root.right, level + 1, firstNode);
    }
}
