package Tree.Plan3;

import Tree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 思路：
 recursion计算出all subtree sum，放进maxHeap<sum, freq>中，
 按照freq从大到小排序，最后取出maxHeap top freq的所有sum值。
 TC: recursion tree o(n), n->#tree node,
 将所有<sum, freq> pair放进maxHeap ->o(nlogn)
 ->最终o(nlogn)
 SC:O(n)

 Sol2.  --> 时间上更加优化
 recursion计算出all subtree sum，放进ma<sum, freq>中
 最后遍历一遍map,找到所有maxFreq的sum，输出
 TC: recursion tree o(n), n->#tree node,
 将所有<sum, freq> pair放进map ->o(n)
 ->最终o(n)
 SC:O(n)
 */
public class MostFrequentSubtreeSum {
    public int[] findFrequentTreeSum(TreeNode root) {
        // sanity check
        if (root == null) {
            return new int[0];
        }
        Map<Integer, Integer> map = new HashMap<>();
        pureRecursion(root, map);
        //iterate map to get Res
        int glbMaxFreq = 0;
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > glbMaxFreq) {
                glbMaxFreq = entry.getValue();
                list.clear();
                list.add(entry.getKey());
            } else if (entry.getValue() == glbMaxFreq) {
                list.add(entry.getKey());
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    /**return val: the subtree sum(including cur root)*/
    private int pureRecursion(TreeNode root, Map<Integer, Integer> map) {
        //base case
        if (root == null) {
            return 0;
        }
        //recursion
        int left = pureRecursion(root.left, map);
        int right = pureRecursion(root.right, map);
        //induction rule
        int curSum = left + right + root.val;
        //update map
        map.put(curSum, map.getOrDefault(curSum, 0) + 1);
        //return val
        return curSum;
    }
}
