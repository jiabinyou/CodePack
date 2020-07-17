package Tree.Plan9;

import Tree.Plan1.Node;

import java.util.ArrayList;

public class SerializeAndDeserializeNaryTree {
    /**
     这里只能用char，不能用string，因为下面要用到s.charAt(idx) == ?
     */
    private static final char LS = '[';  //left symbol
    private static final char RS = ']';  //right symbol
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        //sanity check
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        preOrder(root, sb);
        return sb.toString();
    }

    private void preOrder(Node root, StringBuilder sb) {
        //base case
        if (root == null) {
            return;
        }
        sb.append(LS);
        sb.append(root.val);
        for (Node child : root.children) {
            preOrder(child, sb);
        }
        sb.append(RS);
    }

    // Decodes your encoded data to tree.
    int preIdx = 0;
    public Node deserialize(String data) {
        //sanity check
        if (data == null || data.length() == 0) {
            return null;
        }
        return construct(data);
    }

    private Node construct(String data) {
        preIdx++;
        int nextInt = 0;
        while (data.charAt(preIdx) != LS && data.charAt(preIdx) != RS) {
            nextInt = nextInt * 10 + data.charAt(preIdx) - '0';
            preIdx++;
        }

        Node root = new Node(nextInt, new ArrayList<>());
        while (data.charAt(preIdx) != RS) {  //说明当前root的孩子还没遍历完
            root.children.add(construct(data));
        }
        preIdx++;
        //return val
        return root;
    }
}

