package Tree.Plan2;

import Tree.TreeNode;

/**
 这里假设的是S远远大于T，所以t要么和S完全一样isSame，要么t是S的字数
 易错点1：corner case要问好
 这里我们假设S不能为null，塔是母体，所以S == null，return false
 t可以为null，因为null也算作是S的子树

 易错点2.
 我们需要比较两件事情：
 a）t是否和s是相等的，如果相等，可以直接发回true
 b）t是否是s左子树的subtree，或者右子树的subtree，如果是其中一个，返回true
 最后如果上面两种都不符合，才返回false

 所以可以看出，在这一题中，isSame只是其中一个小helper，这题比isSame要复杂
 */
public class SubtreeOfAnotherTree {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        // sanity check
        if (s == null) {
            return false;
        }
        if (t == null) {
            return true;
        }

        if (isSame(s, t)) {
            return true;
        }
        if(isSubtree(s.left, t) || isSubtree(s.right, t)) {
            return true;
        }
        return false;
    }

    private boolean isSame(TreeNode one, TreeNode two) {
        //base case
        if (one == null && two == null) {
            return true;
        }
        if (one == null || two == null) {
            return false;
        }
        if (one.val != two.val) {
            return false;
        }
        return isSame(one.left, two.left) && isSame(one.right, two.right);
    }
}