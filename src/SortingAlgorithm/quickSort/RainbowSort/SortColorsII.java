package SortingAlgorithm.quickSort.RainbowSort;

import java.util.TreeMap;

/**Sol1. BF,使用treeMap*/
/**第一反应：rainbow sort
 思考：当不确定所有的指针要多少个/多少层循环，不太合适直接swap
 思考：耗费空间存放，最后build res？用treeMap<num, freq>？

 缺点：最后build res的时间复杂度太高了
 */
public class SortColorsII {
    public void sortColors2(int[] colors, int k) {
        // sanity check
        if (colors == null || colors.length == 0) {
            return;
        }
        //build freq map
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : colors) {
            if (!map.containsKey(num)) {
                map.put(num, 0);
            }
            map.put(num, map.get(num) + 1);
        }
        //build Res
        int idx = 0;
        for (int key : map.keySet()) {
            int freq = map.get(key);
            for (int j = 0; j < freq; j++) {
                colors[idx++] = key;
            }
        }
    }

    public static void main(String[] args) {
        SortColorsII obj = new SortColorsII();
        int[] arr = new int[]{2,1,1,2,2};
        int k = 2;
        obj.sortColors2(arr, k);
        System.out.println(arr);
    }
}

/**Sol2.用arr map优化
 * 因为总共input区间是固定的，数据大小就是1到k，完全可以只用含有index 1到k的array来制作freq map
 * **/
class SortColorSol2 {
    public void sortColors2(int[] colors, int k) {
        // sanity check
        if (colors == null || colors.length == 0) {
            return;
        }
        //build freq map
        int[] freqMap = new int[k + 1];
        for (int num : colors) {
            freqMap[num]++;
        }
        //build Res
        int idx = 0;
        for (int i = 0; i < freqMap.length; i++) { //index = 0是费的，无意义，可以i从1开始
            int freq = freqMap[i];
            if (freq > 0) {
                for (int j = 0; j < freq; j++) {
                    colors[idx++] = i;
                }
            }
        }
    }
}

/**Sol3. Rainbow Sort / Counting Sort*/
/**别忘了还有recursion版本的rainbow sort*/
/**
 * 原理：基于quick sort的partition
 * 假设有待sort数据区间[0, colors.length - 1], 要分成的color区间[1,2, 3...... k]
 * 使用recursion思想，each round找到color区间的mid point（midColor） ,待sort数据区间中<= color 的去左边，>color 的去右边
 *
 * n --> 待sort数据区间元素个数
 * k --> color区间颜色数目
 * TC: O(NlogK)    每次midColor左右两边分到的数据完美均衡，则recursion有logK层，每层最多需要O(N)进行sort
 * SC: O(logK）
 * */
class SorColorSol3 {
    public void sortColors2(int[] colors, int k) {
        // sanity check
        if (colors == null || colors.length == 0) {
            return;
        }
        rainbowSort(colors, 0, colors.length - 1, 1, k);
    }

    /**
     left, right: cur round unsorted data range left & right bound
     leftColor, rightColor: cur round color range left & right bound
     */
    private void rainbowSort(int[] colors, int left, int right, int leftColor, int rightColor) {
        //base case
        if (leftColor == rightColor) {
            return;
        }
        if (left >= right) {
            return;
        }

        int midColor = leftColor + (rightColor - leftColor) / 2;
        int l = left;
        int r = right;
        while (l <= r) {
            //update pointer where do not need swap
            while (l <= r && colors[l] <= midColor) {
                l++;
            }
            while (l <= r && colors[r] > midColor) {
                r--;
            }
            //else : swap
            if (l <= r) {
                int temp = colors[l];
                colors[l] = colors[r];
                colors[r] = temp;
            }
        }
        //recursion
        rainbowSort(colors, left, r, leftColor, midColor);
        rainbowSort(colors, l, right, midColor + 1, rightColor);
    }
}



