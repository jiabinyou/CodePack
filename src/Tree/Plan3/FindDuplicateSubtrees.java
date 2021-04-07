package Tree.Plan3;

import Tree.TreeNode;

import java.util.*;

/**
这道题比较特殊：
        一开始会思考是不是按照isSame题目为核心求解，发现这题不好开展；
        此时要知道判断tree相等第二种思路：求path，如果path相等也是same的。
        并且这里same的可能是人字形path，所以依然使用pureRecursion求解。并且使用
        全局的map和res来判断是否已经出现了相等的path
 */
public class FindDuplicateSubtrees {
    Map<String, Integer> map;
    List<TreeNode> res;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        map = new HashMap<>();
        res = new ArrayList<>();


        pureRecursion(root);
        return res;
    }

    //return val:所拿到的subtree的path(人字形)
    public String pureRecursion(TreeNode root) {
        //base case
        if(root == null) {
            return "#";
        }
        //recursion
        String left = pureRecursion(root.left);
        String right = pureRecursion(root.right);
        //induction rule
        String path = root.val + "|" + left + "|" + right;
        //res processing
        map.put(path, map.getOrDefault(path, 0) + 1);
        if(map.get(path) == 2) {
            res.add(root);
        }
        //return val
        return path;
    }
}
