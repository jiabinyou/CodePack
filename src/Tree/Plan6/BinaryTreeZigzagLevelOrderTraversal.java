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

/**重要！！这次终于写明白了
 如果不使用reverse list，那么要抓住的关键就是：
 如果用queue，并且每次left->right孩子去generate下一层，得到的是left-》right打印（queue是tail进head出）
 如果希望得到zigzag顺序，我们将遍历顺序调换即可
 关键！！！！！！《《《当前层访问孩子（generate）顺序，决定的是下一层的打印顺序，所以当前层使用顺序是为了下一层需要打印的顺序服务的》》》
 e.g.所以当前层left-》right打印，就说明下一层是需要right-》left打印
 那么我们就要思考怎样实现相反打印：
 1.当前层是r-》l打印，说明下一层是l-》r打印，当前层就可以使用经典BFS中 queue的功能：tail进，head出，l->r generate（访问孩子顺序）
 2.当前层是l-》r, 说明下一层是r-》l，应该使用与经典BFS中完全相反的queue的功能：head进，tail出，r-》l generate
 --》自然而然，我们应该使用deque
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
                if (level % 2 == 0) {  //even代表当前层r->l, 那么下一层应该l->r打印：经典queue用法，tail进，head出，l-》r generate
                    TreeNode cur = dq.pollFirst();
                    curLayer.add(cur.val);
                    if (cur.left != null) {
                        dq.offerLast(cur.left);
                    }
                    if (cur.right != null) {
                        dq.offerLast(cur.right);
                    }
                } else {   //odd代表当前层l->r, 那么下一层应该r->l打印：head进，tail出，r-》l generate
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