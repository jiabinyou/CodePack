package PriorityQueue;

import java.util.*;

/**
 * TC: O(KLOGN)
 * SC:O(K)
 * */
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
