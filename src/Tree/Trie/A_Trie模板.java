package Tree.Trie;

import java.util.*;

public class A_Trie模板 {

}

/**
 * 使用条件：
 * 1.需要查询包含某个前缀的单词/字符串是否存在
 * 2.字符矩阵中找单词的问题
 * <p>
 * TC: O(L) 增删查改
 * SC: O(N * L)       -> N 是单词数,L 是单词⻓度
 */
class TrieNode {
    // 所有的儿子节点
    public Map<Character, TrieNode> sons;
    // 根节点到该节点是否是一个单词
    public boolean isWord;
    // 根节点到该节点的单词是什么
    public String word;

    public TrieNode() {
        sons = new HashMap<Character, TrieNode>();
        isWord = false;
        word = null;
    }
}

class Trie {
    private TrieNode root;      /**每个trie必备的root*/
    public Trie() {             /**constructor*/
        root = new TrieNode();
    }

    public TrieNode getRoot() {
        return root;
    }

    // 插入单词
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            if (!node.sons.containsKey(letter)) {
                node.sons.put(letter, new TrieNode());
            }
            node = node.sons.get(letter);  //往下走
        }
        node.isWord = true;  /**整个word更新在trie上以后，在word最后一个char所在的trieNode上更新此信息*/
        node.word = word;
    }

    // 判断单词 word 是不是在字典树中
    public boolean hasWord(String word) {
        int L = word.length();
        TrieNode node = root;
        for (int i = 0; i < L; i++) {
            char letter = word.charAt(i);
            if (!node.sons.containsKey(letter)) {
                return false;
            }
            node = node.sons.get(letter); //往下走
        }
        return node.isWord;
    }

    // 判断前缀 prefix 是不是在字典树中
    public boolean hasPrefix(String prefix) {
        int L = prefix.length();
        TrieNode node = root;
        for (int i = 0; i < L; i++) {
            char letter = prefix.charAt(i);
            if (!node.sons.containsKey(letter)) {
                return false;
            }
            node = node.sons.get(letter);
        }
        return true;
    }
}


