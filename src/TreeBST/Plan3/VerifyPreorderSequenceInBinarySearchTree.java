package TreeBST.Plan3;

public class VerifyPreorderSequenceInBinarySearchTree {
    public boolean verifyPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return true;
        }
        int[] preIndex = new int[1];
        return isValid(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE, preIndex);
    }

    private boolean isValid(int[] preorder, int min, int max, int[] preIndex) {
        if (preIndex[0] == preorder.length || preorder[preIndex[0]] > max) {
            return true;
        }
        if (preorder[preIndex[0]] < min) {
            return false;
        }
        int rootVal = preorder[preIndex[0]];
        preIndex[0]++;
        return isValid(preorder, min, rootVal, preIndex) && isValid(preorder, rootVal, max, preIndex);
    }
}

