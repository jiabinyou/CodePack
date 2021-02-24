package Graph.DFS3.Plan1;

/**
 数出input中，所有字母（index有区分）能组成的不同位数的permutation的数量之和
 依然是index不同，val相同的只是用一次
 本质：permutationII
 关键：
 AABs所有的permutation：AAB, ABA, BAA
 所有答案相当于取这
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 难点：
 * new String(array, beginIdx, length)  //beginIdx: including
 *
 * 此时所要的结果，正好是有效的recursion point，按照层数作为长度！！取的subarray
 *              AAB                                      0  [""]
 *        /          |              \
 *       AAB         AAB(X)         BAA                  1  [A] [B]
 *      /   |        /   \         /   \
 *    AAB  ABA    AAB(X) ABA(x)   BAA  BAA(x)            2  [AA] [AB] [BA]
 *
 *                                                       3  [AAB] [ABA] [BAA]
 *  最后结果，减去""， 就是结果
 */
public class LetterTilePossibilities {
    public int numTilePossibilities(String tiles) {
        if (tiles == null || tiles.length() == 0) {
            return 0;
        }
        char[] array = tiles.toCharArray();
        List<String> result = new ArrayList<>();
        dfs(array, 0, result);
        return result.size() - 1;
    }

    private void dfs(char[] array, int index, List<String> result) {
        result.add(new String(array, 0, index));
        Set<Character> set = new HashSet<>();
        for (int i = index; i < array.length; i++) {
            if (set.add(array[i])) {
                swap(array, i, index);
                dfs(array, index + 1, result);
                swap(array, i, index);
            }
        }
    }
    private void swap(char[] array, int i, int j) {
        char tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}

