package Tree.Plan5;

/**
 * Clarify：两个点是否一定在tree中
 *
 * 思路：
 * 先计算一下A,B两个点的depth，如果depth不一致，就把depth更大的那个node的parent pointer，先走|a depth - b depth| steps
 * 再从同样depth开始，一路往上移动parent pointer，什么时候两个parent pointer重合，就是LCA
 * */
 class ParentTreeNode {
     public ParentTreeNode parent, left, right;
 }

public class LowestCommonAncestorIIWithParentPointer {
    public ParentTreeNode lowestCommonAncestorII(ParentTreeNode root, ParentTreeNode A, ParentTreeNode B) {
        int depthA = getDepth(A);
        int depthB = getDepth(B);
        int diff = Math.abs(depthA - depthB);
        if (depthA > depthB) {
            return findLCA(A, B, diff);
        }
        return findLCA(B, A, diff);
    }

    private ParentTreeNode findLCA(ParentTreeNode longer, ParentTreeNode shorter, int diff) {
        while (diff != 0) {
            longer = longer.parent;
            diff--;
        }
        while (longer != shorter) {
            longer = longer.parent;
            shorter = shorter.parent;
        }
        return longer;
    }

    private int getDepth(ParentTreeNode root) {
        int depth = 0;
        while (root != null) {
            root = root.parent;
            depth++;
        }
        return depth;
    }
}

/**Sol2
 * 借鉴LintCode 380.intersection of two linkedList的解法。
 * p1, p2分别从A，B出发， 向root方向遍历。
 * p1达到root之后， 从B开始重新向root遍历。
 * p2达到root之后， 从A开始重新向root遍历。
 * p1和p2在第二次遍历时，一定会在第一个intersection（i.e LCA）相遇。 时间复杂度O(h)，h是数的最大高度。
 * */
class LowestCommonAncestorIIWithParentPointerSol2 {
    public ParentTreeNode lowestCommonAncestorII(ParentTreeNode root, ParentTreeNode A, ParentTreeNode B) {
        ParentTreeNode p1 = A;
        ParentTreeNode p2 = B;
        while (p1 != p2) {
            p1 = p1.parent == null ? B : p1.parent;
            p2 = p2.parent == null ? A : p2.parent;
        }
        return p1;
    }
}