package 面经.AmazonVO;

import java.util.*;

/** Sol.可以使用"相反"PQ，求kth largest用minHeap （就是top k问题）
 * 简历freq map，将freq使用size = k的minHeap维护起来（minHeap按照频率大小排序），
 * 将map entry一一放入minHeap中，size > k 时候，就将顶部元素移出，最后留在minheap中的就是freq top k elements
 * TC:
 * minHeap: O(nlogk)
 * build freqmap: o(n)
 *  -》O(nlogk)
 *
 * SC:
 * minheap： o(k)
 * freqMap:worst o(n)
 * ->o(n)
 */

class Element {
    int id;
    int bandwidth;
    public Element(int id, int bandwidth) {
        this.id = id;
        this.bandwidth = bandwidth;
    }
}

//public class topKBandwidth {
//    public List<Integer> topKBandWidth(Element ele, int k) {
//        List<Integer> res = new ArrayList<>();
//
//        //build map
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int i : nums) {
//            map.put(i, map.getOrDefault(i, 0) + 1);
//        }
//
//        PriorityQueue<Map.Entry<Integer, Integer>> minheap = new PriorityQueue<>(k, new Comparator<Map.Entry<Integer, Integer>>() {
//            @Override
//            public int compare(Map.Entry<Integer, Integer> m1, Map.Entry<Integer, Integer> m2) {
//                if (m1.getValue() == m2.getValue()) {
//                    return 0;
//                }
//                return m1.getValue() < m2.getValue() ? -1 : 1;
//            }
//        });
//
//        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
//            minheap.offer(entry);
//            if (minheap.size() > k) {
//                minheap.poll();
//            }
//        }
//
//        while (!minheap.isEmpty()) {
//            res.add(minheap.poll().getKey());
//            Collections.reverse(res);
//        }
//        return res;
//    }
//}
//
