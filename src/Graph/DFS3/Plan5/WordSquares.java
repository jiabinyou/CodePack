package Graph.DFS3.Plan5;

import java.util.ArrayList;
import java.util.List;

/**
 * Sol1.backTracking
 * 这个方法在LC 中TLE
 */
public class WordSquares {
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> res = new ArrayList<>();
        //sanity check
        if (words == null || words.length == 0) {
            return res;
        }
        List<String> path = new ArrayList<>();  //这里放的是一个sol，即一个wordSqure中已经放入的单词
        backTracking(words, path, res);
        return res;
    }

    private void backTracking(String[] words, List<String> path, List<List<String>> res) {
        /**base case, 关键：当前sol path的长度 == words的列数，说明当前path只需拼到input中word长度即可，因为不是words中所有都用上*/
        if (path.size() == words[0].length()) {
            res.add(new ArrayList<>(path));
            return;
        }
        //recurison
        for (String word : words) {  /**每次nei都是所有word，因为也可重复使用同一个word*/
            if (isValid(word, path)) {
                path.add(word);
                backTracking(words, path, res);
                path.remove(path.size() - 1);
            }
        }
    }

    /**最关键：将当前word和已经拿到的path中word比较，看当前word是否能在path上组成wirdSqure*/
    //word即在第r行，word[c] == path[c][r] 即可
    private boolean isValid(String word, List<String> path) {
        int r = path.size();  /**path中已经拿到的单词数量,也是当前word能检查的最大col数*/
        for (int c = 0; c < r; c++) {
            if (word.charAt(c) != path.get(c).charAt(r)) {
                return false;
            }
        }
        return true;
    }
}

/**
 * Sol2.backTracking on Trie
 */
class SolWS {
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> result = new ArrayList<>();
        if (words == null || words.length == 0) {
            return result;
        }
        //build trie using words
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        //bacjTracking on trie
        List<String> partial = new ArrayList<>();
        backTracking(trie, result, partial, words[0].length());
        return result;
    }

    private void backTracking(Trie trie, List<List<String>> result, List<String> partial, int n) {
        if (partial.size() == n) {
            result.add(new ArrayList<>(partial));
            return;
        }
        StringBuilder sb = new StringBuilder();
        int m = partial.size();
        for (String s : partial) {
            sb.append(s.charAt(m));
        }
        String prefix = sb.toString();
        List<String> words = trie.startsWith(prefix);
        for (String word : words) {
            partial.add(word);
            backTracking(trie, result, partial, n);
            partial.remove(partial.size() - 1);
        }
    }

    class Trie {
        TrieNode root;
        public Trie() {
            this.root = new TrieNode();
        }
        void insert(String word) {
            TrieNode cur = root;
            cur.startsWith.add(word);
            for (char c: word.toCharArray()) {
                if (cur.children[c - 'a'] == null) {
                    cur.children[c - 'a'] = new TrieNode();
                }
                cur = cur.children[c - 'a'];
                cur.startsWith.add(word);
            }
        }

        List<String> startsWith(String prefix) {
            List<String> result = new ArrayList<>();
            TrieNode cur = root;
            for (char c : prefix.toCharArray()) {
                if (cur.children[c - 'a'] == null) {
                    return result;
                }
                cur = cur.children[c - 'a'];
            }
            result.addAll(cur.startsWith);
            return result;
        }

        class TrieNode {
            List<String> startsWith;
            TrieNode[] children;
            TrieNode() {
                this.startsWith = new ArrayList<>();
                this.children = new TrieNode[26];
            }
        }
    }
}






