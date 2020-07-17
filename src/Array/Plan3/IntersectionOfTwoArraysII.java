package Array.Plan3;

import java.util.*;

/**
 res保留dup
 */

/**
 * 将上一题set改成list即可
 */
public class IntersectionOfTwoArraysII {
    public int[] intersect(int[] nums1, int[] nums2) {
        //sanity check
        if (nums1 == null || nums1.length == 0 ||
                nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        //sort first
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> res = new ArrayList<>();
        //pointers to find intersection
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if(nums1[i] == nums2[j]) {
                res.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }

        int[] resArr = new int[res.size()];
        int k = 0;
        for (int num : res) {
            resArr[k++] = num;
        }
        return resArr;
    }
}

/**
 Sol2. uSE hASHmAP
 */
class SolIta2 {
    public int[] intersect(int[] nums1, int[] nums2) {
        //sanity check
        if (nums1 == null || nums1.length == 0 ||
                nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        Map<Integer, Integer> map = new HashMap<>(); //<value, idx>
        for (int i : nums1) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        List<Integer> res = new ArrayList<>();

        for (int ele : nums2) {
            if (map.containsKey(ele)) {
                res.add(ele);
                int count = map.get(ele);
                if (count == 1) {
                    map.remove(ele);
                } else {
                    map.put(ele, count - 1);
                }
            }
        }

        int[] resArr = new int[res.size()];
        int k = 0;
        for (int num : res) {
            resArr[k++] = num;
        }
        return resArr;
    }
}