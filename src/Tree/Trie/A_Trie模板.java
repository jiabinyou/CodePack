package Tree.Trie;

import java.util.*;

public class A_Trie模板 {

}

/**TrieTree字典树：又名前缀树，是一种哈希树的变种,其优点在于可以高效查询前缀
 * Trie tree的根本优点：空间换时间
 *
 * 字典树的基本操作
 *      插入单词
 *      前缀计数
 *      查找单词
 *      查找前缀
 * 字典树的题型
 *     1.直接操作字典树 e.g.判断前缀、单词是否存在于字典中   (基本操作)
 *     2.在字典树上dfs     (题目较多，难度偏高)
 *     3.使用字典树加速其他算法，利用其高效查询前缀、单词的优势，优化其他算法
 *
 * TC: O(L) （包括insert, delete, search, update，即增删查改）
 * SC: O(N * L)       -> N 是单词数,L 是单词⻓度
 */

/**TrieTree基本结构 【与常见的树型结构最大的不同：edge表示字母】
 *                                         [*fake root*]
 *                          a /                                   \b
 *                          [ ]                                   [ ]
 *                  a/               \b                   a/                   \c
 *                [ ]                [ ]                 [ ]                   [ ]
 *           b/         \d         /c     \f         c/       \d           e/      \f
 *         [ ]          [ ]     [ ]       [ ]       [ ]       [ ]
 *        c/
 *       []
 * 这里比较容易出现的误区是：
 * e.g. aabc, abc,从上图可以看出来属于两个完全不同的branch，因为我们要保证的是每个word的开头字母在trie tree上都应该是root的位置
 *      不要想当然以为这两个word中的共同substring"abc"属于共用同一个branch。
 *
 * 小细节：
 * trie tree本质是个字典，就是先存储是为了加快查询，这点和map很像。但是在hashcollision小的情况下，建立map要比trietree，查询速度更快。
 * hash collision小的话，map的查询还是要比o(n)小的，然后trie tree是稳定在o(n)。
 * */

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


