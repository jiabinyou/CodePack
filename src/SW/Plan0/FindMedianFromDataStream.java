package SW.Plan0;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * Sol1. maxPQ + minPQ
 * O（nlogk)
 */
public class FindMedianFromDataStream {
    //representative ds
    PriorityQueue<Integer> l;
    PriorityQueue<Integer> r;
    /** initialize your data structure here. */
    public void MedianFinder() {
        //representative ds
        l = new PriorityQueue<>(11, Collections.reverseOrder());
        r = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if (l.isEmpty() || num <= l.peek()) {
            l.offer(num);
        } else {
            r.offer(num);
        }
        balance();
    }

    public double findMedian() {
        if (l.size() == r.size()) {  //or k % 2 == 0
            return l.peek() / 2.0 + r.peek() / 2.0;
        } else {
            return l.peek();  //和keep leftSize = rightSize + 1配合使用
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
 * Sol2. 一个treeSet
 */
class SolFMFD2 {
    private TreeSet<Element> treeset;
    private Element median;
    private int seqNum;
    /** initialize your data structure here. */
    public void MedianFinder() {
        this.treeset = new TreeSet<>();
    }

    public void addNum(int num) {
        seqNum++;
        Element cur = new Element(seqNum, num);
        if (treeset.isEmpty()) {
            median = cur;
        }
        treeset.add(cur);
        if (treeset.size() % 2 == 0 && cur.compareTo(median) < 0) {
            median = treeset.lower(median);
        } else if (treeset.size() % 2 == 1 && cur.compareTo(median) > 0) {
            median = treeset.higher(median);
        }
    }

    public double findMedian() {
        if (treeset.isEmpty()) {
            return (double)Integer.MIN_VALUE;
        }
        if (treeset.size() % 2 == 0) {
            return (median.val + treeset.higher(median).val) / 2.0;
        }
        return median.val;
    }

    static class Element implements Comparable<Element> {
        int seqNum;
        int val;
        Element(int seqNum, int val) {
            this.seqNum = seqNum;
            this.val = val;
        }
        @Override
        public int compareTo(Element other) {
            int compareResult = Integer.compare(this.val, other.val);
            if (compareResult == 0) {
                return Integer.compare(this.seqNum, other.seqNum);
            }
            return compareResult;
        }
    }
}




