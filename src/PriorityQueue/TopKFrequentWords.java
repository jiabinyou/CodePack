package PriorityQueue;

import java.util.*;

/** Sol.可以使用"相反"PQ，求kth largest用minHeap （就是top k问题）
 * 简历freq map，将freq使用size = k的minHeap维护起来（minHeap按照频率大小排序），
 * 将map entry一一放入minHeap中，size > k 时候，就将顶部元素移出，最后留在minheap中的就是freq top k elements
 * TC:
 * minHeap: O(nlogk)-》假设共有n个word
 * build freqmap: o(n)
 *  -》O(nlogk)
 *
 * SC:
 * minheap： o(k)
 * freqMap:worst o(n)
 * ->o(n)
 */
public class TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {
        List<String> res = new ArrayList<>();
        //sanity check
        if (words == null || words.length == 0) {
            return res;
        }
        //build fre map for words
        Map<String, Integer> map = new HashMap<>();
        for (String s : words) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        //k size minHeap to find k highest frequecy
        //define pq
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(k, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> m1, Map.Entry<String, Integer> m2) {
                if (m1.getValue().equals(m2.getValue())) {
                    return m2.getKey().compareTo(m1.getKey());  //频率相同，就按照字母排序从大到小
                }
                return m1.getValue() - m2.getValue(); //minHeap for frequecy
            }
        });

        //build pq
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            pq.offer(entry);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        //build res
        while (!pq.isEmpty()) {
            res.add(pq.poll().getKey());
        }
        Collections.reverse(res);
        return res;
    }
}
