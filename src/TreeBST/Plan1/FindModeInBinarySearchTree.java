package TreeBST.Plan1;
import Tree.TreeNode;

import java.util.*;
/**
 * Sol1. inOrder recursion*/
public class FindModeInBinarySearchTree {
    Map<Integer, Integer> map;   /**<node.val, freq>*/
    int max = 0;
    public int[] findMode(TreeNode root) {
        //sanity check
        if (root == null) {
            return new int[0];
        }
        this.map = new HashMap<>();

        inorder(root);

        List<Integer> list = new LinkedList<>();  //先用一个list来build res比较方便
        for (int key: map.keySet()) {
            if(map.get(key) == max) {
                list.add(key);
            }
        }
        /**convert list res to int[] res*/
        int[] res = new int[list.size()];
        for(int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    private void inorder(TreeNode node){
        if (node.left != null) {
            inorder(node.left);
        }
        map.put(node.val, map.getOrDefault(node.val, 0)+1);
        max = Math.max(max, map.get(node.val));
        if(node.right != null) {
            inorder(node.right);
        }
    }
}

/**
 * Sol2. inOrder Asc Iterator
 */
class SolFMIBST2 {
    public int[] findMode(TreeNode root) {
        //sanity check
        if (root == null) {
            return new int[0];
        }
        AscIterator ascIter = new AscIterator(root);
        int maxFreq = 0;
        int curFreq = 1;
        Integer prev = null;
        List<Integer> result = new ArrayList<>(); /**先使用list来记录结果比较方便*/
        while (ascIter.hasNext()) {
            Integer cur = ascIter.next();
            if (cur.equals(prev)) {
                curFreq++;
            } else {
                curFreq = 1;
                prev = cur;
            }
            if (curFreq > maxFreq) {  /**有了新的最大freq，需要重新记录结果*/
                result = new ArrayList<>();
                result.add(cur);
                maxFreq = curFreq;
            } else if (curFreq == maxFreq) {
                result.add(cur);
            }
        }
        /**convert list res to int[] res*/
        int[] resArray = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            resArray[i] = result.get(i);
        }
        return resArray;
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
