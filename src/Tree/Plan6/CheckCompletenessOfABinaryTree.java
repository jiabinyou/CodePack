package Tree.Plan6;

import Tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 Sol1.BFS
 难点：
 1.需要先把所有node放进queue，这样才能查看是否出现了null node，所以写法和经典版本的BFS不太一样
 2.只能使用LinkedLIst来初始化queue，而不能用ArrayDeque（因为ArrayDeque不接受null）
 */
public class CheckCompletenessOfABinaryTree {
    public boolean isCompleteTree(TreeNode root) {
        //sanity check
        if (root == null) {
            return true;
        }
        boolean findNull = false;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            if (cur != null && findNull) {
                return false;
            } else if (cur == null) {
                findNull = true;
            } else {
                q.offer(cur.left);
                q.offer(cur.right);
            }
        }
        return true;
    }
}

/**
 * Sol2. DFS 推荐写法！！
 */
class SolCBT {
    public boolean isCompleteTree(TreeNode root) {
        //sanity check
        if (root == null) {
            return true;
        }
        ReturnType res = pureRecursion(root);
        return res.ifComplete;
    }

    private ReturnType pureRecursion(TreeNode root) {
        //base case
        if (root == null) {
            return new ReturnType(0, true, true);
        }
        //recursion
        ReturnType l = pureRecursion(root.left);
        ReturnType r = pureRecursion(root.right);
        //induction rule
        int curHeight = 1 + Math.max(l.height, r.height);
        boolean ifCurPerfect = l.ifPerfect && r.ifPerfect && l.height == r.height;
        boolean ifCurComplete = (l.ifComplete && r.ifPerfect && l.height == r.height + 1)
                || (l.ifPerfect && r.ifComplete && l.height == r.height);
        //return value
        return new ReturnType(curHeight, ifCurComplete, ifCurPerfect);
    }

}

class ReturnType {
    int height;
    boolean ifComplete;
    boolean ifPerfect;
    public ReturnType(int height, boolean ifComplete, boolean ifPerfect) {
        this.height = height;
        this.ifComplete = ifComplete;
        this.ifPerfect = ifPerfect;
    }
}
