package Graph.BFS1.Plan1;
import java.util.*;
/**
 * Solution 1:Graph.BFS1 find SD, DFS recover all path
 *
 * 有前提：all edge weights equal
 * 之所以能使用BFS1去找shortest path，在第一个expand或者generate node的时候就mark visit，
 * 那么从init到达goal所用的一定是最小的level，而level就能够直接反映出path weights。
 * 如果所有path weights相等(即有前提all edge weights equal)，那么最终由level信息就直接能够得到shortest distance。
 * mark visit at expand ： path weight = level （mark at cur level）
 * mark visit at generation ： path weight = level + 1
 *
 * 这里 BFS 是 traverse k level 中所有 vertex + edge。
 * DFS 是从 goal 开始，向 init traverse，每条路径都是之前从 init出发记录下来的，
 * 所以从 goal 出发 record 中所有路径都可以到达 init，而且都是符合条件的最短路径。
 * 这样没有任何多余的traverse，所以就是 size of result
 * Time: BFS: O(b ^ k) + DFS: O(size of result);
 * Space: O(b^k + k) = O(b^k);
 */
public class WordLadderII {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        Set<String> wordSet = new HashSet<>(wordList);
        //sanity check
        if (!wordSet.contains(endWord)) {
            return result;
        }
        /**BFS find all valid shortest path: 难点!!后面要想recover all path，必须记prevNode*/
        Map<String, List<String>> prevMap = new HashMap<>();  //<curNode, List<prevNode>>
        int level = bfs(beginWord, endWord, wordSet, prevMap);
        if (level == 0) {  //pruning
            return result;
        }
        List<String> path = new ArrayList<>();
        dfs(endWord, prevMap, path, result);
        return result;
    }

    private int bfs(String beginWord, String endWord, Set<String> wordSet, Map<String, List<String>> prevMap) {
        Queue<String> queue = new ArrayDeque<>();
        /**<String, min level>, 根据BFS性质，记录第一遇到该node的level，即该node在shortest path上的level*/
        /**BFS性质：使用BFS遍历到的node，第一次时候，一定在其shortest path上*/
        Map<String, Integer> levels = new HashMap<>();
        queue.offer(beginWord);
        prevMap.put(beginWord, new ArrayList<>());
        levels.put(beginWord, 0);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                //expansion
                String cur = queue.poll();
                List<String> neighbors = findNeighbors(cur, wordSet);
                if (neighbors.isEmpty()) {
                    return 0;
                }
                //generation
                for (String nei : neighbors) {
                    if (!levels.containsKey(nei)) {   //nei是第一次碰到，无条件generate
                        List<String> parent = new ArrayList<>();
                        parent.add(cur);
                        prevMap.put(nei, parent);
                        levels.put(nei, level + 1);
                        queue.offer(nei);
                        /**难点：nei不是第一次碰到，且在shortest level,才将cur记为parent。
                         如果不是第一次遇到nei，且nei不在其shortest level，那么cur对于nei没有意义，一定不在shortest path上
                         */
                    } else if (levels.get(nei) == level + 1) {
                        prevMap.get(nei).add(cur);
                    }
                }
            }
            level++;
            if (levels.containsKey(endWord)) {
                break;
            }
        }
        return level;
    }

    private List<String> findNeighbors(String s, Set<String> wordSet) {
        List<String> result = new ArrayList<>();
        char[] array = s.toCharArray();
        for (int i = 0; i < array.length; i++) {
            char tmp = array[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != tmp) {
                    array[i] = c;
                    String cur = new String(array);
                    if (wordSet.contains(cur)) {
                        result.add(cur);
                    }
                    array[i] = tmp;
                }
            }
        }
        return result;
    }

    /**using DFS recover all path from all shortest path find from BFS*/
    private void dfs(String cur, Map<String, List<String>> prevMap, List<String> path, List<List<String>> result) {
        path.add(cur);
        if (prevMap.get(cur).isEmpty()) {
            List<String> tmp = new ArrayList<>(path);
            Collections.reverse(tmp);
            result.add(tmp);
        } else {
            for (String s : prevMap.get(cur)) {
                dfs(s, prevMap, path, result);
            }
        }
        path.remove(path.size() - 1);
    }
}

/**
 * Sol2.Bi-Graph.BFS1  (这个解在LC反而TLE)
 *
 * 思路：
 * 从 init 和 goal 同时走，直到 init 的下一层在 goal 的当前 level 中（或者反过来，goal 的下一层在 init 的当前的 level 中）。
 * 对 BFS 的优化：比较 bfsA.queue.size() 和 bfsB.queue.size()，每次对 size 小的做 bfs。(智能版本，更快)
 * 时间空间上有很大优化：O(2 * b^(k/2)) = O(b^(k/2)); (one direction 是 O(b^k) time & space)
 * 最大的难点在于正向和反向的 map 记录方式不同：
 * bfsA. 正向：cur is key, nei is value
 * bfsB. 反向：nei is key, cur is value
 * DFS 的时候，从 init 开始，先从 bfsA.graph 中找，如果bfsA.graph 中没有cur，再去 bfsB.graph 中找。
 * 从init 开始 在bfsA 中从 root 能得到 child，
 * 如果 bfsA 中没有 cur，若cur在 bfsB 中，则可以从 child 得到 root
 *
 * b is branching factor, k is shortest path length (level of BFS: bfsA + bfsB)
 *
 * Time:
 * BFS: O(b^(k/2));
 * DFS: O(all vertices and edges in bfsA.graph + bfaB.graph);
 * -->O(b^(k/2));
 * Space:
 * O(b^(k/2));
 */
class Solution {
    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) {
            return res;
        }
        BFSImpl bfsA = new BFSImpl(beginWord, true, dict);
        BFSImpl bfsB = new BFSImpl(endWord, false, dict);
        if (!BFS(bfsA, bfsB, dict)) {
            return res;
        }
        DFS(endWord, beginWord, new ArrayList<String>(), res, bfsA, bfsB);
        return res;
    }

    private static boolean BFS(BFSImpl bfsA, BFSImpl bfsB, Set<String> dict) {
        while (bfsA.hasNext() && bfsB.hasNext()) {
            if (bfsA.queue.size() <= bfsB.queue.size()) {
                // return true means we should stop here.
                if (bfsA.next(dict, bfsB)) {
                    return true;
                } else {
                    if (bfsB.next(dict, bfsA)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    static class BFSImpl {
        Set<String> queue;
        Map<String, List<String>> graph;
        // Map<Integer, Integer> levelMap;
        int level;
        boolean dir;

        public BFSImpl(String init, boolean dir, Set<String> dict) {
            this.queue = new HashSet<>();
            this.graph = new HashMap<>();
            this.dir = dir;
            queue.add(init);
            graph.put(init, new ArrayList<String>());
            level = 1;
        }

        public boolean hasNext() {
            return !queue.isEmpty();
        }

        public boolean next(Set<String> dict, BFSImpl reverse) {
            dict.removeAll(queue);
            dict.removeAll(reverse.queue);
            boolean terminate = false;
            Set<String> nextLevel = new HashSet<>();
            for (String cur : queue) {
                for (String nei : getNeighbors(cur, dict, reverse.queue)) {
                    if (reverse.queue.contains(nei)) {
                        terminate = true;
                    }
                    String key = dir ? cur : nei;
                    String value = dir ? nei : cur;
                    List<String> parent = graph.get(key);
                    if (parent == null) {
                        parent = new ArrayList<>();
                        graph.put(key, parent);
                    }
                    parent.add(value);
                    nextLevel.add(nei);
                }
            }
            queue = nextLevel;
            return terminate;
        }

        private List<String> getNeighbors(String cur, Set<String> dict, Set<String> end) {
            Set<String> res = new HashSet<>();
            char[] array = cur.toCharArray();
            for (int i = 0; i < cur.length(); i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    char prev = array[i];
                    array[i] = c;
                    String nei = new String(array);
                    if (dict.contains(nei) || end.contains(nei)) {
                        res.add(nei);
                    }
                    array[i] = prev;
                }
            }
            res.remove(cur);
            return new ArrayList<String>(res);
        }
    }

    private static void DFS(String goal, String cur, List<String> path, List<List<String>> res,
                                BFSImpl bfsA, BFSImpl bfsB) {
        path.add(cur);
        if (cur.equals(goal)) {
            res.add(new ArrayList<String>(path));
        } else if (bfsA.graph.containsKey(cur)) {
            for (String nei : bfsA.graph.get(cur)) {
                DFS(goal, nei, path, res, bfsA, bfsB);
            }
        } else if (bfsB.graph.containsKey(cur)) {
            for (String nei : bfsB.graph.get(cur)) {
                DFS(goal, nei, path, res, bfsA, bfsB);
            }
        }
        path.remove(path.size() - 1);
    }
}

