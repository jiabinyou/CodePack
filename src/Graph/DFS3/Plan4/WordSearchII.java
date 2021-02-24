package Graph.DFS3.Plan4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

