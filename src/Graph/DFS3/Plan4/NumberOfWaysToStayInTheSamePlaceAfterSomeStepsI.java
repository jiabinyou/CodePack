package Graph.DFS3.Plan4;

import java.util.HashSet;
import java.util.Set;

/**
 backTracking:找到所有可行的path，输出#path
 但这种接法一旦输入过大，会在DFS中stackoverflow，说明还不够节省空间，需要想优化方法
 */
public class NumberOfWaysToStayInTheSamePlaceAfterSomeStepsI {
    public int numWays(int steps, int arrLen) {
        // sanitycheck
        if (arrLen == 0) {  //只有stay一种方法
            return 1;
        }
        Set<String> dedup = new HashSet<>();  //放所有不重复的path
        backTracking(0, steps, arrLen, " ", dedup);
        return dedup.size();
    }

    private void backTracking(int rIdx, int steps, int arrLen, String path, Set<String> dedup) {
        //base case
        if (rIdx >= arrLen || rIdx < 0) {  //pruning
            return;
        }
        if (steps < 0) { //pruning
            return;
        }
        if (steps == 0 && rIdx == 0) {
            dedup.add(path);
            return;
        }
        //update cur path + recursion
        backTracking(rIdx - 1, steps - 1, arrLen, path + 'l', dedup);
        backTracking(rIdx + 1, steps - 1, arrLen, path + 'r', dedup);
        backTracking(rIdx, steps - 1, arrLen, path + 's', dedup);
    }
}

    /**
     仔细思考：
     1.这道题虽然是DFS3,但是真的需要mark visited吗？
     其实不需要。因为DFS3中的mark visited是帮助我们避开环，即不在同一path中重复遍历某个vertex，
     但这里实际上不所谓。因为完全可以l-》r -》 l，即回到同一个vertex。

     2.需要dedup path吗？
     实际上也不需要，因为第一步开始我们就走的是l，r，s三个branch，无论后面是否重复，
     path的第一个字母一定是不同的，所以path之间不会重复
     所以dedup set也可以省略
     return value：cur subtree所拿到的已经走的

     我们直接用一个count来数最后拿到的path数即可
     */
class NumberOfWaysToStayInTheSamePlaceAfterSomeStepsISol2 {
    public int numWays(int steps, int arrLen) {
        // sanitycheck
        if (arrLen == 0) {  //只有stay一种方法
            return 1;
        }
        int[] count = new int[1];
        backTracking(0, steps, arrLen, count);
        return count[0];
    }

    private void backTracking(int rIdx, int steps, int arrLen, int[] count) {
        //base case
        if (rIdx >= arrLen || rIdx < 0) {  //pruning
            return;
        }
        if (steps < 0) { //pruning
            return;
        }
        if (steps == 0 && rIdx == 0) {
            count[0]++;
            return;
        }
        //update cur path + recursion
        backTracking(rIdx - 1, steps - 1, arrLen, count);
        backTracking(rIdx + 1, steps - 1, arrLen, count);
        backTracking(rIdx, steps - 1, arrLen, count);
    }
}

/**
 * 简化代码：用return value简化backTracking
 * */
class NumberOfWaysToStayInTheSamePlaceAfterSomeStepsISol3 {
    public int numWays(int steps, int arrLen) {
        // sanitycheck
        if (arrLen == 0) {  //只有stay一种方法
            return 1;
        }
        return backTracking(0, steps, arrLen);
    }

    private int backTracking(int rIdx, int steps, int arrLen) {
        //base case
        if (rIdx >= arrLen || rIdx < 0) {  //pruning
            return 0;
        }
        if (steps < 0) { //pruning
            return 0;
        }
        //update cur path + recursion
        if (steps == 0 && rIdx == 0) {
            return 1;
        }
        return backTracking(rIdx - 1, steps - 1, arrLen) +
                backTracking(rIdx + 1, steps - 1, arrLen) +
                backTracking(rIdx, steps - 1, arrLen);
    }
}




