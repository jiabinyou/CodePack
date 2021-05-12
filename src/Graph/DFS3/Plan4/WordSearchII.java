package Graph.DFS3.Plan4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Sol.TrieTree
 * 1.将待查找的单词储存在字典树Trie中。每个trie node存储的信息为：boolean isWord, Map<Character, TrieNode> children;
 * 2.使用DFS在board中查找，利用字典树进行剪枝:
 *   如果dfs的下一层所查找的字母在trie中存在，该path就继续往下走继续查找；
 *   如果dfs的下一层所查找的字母在trie中不存在，说明该path无效，直接返回；
 *
 * TC:
 * m x n matrix
 * s - size of the dictionary
 * k - average length of a word
 * 1.make a trie with a cost of O(s x k)
 * 2.dfs:o(4^k),对mn个node进行backTRacking
 * -->最终O(m x n x 4^k)
 *
 * SC:
 * 1.trie 存储all word ： o(s*k)
 * 2.backTracking call stack: K
 * -->最终o(s*k)
 * e.g.
 * words = ["oath","pea","eat","rain"]
 * board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]],
 *
 * trie：
 *
 *           o  /     p/   e /      r/
 *            []     []     []      []
 *         a /     e /     a/     a /
 *         []       []     []      []
 *      t /       a /     t /    i /
 *      []                        []
 *   h /                         n /
 *    []
 *
 *
 * ["o","a","a","n"]
 * ["e","t","a","e"]
 * ["i","h","k","r"]
 * ["i","f","l","v"]
 * */

public class WordSearchII {
    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        //sanity check
        if (board == null || board.length == 0 || board[0].length == 0) {
            return res;
        }
        //build trie using words
        Trie trie = new Trie();
        for (String s : words) {
            trie.insert(s);
        }
        //backTRacking on trie tree
        StringBuilder sb = new StringBuilder();
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                backTracking(trie.root, board, i, j, visited, sb, res);
            }
        }
        return res;
    }

    private void backTracking(TrieNode cur, char[][] board, int i, int j, boolean[][] visited, StringBuilder sb, List<String> res) {
        //base case
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return;
        }
        if (visited[i][j]) {
            return;
        }
        //check res
        char c = board[i][j];
        TrieNode next = cur.children.get(c);
        if (next == null) {
            return;   //words在board中断了
        }
        cur = next; //board[i][j]在trie中存在，继续往下寻找
        sb.append(c);
        if (cur.isWord) {
            res.add(sb.toString());
            cur.isWord = false;    //mark visited, 当前path已经录入res
        }
        //recursion
        visited[i][j] = true;
        for (int[] dir : DIRS) {
            int newR = dir[0] + i;
            int newC = dir[1] + j;
            backTracking(cur, board, newR, newC, visited, sb, res);
        }
        sb.deleteCharAt(sb.length() - 1);
        visited[i][j] = false;
    }

    static class TrieNode {
        boolean isWord;
        Map<Character, TrieNode> children;
        TrieNode() {
            this.children = new HashMap<>();
        }
    }

    static class Trie {
        TrieNode root;
        Trie() {
            this.root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode cur = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                TrieNode next = cur.children.get(c);
                if (next == null) {
                    next = new TrieNode();
                    cur.children.put(c, next);
                }
                cur = next;
            }
            cur.isWord = true;
        }
    }
}

