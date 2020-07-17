package Array.Plan3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 //坑：两个array都是unsorted的，所以如果没有sort，不能直接用指针移动来dedup

 res中无dup，所以使用set
 */
public class IntersectionOfTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        //sanity check
        if (nums1 == null || nums1.length == 0 ||
                nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        Set<Integer> res = new HashSet<>();
        Set<Integer> one = new HashSet<>(); //element appear in nums1
        for (int i = 0; i < nums1.length; i++) {
            one.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (one.contains(nums2[i])) {
                res.add(nums2[i]);
            }
        }

        int[] resArr = new int[res.size()];
        int i = 0;
        for (int num : res) {
            resArr[i++] = num;
        }
        return resArr;
    }
}

/**
 * Sol2. 先sort，在sorted arr上应用经典指针法，找intersection
 */
class SolITA2 {
    public int[] intersection(int[] nums1, int[] nums2) {
        //sanity check
        if (nums1 == null || nums1.length == 0 ||
                nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        //sort first
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        Set<Integer> res = new HashSet<>();
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