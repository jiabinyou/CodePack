package Tree.Plan9;

import Tree.TreeNode;

public class SerializeAndDeserializeBinaryTree {
    private static final String SPLITTER = ",";
    private static final String NN = "#";  //NULL NODE
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preOrder(root, sb);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private void preOrder(TreeNode root, StringBuilder sb) {
        //base case
        if (root == null) {
            sb.append(NN);
            sb.append(SPLITTER);
            return;
        }
        //induction rule
        sb.append(root.val);
        sb.append(SPLITTER);
        //recursion
        preOrder(root.left, sb);
        preOrder(root.right, sb);
    }

    // Decodes your encoded data to tree.
    int preIdx = 0;
    public TreeNode deserialize(String data) {
        String[] arr = data.split(SPLITTER);
        return construct(arr);
    }

    private TreeNode construct(String[] arr) {
        if(arr[preIdx].equals(NN)) {
            preIdx++;
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(arr[preIdx]));
        preIdx++;
        root.left = construct(arr);
        root.right = construct(arr);
        return root;
    }
}
