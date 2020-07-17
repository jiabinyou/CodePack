package DFS3.Plan1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 input: dup val
 res: no dup idx, can have dup val
 -->需要dedup idx
 */
public class PermutationsII {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        //sanity check
        if (nums == null || nums.length == 0) {
            return res;
        }
        backTracking(nums, 0, res);
        return res;
    }

    private void backTracking(int[] nums, int index, List<List<Integer>> res) {
        //base case
        if (index == nums.length - 1) {
            res.add(new ArrayList<>(arrToList(nums)));
            return;
        }
        /**dedup关键： 当前层相同val元素跳过，不再交换(!!!如果不sort，就需要在!!每一层!!使用set去重)*/
        Set<Integer> visited = new HashSet<>();
        //add cur node influence + iterate all branches
        for (int i = index; i < nums.length; i++) {
            //non-visited才是valid branch
            if (visited.add(nums[i])) {
                swap(nums, i, index);
                backTracking(nums, index + 1, res);
                //recovery
                swap(nums, i, index);
            }
        }
    }

    private List<Integer> arrToList(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(nums[i]);
        }
        return list;
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
