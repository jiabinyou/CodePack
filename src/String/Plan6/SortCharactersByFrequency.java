package String.Plan6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 解法很巧妙：
 先制作freqMap<char, freq>
 排序方法：
 因为最多出现频率不会超过s.length(),可以将每个entry拿出来，使用bucket sort：
 bucket[]: idx-- freq, val: char
 只要从后往前遍历bucket[], 拿到的就是频率从大到小排列的char
 */
public class SortCharactersByFrequency {
    public String frequencySort(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        //frequecy map
        Map<Character, Integer> freqMap = new HashMap<>();
        int maxFreq = 0;
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        //bucket[] : idx-- freq, val: char
        List<Character>[] buckets = new List[s.length() + 1];
        for (Map.Entry<Character,Integer> entry : freqMap.entrySet()) {
            int freq = entry.getValue();
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(entry.getKey());
        }
        StringBuilder sb = new StringBuilder();
        for (int i = buckets.length - 1; i >= 0; i--) {  //从后向前遍历bucket
            if (buckets[i] != null) {
                for (char c : buckets[i]) {
                    int freq = freqMap.get(c);
                    for (int j = 0; j < freq; j++) {
                        sb.append(c);
                    }
                }
            }
        }
        return sb.toString();
    }
}

