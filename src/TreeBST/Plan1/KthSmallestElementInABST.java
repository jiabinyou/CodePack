package TreeBST.Plan1;
import Tree.TreeNode;

import java.util.*;
/**
 * SOL1,DFS
 * 按照inOrder，第K个遍历的node即为Kth Smallest Element
 * o(n)
 */
public class KthSmallestElementInABST {
    public int kthSmallest(TreeNode root, int k) {
        //SANITY CHECK
        if (root == null || k < 0) {
            return Integer.MAX_VALUE;
        }
        List<Integer> res = new ArrayList<>();
        dfs(root, k, res);
        return res.get(res.size() - 1);
    }

    private void dfs(TreeNode root, int k, List<Integer> res) {
        //base case
        if (root == null) {
            return;
        }
        dfs(root.left, k, res);
        if (res.size() == k) { //pruning
            return;
        }
        res.add(root.val);
        if (res.size() == k) {
            return;
        }
        dfs(root.right, k, res);
    }
}

/**
 * SOL2.Iterator
 * BST inOrder Asc iterator,走到第K步即为结果
 */
class SolKSEBST2 {
    public int kthSmallest(TreeNode root, int k) {
        //SANITY CHECK
        if (root == null || k < 0) {
            return Integer.MAX_VALUE;
        }
        AscIterator ascIter = new AscIterator(root);
        for (int i = 0; i < k - 1; i++) {
            if (ascIter.hasNext()) {
                ascIter.next();
            }
        }
        return ascIter.next();
    }

    static class AscIterator {
        private Deque<TreeNode> stack;
        AscIterator(TreeNode root) {
            this.stack = new ArrayDeque<>();
            firstNode(root, stack);
        }
        public boolean hasNext() {
            return !stack.isEmpty();
        }
        public Integer next() {
            if (!hasNext()) {
                return null;
            }
            TreeNode cur = stack.pop();
            firstNode(cur.right, stack);
            return cur.val;
        }
        private void firstNode(TreeNode root, Deque<TreeNode> stack) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        }
    }
}

