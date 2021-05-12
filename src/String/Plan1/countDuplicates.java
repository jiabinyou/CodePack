package String.Plan1;

import java.util.*;

/**
 input: no dup

 Sol1:使用set
将所有无法重复add的数字放进res set中【不能使用set来放resresult】
 [5, 1, 2, 2, 1, 1]     set.add      RES（set,要去重)
  i                       T
     i                    T
        i                 T
           i              F          2
              i           F          1
                 i        F          1(DEDUP)
 !!!!!!!!!!!
 set在发现重复，再次add并且删除的时候，只能保证无重复数字，但是并不保证直接保留之前的而现在的不加上，有可能删除了之前的
 所以可能会导致最后得到的index顺序不正确
 可以直接使用list来存储数据，查找是否存在

 TC:O(N^2)
 SC:O(N)
 */
public class countDuplicates {
    public List<Integer> countduplicates(List<Integer> nums) {
        List<Integer> res = new ArrayList<>();
        //sanity check
        if (nums == null || nums.size() == 0) {
            return res;
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                if (!res.contains(num)) {
                    res.add(num);
                }
            }
        }
        return res;
    }
}

/**
 * Sol2：使用freqMap，ferq == 2的数字介入res
 * TC:O(N)
 * SC:O(N)
 * */
class countDuplicatesSol2 {
    public List<Integer> countduplicates(List<Integer> nums) {
        List<Integer> res = new ArrayList<>();
        //sanity check
        if (nums == null || nums.size() == 0) {
            return res;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.get(num) == 2) {
                res.add(num);
            }
        }
        return res;
    }
}

