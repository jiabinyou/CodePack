package Tree.Plan5;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**题目：
 * Given M nodes in a K-nary tree, find their lowest common ancestor.
 *
 * Assumptions
 *
 * - M >= 2.
 *
 * - There is no parent pointer for the nodes in the K-nary tree.
 *
 * - The given M nodes are guaranteed to be in the K-nary tree.
 *
 * Examples
 *
 *         5
 *
 *       /   \
 *
 *      9   12
 *
 *    / | \      \
 *
 *   1 2 3     14
 *
 *
 *
 * The lowest common ancestor of 2, 3, 14 is 5.
 *
 * The lowest common ancestor of 2, 3, 9 is 9.
 * */

class KnaryTreeNode {
    int key;
    List<KnaryTreeNode> children;
    public KnaryTreeNode(int key) {
        this.key = key;
        this.children = new ArrayList<>();
    }
}

public class LowestCommonAncestorVIKnaryTree {
    public KnaryTreeNode lowestCommonAncestor(KnaryTreeNode root, List<KnaryTreeNode> nodes) {
        //sanity check
        if (root == null) {
            return null;
        }
        Set<KnaryTreeNode> set = new HashSet<>(nodes);
        return lca(root, set);
    }

    private KnaryTreeNode lca(KnaryTreeNode root, Set<KnaryTreeNode> set) {
        //base case
        if (root == null) {
            return root;
        }
        if (set.contains(root)) {
            return root;
        }
        //induction rule (all branches)
        int count = 0; //数child subtree中有几个结果
        KnaryTreeNode curLCA = null;  //接住当前层的lca是什么，如果没有，就是null
        for (KnaryTreeNode child : root.children) {
            KnaryTreeNode res = lca(child, set);
            if (res != null) {
                count++;
                curLCA = res;
            }
        }
        if (count > 1) {
            return root;
        }
        return curLCA;  //当且仅当只有一个branch返回非null时候，才会返回curLCA
    }
}
