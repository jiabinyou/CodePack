package TreeBST.Plan1;
import Tree.TreeNode;
import java.util.*;

/**
 * Sol1.BFS
 * 但是这种方法无法体现BST的优势
 */
public class TwoSumIVInputIsBST {
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        //init
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (set.contains(k - cur.val)) {
                return true;
            }
            set.add(cur.val);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        return false;
    }
}

/**
 * Sol2. self defined iterator
 */
class SolTSBST2 {
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        AscIterator ascIterator = new AscIterator(root);
        DescIterator descIterator = new DescIterator(root);
        while (ascIterator.peek().val < descIterator.peek().val) {
            int sum = ascIterator.peek().val + descIterator.peek().val;
            if (sum == k) {
                return true;
            }
            if (sum < k) {
                ascIterator.next();
            } else {
                descIterator.next();
            }
        }
        return false;
    }

    static abstract class MyIterator {
        Deque<TreeNode> stack;
        public MyIterator(TreeNode root) {
            this.stack = new ArrayDeque<>();
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        TreeNode getCur() {
            if (!hasNext()) {
                return null;
            }
            return stack.pop();
        }

        public abstract TreeNode next();

        public TreeNode peek() {
            return stack.peek();
        }

        void firstSmallestNode(TreeNode root) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        }

        void firstLargestNode(TreeNode root) {
            while (root != null) {
                stack.push(root);
                root = root.right;
            }
        }
    }

    static class AscIterator extends MyIterator {
        public AscIterator(TreeNode root) {
            super(root);
            firstSmallestNode(root);
        }
        @Override
        public TreeNode next() {
            if (!hasNext()) {
                return null;
            }
            TreeNode cur = getCur();
            firstSmallestNode(cur.right);
            return cur;
        }
    }

    static class DescIterator extends MyIterator {
        public DescIterator(TreeNode root) {
            super(root);
            firstLargestNode(root);
        }
        @Override
        public TreeNode next() {
            if (!hasNext()) {
                return null;
            }
            TreeNode cur = getCur();
            firstLargestNode(cur.left);
            return cur;
        }
    }
}
