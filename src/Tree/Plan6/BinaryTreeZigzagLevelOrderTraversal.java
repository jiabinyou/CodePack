package Tree.Plan6;

import Tree.TreeNode;

import java.util.*;
/**
 * 给level标号，，非偶数level，简单reverse一下即可
 */
public class BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        //sanity check
        if (root == null) {
            return res;
        }
        int level = 0;
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> curLayer = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                curLayer.add(cur.val);
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
            if (level % 2 != 0) {
                Collections.reverse(curLayer);
            }
            level++;
            res.add(new ArrayList<>(curLayer));
        }
        return res;
    }
}

/**
 * 使用deque
 * 偶数level：l to r 打印，所以和queue同，tail进head出
 * 奇数level：r to l 打印，所以和queue反，head进tail出,并且r to l generate！！！
 */
class SolBTZigzag2 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        //sanity check
        if (root == null) {
            return res;
        }
        int level = 0;
        Deque<TreeNode> dq = new ArrayDeque<>();
        dq.offer(root);
        while (!dq.isEmpty()) {
            int size = dq.size();
            List<Integer> curLayer = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                if (level % 2 == 0) {
                    TreeNode cur = dq.pollFirst();
                    curLayer.add(cur.val);
                    if (cur.left != null) {
                        dq.offerLast(cur.left);
                    }
                    if (cur.right != null) {
                        dq.offerLast(cur.right);
                    }
                } else {
                    TreeNode cur = dq.pollLast();
                    curLayer.add(cur.val);
                    if (cur.right != null) {
                        dq.offerFirst(cur.right);
                    }
                    if (cur.left != null) {
                        dq.offerFirst(cur.left);
                    }
                }
            }
            level++;
            res.add(new ArrayList<>(curLayer));
        }
        return res;
    }
}