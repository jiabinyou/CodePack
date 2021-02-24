package TreeBST.Plan1;
import Tree.TreeNode;

import java.util.*;
/**
 * R + SW
 * 从左往右按照inOrder顺序遍历，每次把新（fast）元素放进tail一端
 * 维护size = k的deque，根据tail和head离target的距离，决定哪一端元素是要被扔掉的slow
 */
public class ClosestBinarySearchTreeValueII {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        //sanity check
        if (root == null || k < 1) {
            return new ArrayList<>();
        }
        Deque<Integer> window = new ArrayDeque<>();
        inorder(root, target, k, window);
        return new ArrayList<>(window);
    }

    private void inorder(TreeNode root, double target, int k, Deque<Integer> window) {
        if (root == null) {
            return;
        }
        inorder(root.left, target, k, window);
        //inOrder induction rule
        window.offerLast(root.val);
        if (window.size() > k) {  //check res
            if (Math.abs(window.peekLast() - target) > Math.abs(window.peekFirst() - target)) { //size > k时候，尾巴大就poll尾巴
                window.pollLast();
                return;
            }
            window.pollFirst(); //头大就poll头
        }
        inorder(root.right, target, k, window);
    }
}

/**
 * Sol2.Ator + Dtor
 * 每往从target开始，往Asc和Dsc各组一步，看谁离target更近
 */
class SolCBSTVII2 {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        //sanity check
        if (root == null || k < 1) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        if (root == null || k == 0) return result;
        AscIterator ascIterator = new AscIterator(root, target);
        DescIterator descIterator = new DescIterator(root, target);
        TreeNode nextSmaller = descIterator.next();
        TreeNode nextLarger = ascIterator.next();
        for (int i = 0; i < k; i++) {
            if (nextSmaller == null && nextLarger == null) {
                return new ArrayList<>();
            } else if (nextSmaller == null || (nextLarger != null && isCloser(nextSmaller, nextLarger, target) == nextLarger)) {
                result.add(nextLarger.val);
                nextLarger = ascIterator.next();
            } else if (nextLarger == null || (nextSmaller != null && isCloser(nextSmaller, nextLarger, target) == nextSmaller)) {
                result.add(nextSmaller.val);
                nextSmaller = descIterator.next();
            }
        }
        return result;
    }
    private TreeNode isCloser(TreeNode nextSmaller, TreeNode nextLarger, double target) {
        if (Math.abs(nextSmaller.val - target) < Math.abs(nextLarger.val - target)) {
            return nextSmaller;
        } else {
            return nextLarger;
        }
    }
}

class AscIterator { //elements are smallestLarger
    private Deque<TreeNode> stack;
    AscIterator(TreeNode root, double target) {
        stack = new ArrayDeque<>();
        while (root != null) {
            if (root.val > target) {
                stack.push(root);
                root = root.left;
            } else {
                root = root.right;
            }
        }
    }
    public boolean hasNext() {
        return !stack.isEmpty();
    }
    public TreeNode next() {
        if (!hasNext()) return null;
        TreeNode cur = stack.pop();
        TreeNode res = cur;
        cur = cur.right;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        return res;
    }
}

class DescIterator{ // elements are largestSmallerOrEquals
    private Deque<TreeNode> stack;
    DescIterator(TreeNode root, double target) {
        stack = new ArrayDeque<>();
        while (root != null) {
            if (root.val <= target) {
                stack.push(root);
                root = root.right;
            } else {
                root = root.left;
            }
        }
    }
    public boolean hasNext() {
        return !stack.isEmpty();
    }
    public TreeNode next() {
        if (!hasNext()) return null;
        TreeNode cur = stack.pop();
        TreeNode res = cur;
        cur = cur.left;
        while (cur != null) {
            stack.push(cur);
            cur = cur.right;
        }
        return res;
    }
}



