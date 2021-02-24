package TreeBST.Plan1;

/***有parent pointer，但是没有根root信息
 *  可以采用itera贴方式，通过parent来判断要回到哪一层为止
 */
public class InorderSuccessorInBSTII {
    public Node inorderSuccessor(Node node) {
        if (node == null) {
            return null;
        }
        if (node.right != null) {
            return firstNode(node.right);
        }
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        return node.parent;
    }

    private Node firstNode(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }
}

