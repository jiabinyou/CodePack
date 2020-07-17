package DFS3.Plan1;

import java.util.ArrayList;
import java.util.List;


/**
 O(N!)
 */
public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        //sanity check
        if (nums == null || nums.length == 0) {
            return res;
        }
        backTracking(nums, 0, res);
        return res;
    }

    private void backTracking(int[] nums, int level, List<List<Integer>> res) {
        //base case
        if (level == nums.length) {
            List<Integer> curLayer = arrayToList(nums);
            res.add(curLayer);
            return;
        }
        //recursion
        for (int i = level; i < nums.length; i++) {
            swap(nums, i, level);
            backTracking(nums, level + 1, res);
            swap(nums, i, level);      //recover
        }
    }

    private List<Integer> arrayToList(int[] copy){
        List<Integer> list = new ArrayList<>();
        for(int num : copy){
            list.add(num);
        }
        return list;
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
