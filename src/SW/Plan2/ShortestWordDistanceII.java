package SW.Plan2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 技巧：多次call，重复call，可考虑做preComputation
 * 这里记录的是invert idx list，在这个list上面，指针可以向右不回头
 * 所以可以简化到two pointers谁小移谁:now we get list f indices, the idea is to use "two pointer approach"
 * on the invert list of indices to find :the pair of indices (i, j) such that their absolute difference is minimum.
 *
 * e.g.
 * word1_locations = [2,4,5,9]
 * word2_locations = [4,10,11]
 *
 * i, j = 0, 0
 * min_diff = 2 (abs(2 - 4))
 * word1[i] < word2[j] i.e. 2 < 4
 *   move i one step forward
 *
 * i, j = 1, 0 (abs(4 - 4))
 * min_diff = 0 (We hit the jackpot!)
 *
 * TC:
 * 建立invert indices：o（n)
 * 找到最终结果：o(m) + o(l) -> max o(n)      m,l分别是最后遍历的两个list的航都
 * 最终o(n)
 *
 * SC:
 * o（n)
 */
public class ShortestWordDistanceII {
    Map<String, List<Integer>> map;  //<string, list<idx>>
    public void WordDistance(String[] words) {
        map = new HashMap<>();
        //preComputation for idx
        for (int j = 0; j < words.length; j++) {
            if (!map.containsKey(words[j])) {
                map.put(words[j], new ArrayList<>());
            }
            map.get(words[j]).add(j);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> pos1 = map.get(word1);
        List<Integer> pos2 = map.get(word2);
        int glbShortest = Integer.MAX_VALUE;
        //two pointers谁小移谁
        int i = 0;
        int j = 0;
        while (i < pos1.size() && j < pos2.size()) {
            glbShortest = Math.min(glbShortest, Math.abs(pos1.get(i) - pos2.get(j)));
            if (pos1.get(i) < pos2.get(j)) {
                i++;
            } else {
                j++;
            }
        }
        return glbShortest == Integer.MAX_VALUE ? 0: glbShortest;
    }
}
