package Tree.Plan9;

import Tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        //sanity check
        if (postorder == null || postorder.length == 0 ||
                inorder == null || inorder.length == 0) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>(); //inorder(value, index)
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return construct(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1, map);
    }

    private TreeNode construct(int[] postorder, int postBegin, int postEnd,
                               int[] inorder, int inBegin, int inEnd,
                               Map<Integer, Integer> map) {
        //base case
        if (postBegin > postEnd) {
            return null;
        }
        //induction rule
        TreeNode root = new TreeNode(postorder[postEnd]);
        int rootIdx = map.get(postorder[postEnd]);
        //recursion
        //? = rootIdx - 1 - inBegin + postBegin
        root.left = construct(postorder, postBegin, rootIdx - 1 - inBegin + postBegin, inorder, inBegin, rootIdx - 1, map);
        //preEnd - 1 - ? = inEnd - (rootIdx + 1) // ? = preEnd  - 1 - inEnd + (rootIdx + 1)
        root.right = construct(postorder, postEnd - inEnd + rootIdx, postEnd - 1, inorder, rootIdx + 1, inEnd, map);
        //return
        return root;
    }
}
