package Tree.Plan9;

import Tree.TreeNode;

public class ConstructBinaryTreeFromString {
    private int i = 0; //i:全局走到的cur idx
    public TreeNode str2tree(String s) {
        if (i == s.length()) {
            return null;
        }
        int j = i; //j:当前value终止的idx
        while (j < s.length() && s.charAt(j) != '(' && s.charAt(j) != ')') {
            j++;
        }
        TreeNode result = new TreeNode(Integer.valueOf(s.substring(i, j)));
        i = j;
        if (i < s.length() && s.charAt(i) == '(') {  // for the possible leftNode, if '(' met.
            i++;
            result.left = str2tree(s);
        }
        if (i < s.length() && s.charAt(i) == '(') { // for the possible leftNode, if '(' met.
            i++;
            result.right = str2tree(s);
        }
        // if not '(' it must be ')' or i==s.length(),所以我们不做recursion和connect，直接跳过这一格
        i++;
        return result;
    }
}
