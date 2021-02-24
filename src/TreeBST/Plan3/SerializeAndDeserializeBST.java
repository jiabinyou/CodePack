package TreeBST.Plan3;
import Tree.TreeNode;

public class SerializeAndDeserializeBST {
    private static final String SPLITER = ",";
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "";
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) return;
        sb.append(root.val).append(SPLITER);
        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) return null;
        String[] array = data.split(SPLITER);
        int[] preIndex = new int[1];
        return deserialize(array, Integer.MAX_VALUE, preIndex);
    }

    private TreeNode deserialize(String[] array, int rootValue, int[] preIndex) {
        if (preIndex[0] == array.length || Integer.valueOf(array[preIndex[0]]) > rootValue) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(array[preIndex[0]]));
        preIndex[0]++;
        root.left = deserialize(array, root.val, preIndex);
        root.right = deserialize(array, rootValue, preIndex);
        return root;
    }
}


