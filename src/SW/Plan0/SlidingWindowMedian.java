package SW.Plan0;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * Sol1. maxPQ + minPQ
 * O（nlogk)
 */

/**
 * 小细节比较多，下面用注释标注出来
 */
public class SlidingWindowMedian {
    PriorityQueue<Integer> l = new PriorityQueue<>(11, Collections.reverseOrder()); //left pq, max pq
    PriorityQueue<Integer> r = new PriorityQueue<>(); //right pq, min pq
    public double[] medianSlidingWindow(int[] nums, int k) {
        //sanity check
        if (nums == null || nums.length < k || k == 0) {
            return new double[0];
        }
        //FS-SW
        double[] res = new double[nums.length - k + 1];
        for (int j = 0; j < nums.length; j++) {
            //add fast
            addNum(nums[j]);
            //remove slow
            if (j >= k) {
                removeNum(nums[j - k]);
            }
            //check res
            if (j >= k - 1) {
                res[j - k + 1] = getMedian();
            }
        }
        return res;
    }

    private void addNum(int num) {
        if (l.isEmpty() || num <= l.peek()) {    //<=
            l.offer(num);
        } else {
            r.offer(num);
        }
        balance(); //需要balance
    }

    private void removeNum(int num) {
        if (num <= l.peek()) {  //<=
            l.remove(num);  //remove(num), 而不是poll
        } else {
            r.remove(num);  //remove(num), 而不是poll
        }
        balance(); //需要balance
    }

    private double getMedian() {
        if (l.size() == r.size()) {
            return l.peek() / 2.0 + r.peek() / 2.0;
        } else {
            return l.peek();
        }
    }

    private void balance() {
        if (l.size() > r.size() + 1) {
            r.offer(l.poll());
        } else if (l.size() < r.size()) {
            l.offer(r.poll());
        }
    }
}


/**
 * Sol2. maxTreeSet + minTreeSet
 *   O（nlogk)
 */
class Solution {
    /**coding难点：
     1.
     两个都是小到大的treeSet, fisrt是min，last是max
     所以left treeSet全部取last
        right treeSet全部取first

     2.TreeSet本身不能处理duplicate，
     如果input含dup，就要建立Element class，重新排序，val相同元素index放在前面
     */
    TreeSet<Element> l = new TreeSet<>();  //需要max，取last
    TreeSet<Element> r = new TreeSet<>();  //需要min，取first
    public double[] medianSlidingWindow(int[] nums, int k) {
        //sanity check
        if (nums == null || nums.length < k || k == 0) {
            return new double[0];
        }
        double[] res = new double[nums.length - k + 1];

        //FS-SW
        for (int j = 0; j < nums.length; j++) {
            //add fast
            Element fastEle = new Element(j, nums[j]);
            addNum(fastEle);
            //remove slow
            if (j >= k) {
                Element slowEle = new Element(j - k, nums[j - k]);
                removeNum(slowEle);
            }
            //check res
            if (j >= k - 1) {
                res[j - k + 1] = getMedian();
            }
        }
        return res;
    }

    private void addNum(Element ele) {
        if (l.isEmpty() || ele.val <= l.last().val) {
            l.add(ele);
        } else {
            r.add(ele);
        }
        rebalance(l, r);
    }

    private void removeNum(Element ele) {
        if (l.contains(ele)) {
            l.remove(ele);
        } else {
            r.remove(ele);
        }
        rebalance(l, r);
    }

    private double getMedian() {
        if (l.size() == r.size()) {
            return l.last().val / 2.0 + r.first().val / 2.0;
        } else {
            return l.last().val;
        }
    }

    private void rebalance(TreeSet<Element> l, TreeSet<Element> r) {
        if (l.size() > r.size() + 1) {
            // Element tmp = l.last();
            // l.remove(tmp);
            // r.add(tmp);
            r.add(l.pollLast());
        } else if (r.size() > l.size()) {
            // Element tmp = r.first();
            // r.remove(tmp);
            // l.add(tmp);
            l.add(r.pollFirst());
        }
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
            if (this.val == other.val) {
                return Integer.compare(this.index, other.index);
            }
            return Integer.compare(this.val, other.val);
        }
    }
}



/**
 * Sol3. 一个treeSet
 */
//One TreeSet
class SolSWM3 {
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < k || k == 0) {
            return new double[0];
        }
        TreeSet<Element> treeSet = new TreeSet<>();
        Element median = new Element(0, nums[0]);
        for (int fast = 0; fast < k; fast++) {
            Element cur = new Element(fast, nums[fast]);
            treeSet.add(cur);
            if (treeSet.size() % 2 == 0 && cur.compareTo(median) < 0) {
                median = treeSet.lower(median);
            } else if (treeSet.size() % 2 == 1 && cur.compareTo(median) > 0) {
                median = treeSet.higher(median);
            }
        }
        double[] result = new double[nums.length - k + 1];
        result[0] = k % 2 == 1 ? median.val : median.val / 2.0 + treeSet.higher(median).val / 2.0;
        for (int fast = k; fast < nums.length; fast++) {
            Element cur = new Element(fast, nums[fast]);
            treeSet.add(cur);
            Element slow = new Element(fast - k, nums[fast - k]);
            if (cur.compareTo(median) < 0 && slow.compareTo(median) >= 0) {
                median = treeSet.lower(median);
            } else if (cur.compareTo(median) > 0 && slow.compareTo(median) <= 0) {
                median = treeSet.higher(median);
            }
            treeSet.remove(slow);
            result[fast - k + 1] = k % 2 == 1 ? median.val : median.val / 2.0 + treeSet.higher(median).val / 2.0;

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
            int res = Integer.compare(this.val, other.val);
            if (res == 0) {
                return Integer.compare(this.index, other.index);
            }
            return res;
        }
    }
}
