package SW.Plan0;

import java.util.*;

/**
 Sol1.TLE!!!!!!不是正确Sol
 将MAX_HEAP作为NF-SW的representative ds

 注意：这道题实际上因为input会有dup，那么直接使用maxHeap会出问题，下面会说明
 */
public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        //sanity check
        if (nums == null || nums.length < k || k == 0) {
            return new int[0];
        }
        //NF-SW
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(11, Collections.reverseOrder());
        int[] res = new int[nums.length - k + 1];
        for (int j = 0; j < nums.length; j++) {
            //add fast
            maxHeap.offer(nums[j]);
            //remove slow
            /**这里如果val相同，不同idx的都会被remove掉*/
            if (j >= k) {
                maxHeap.remove(nums[j - k]);
            }
            //check res
            if (j >= k - 1) {
                res[j - k + 1] = maxHeap.peek();
            }
        }
        return res;
    }
}

/**
 * 解决方法：使用treeSet，定义同样val的，按照idx从小到大排序
 * 因为treeSet按照maxHeap使用，所以取last值
 *
 * o(nlogk)  o(k)
 */
class SolSWMa2 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < k || k == 0) {
            return new int[0];
        }
        int[] result = new int[nums.length - k + 1];

        //NF-SW
        TreeSet<Element> treeSet = new TreeSet<>();  //representative ds
        for (int fast = 0; fast < nums.length; fast++) {
            //add fast
            treeSet.add(new Element(fast, nums[fast]));
            //remove slow
            if (fast >= k) {
                Element slowElement = new Element(fast - k, nums[fast - k]);
                treeSet.remove(slowElement);
            }
            //check res
            if (fast >= k - 1) {
                result[fast - k + 1] = treeSet.last().val;
            }
        }
        return result;
    }

    static class Element implements Comparable<Element> {
        int index;
        int val;

        Element(int index, int val) {
            this.index = index;
            this.val = val;
        }

        @Override
        public int compareTo(Element other) {
            int compareResult = Integer.compare(this.val, other.val);
            if (compareResult == 0) {
                return Integer.compare(this.index, other.index);
            }
            return compareResult;
        }
    }
}

/**
 * Sol3.
 * NF-SW representative ds使用MDD,存储idx
 *
 * O(N) O(K)
 */
class SolSWMa3 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < k || k == 0) {
            return new int[0];
        }
        int[] res = new int[nums.length - k + 1];

        //NF-SW
        Deque<Integer> dq = new ArrayDeque<>();  //representative ds: MDD(idx)
        for (int j = 0; j < nums.length; j++) {
            //operate MDD
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[j]) {
                dq.pollLast();
            }
            //add fast
            dq.offerLast(j);
            //remove slow
            if (j >= k) {
                if (j - dq.peekFirst() + 1 > k) {  //超出范围的删除
                    dq.pollFirst();
                }
            }
            //check res
            if (j >= k - 1) {
                res[j - k + 1] = nums[dq.peekFirst()];
            }
        }
        return res;
    }

    static class Element implements Comparable<Element> {
        int index;
        int val;

        Element(int index, int val) {
            this.index = index;
            this.val = val;
        }

        @Override
        public int compareTo(Element other) {
            int compareResult = Integer.compare(this.val, other.val);
            if (compareResult == 0) {
                return Integer.compare(this.index, other.index);
            }
            return compareResult;
        }
    }
}
