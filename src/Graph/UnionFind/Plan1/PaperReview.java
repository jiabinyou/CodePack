package Graph.UnionFind.Plan1;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**UnionFind + DP
 * 1.UnionFind，根据所给的字典，求input的两个两个句子里哪些字符是similar的。
 * 2. DP求两个转换过的句子的LCS(Longest Common SubSequense)的长度。
 * res = LCS * 2 / (word1Len + word2Len)
 *
 * 具体做法：
 * step 1. (uf.add)
 *      分别以word1， word2中的各个string作为root，建立独立集合
 * step 2. （uf.union)
 *      遍历所给的字典，将similar的两个string union在一起
 * step 3. （uf.find / fu.isConnected)
 *      使用DP中的Longest Common SubSequense计算word1和word2的LCS,唯一注意的就是不是判断是否word1（i) == word2(j)?
 *      而是判断word1（i)，word2(j）在UF中的连通性，可以使用uf.isConnected或者findRoot是否相同来判断。
 *
 * */
public class PaperReview {
    public float getSimilarity(List<String> words1, List<String> words2, List<List<String>> pairs) {
        // sanity check
        if (words1 == null || words1.size() == 0 || words2 == null || words2.size() == 0) {
            return (float)0; /**转成float type, 或者写0f*/
        }
        //step 1: uf.add
        UnionFind uf = new UnionFind();
        for (String s1 : words1) {
            uf.add(s1);
        }
        for (String s2 : words2) {
            uf.add(s2);
        }
        //step 2: uf.union
        for (List<String> pair : pairs) {
            uf.union(pair.get(0), pair.get(1));
        }
        //step 3: DP find LCS
        int m = words1.size();
        int n = words2.size();
        int[][] dp = new int[m + 1][n + 1];
        //base case
        for (int i = 0; i < m ; i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 0;
        }
        //induction rule
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                String rootA = uf.find(words1.get(i - 1)); /**off by 1, i,j表示长度*/
                String rootB = uf.find(words2.get(j - 1));
                if (rootA.equals(rootB)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(Math.max(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1]);
                }
            }
        }
        return (float)(dp[m][n] * 2.0) / (words1.size() + words2.size());
    }

    /**sizeOfSet, numOfSet用不到，father map应使用<String，String>类型*/
    static class UnionFind {
        private Map<String, String> father;
        public UnionFind() {
            father = new HashMap<String, String>();
        }

        public void add(String x) {
            if (father.containsKey(x)) {  //dedup
                return;
            }
            father.put(x, null);
        }

        public void union(String x, String y) {
            String rootX = find(x);
            String rootY = find(y);
            if (!rootX.equals(rootY)) {
                father.put(rootX, rootY);
            }
        }

        public String find(String x) {
            String root = x;
            while (father.get(root) != null) {
                root = father.get(root);
            }
            while (!x.equals(root)) {
                String originalFather = father.get(x);
                father.put(x, root);
                x = originalFather;
            }
            return root;
        }
    }
}
