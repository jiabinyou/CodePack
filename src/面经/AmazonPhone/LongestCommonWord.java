package 面经.AmazonPhone;

import java.util.*;

public class LongestCommonWord {
}

class Solution {
    private List<String> candidate = new ArrayList<>();
    private int longest = 0;

    public String longestCommonPrefix(String[] strs) {
        //sanity check
        if (strs == null || strs.length == 0) {
            return "";
        }
        Trie trie = new Trie();
        for (String s : strs) {
            trie.insert(s);
        }
        //遍历结果，当且仅当结果集只有一个结果，输出结果，否则就是返回空string
        return candidate.size() == 1 ? candidate.get(0) : "";
    }

    class TrieNode {
        Map<Character, TrieNode> sons;
        Map<String, Integer> freqMap;  //每个trie node上存的freqMap

        public TrieNode() {
            this.sons = new HashMap<>();
            this.freqMap = new HashMap<>();
        }
    }

    class Trie {
        private TrieNode root;
        public Trie() {
            root = new TrieNode();
        }

        // 插入单词
        public void insert(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char letter = word.charAt(i);
                if (!node.sons.containsKey(letter)) {
                    node.sons.put(letter, new TrieNode());
                }
                //check freMap是否需要更新
                if (i + 1 == word.length() || (i + 1 < word.length() && word.charAt(i + 1) == ' ')) {
                    String key = word.substring(0, i + 1);
                    node.freqMap.put(key, node.freqMap.getOrDefault(key, 0) + 1);
                    //check res
                    int curFreq = node.freqMap.get(key);
                    if (curFreq >= 2) {
                        if (key.length() > longest) {
                            longest = key.length();
                            candidate.clear();  //有了更长的，之前的就可以全部清除
                            candidate.add(key);
                        } else if (key.length() == longest) {  //如果一样长，就都记下来
                            candidate.add(key);
                        }
                    }
                }
                node = node.sons.get(letter);  //往下走
            }
        }
    }
}




