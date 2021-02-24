package Graph.GraphTraversal.Plan4;

/**
 * Greedy:
 * 使用candidate变量带出所有的celebrity，（candidate初始设为第一个0）
 */
public class FindTheCelebrity {
    public int findCelebrity(int n) {
        int candidate = 0; /**题目默认只有唯一一个candidate，所以计算一次即可*/
        for (int i = 1; i < n; i++) {
            if (knows(candidate, i)) {  //candidate knows i, i is a candidate
                candidate = i;
            }
        }
        //ensure i is the real candidate
        for (int i = 0; i < n; i++) {
            if (i == candidate) {  //skip自己
                continue;
            }
            if (!knows(i, candidate) || knows(candidate, i)) {
                return -1;
            }
        }
        return candidate;
    }

    private boolean knows(int i, int j) {
        return true;
    }
}
