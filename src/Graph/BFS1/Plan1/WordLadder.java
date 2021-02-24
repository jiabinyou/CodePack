package Graph.BFS1.Plan1;

import java.util.*;

/**
 * Graph.BFS1 find SD
 *
 * 有前提：all edge weights equal
 * 之所以能使用BFS1去找shortest path，在第一个expand或者generate node的时候就mark visit，
 * 那么从init到达goal所用的一定是最小的level，而level就能够直接反映出path weights。
 * 如果所有path weights相等(即有前提all edge weights equal)，那么最终由level信息就直接能够得到shortest distance。
 * mark visit at expand ： path weight = level （mark at cur level）
 * mark visit at generation ： path weight = level + 1
 *
 * need extra V ds?
 * 需要，因为是traverse vertex，graph node是begin word + 字典中的每个单词，
 * 但是begin word和字典中的单词是不能够重复遍历的，我们需要mark visited
 */
public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        //sanity check
        if (!wordSet.contains(endWord)) {
            return 0;
        }
        return bfs(beginWord, endWord, wordSet);
    }

    private int bfs(String beginWord, String endWord, Set<String> wordSet) {
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(beginWord);  //init root
        visited.add(beginWord); //单独mark visited root
        int level = 1;  //数#node，所以初始值为1
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                /**expansion*/
                String cur = queue.poll();
                List<String> neighbors = getNeighbors(wordSet, cur); /**关键步骤，decouple写更清晰*/
                /**generation*/
                for (String nei : neighbors) {
                    if (nei.equals(endWord)) {
                        return level + 1;
                    }
                    if (visited.add(nei)) { /**mark visited at generation*/
                        queue.offer(nei);
                    }
                }
            }
            level++;
        }
        return 0;
    }

    private List<String> getNeighbors(Set<String> wordSet, String s) {
        List<String> neighbors = new ArrayList<>();
        char[] array = s.toCharArray();
        for (int i = 0; i < array.length; i++) {
            char tmp = array[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != tmp) {
                    array[i] = c;
                    String cur = new String(array);
                    if (wordSet.contains(cur)) {
                        neighbors.add(cur);
                    }
                    array[i] = tmp; //recover array[i]
                }
            }
        }
        return neighbors;
    }
}


