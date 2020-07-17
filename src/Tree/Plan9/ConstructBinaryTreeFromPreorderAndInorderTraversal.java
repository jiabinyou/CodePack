package Tree.Plan9;

import Tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //sanity check
        if (preorder == null || preorder.length == 0 ||
                inorder == null || inorder.length == 0) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>(); //inorder(value, index)
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return construct(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
    }

    private TreeNode construct(int[] preorder, int preBegin, int preEnd,
                               int[] inorder, int inBegin, int inEnd,
                               Map<Integer, Integer> map) {
        //base case
        if (preBegin > preEnd) {
            return null;
        }
        //induction rule
        TreeNode root = new TreeNode(preorder[preBegin]);
        int rootIdx = map.get(preorder[preBegin]);
        //recursion
        //? = rootIdx - 1 - inBegin + (preBegin + 1)
        root.left = construct(preorder, preBegin + 1, rootIdx - inBegin + preBegin, inorder, inBegin, rootIdx - 1, map);
        //preEnd - ? = inEnd - (rootIdx + 1) // ? = preEnd - inEnd + (rootIdx + 1)
        root.right = construct(preorder, preEnd - inEnd + rootIdx + 1, preEnd, inorder, rootIdx + 1, inEnd, map);
        //return
        return root;
    }
}

