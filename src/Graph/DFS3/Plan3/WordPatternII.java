package Graph.DFS3.Plan3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 两个ds上的backTracking：
 *
 * 与前一题"SplitArrayIntoFibonacciSequence"非常相似，会发现只要有一条path是valid sol，就可以返回true
 *
 * backTracking分解：
 * 层数：input str有多少个char，就有多少层
 * 每层branch：当前index(level)的字母开头，后面有多少可选candidate char，就有多少branch
 *
 * 进入下一层方法：i + 1 ！！因为只能从当前层String curr结尾，再往后一个char开始处理
 * iterate all branch方法: 注意！！[index, s.length() - 1)] 都是valid branch
 *                         而当前recursion point所取得substring是s.substring(index, i + 1)
 *
 * 这一题不需要recover path，所以我们不需要用ds记录dfs path，但是要check 每个pattern的character是否始终对应str中一致的substring
 * 所以需要额外的ds来帮助check
 *
 * 对于每条DFS path，都需要：
 * Map<Character, String> : <pattern char, str substring>
 * Set<String> : str substring  当前path出现过的substring
 * 又因为两个ds都需要遍历，一个index不够，我们在两个ds上都需要index，分别是pIndex，sIndex
 */
public class WordPatternII {
    public boolean wordPatternMatch(String pattern, String str) {
        //sanity check
        if (pattern == null || str == null || pattern.length() > str.length()) {
            return false;
        }
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        return backTracking(0, 0, pattern, str, map, set);
    }

    private boolean backTracking(int pIndex, int sIndex, String pattern, String str, Map<Character, String> map, Set<String> set) {
        //base case
        if (pIndex == pattern.length() && sIndex == str.length()) {
            return true;
        }
        if (pIndex == pattern.length() || sIndex == str.length()) { //pruning
            return false;
        }
        /**recursion, 分c有无映射过两个方向处理*/
        char c = pattern.charAt(pIndex);
        String s = map.get(c);
        if (s != null) {
            if (!str.startsWith(s, sIndex)) { //pruning，如果发现c之前已经被映射成s，但现在str从sIndex开始，s不是preFix，直接返回false
                return false;
            }
            return backTracking(pIndex + 1, sIndex + s.length(), pattern, str, map, set);
        }
        //s != null, recursion to nextLevel
        for (int i = sIndex; i < str.length(); i++) {
            String cur = str.substring(sIndex, i + 1);
            if (!set.contains(cur)) {
                map.put(c, cur);
                set.add(cur);
                if (backTracking(pIndex + 1, sIndex + cur.length(), pattern, str, map, set)) {
                    return true;
                }
                map.remove(c);
                set.remove(cur);
            }
        }
        return false;
    }
}


