package Graph.DFS3.Plan5;

import java.util.HashSet;
import java.util.Set;

/**虽然我们要遍历的graph是[1,9]这9个数字， 但是并不是按照固定的一维方向遍历，而是有不同的任意方向
 所以需要一维的mark visited ds，来帮助我们避免重复遍历graph node
 */
//mark visited at generation
public class AndroidUnlockPatterns {
    public int numberOfPatterns(int m, int n) {
        /**build tools:帮助判断是否是一条valid path on graph*/
        int[][] passThrough = new int[10][10];    //记录路径中pass的key
        initPassThrough(passThrough);
        /**backTracking on graph: graph是[1,9]这9个数字*/
        int[] glbCount = new int[]{0};
        Set<Integer> visited = new HashSet<>();
        for (int i = 1; i < 10; i++) {   //mark visited init
            visited.add(i);
            glbCount[0] += backTracking(m, n, i, passThrough, visited);
            visited.remove(i);
        }
        return glbCount[0];
    }

    //return value: the valid pattern in cur path
    private int backTracking(int m, int n, int curKey, int[][] passThrough, Set<Integer> visited) {
        //base case
        int count = 0;
        if (visited.size() >= m) {
            count++;
            if (visited.size() == n) {
                return count;
            }
        }

        //all branches + mark visit at generation
        for (int nextKey = 1; nextKey < 10; nextKey++) {
            if (visited.contains(nextKey)) {  //pruning, 已经遍历过nextKey这个node
                continue;
            }
            //pruning，所有跳跃式路径，中间跳过的key，之前没有遍历过，也是invalid branch
            if (passThrough[curKey][nextKey] > 0 && !visited.contains(passThrough[curKey][nextKey])) {
                continue;
            }

            visited.add(nextKey);
            count += backTracking(m, n, nextKey, passThrough, visited);
            visited.remove(nextKey);
        }
        return count;
    }

    //把跳跃式路径中间经过的key找出来
    //passThrough[i][j] > 0 : 表示跳跃式i -> j之间的key是什么
    private void initPassThrough(int[][] passThrough) {
        passThrough[1][3] = passThrough[3][1] = 2;
        passThrough[1][7] = passThrough[7][1] = 4;
        passThrough[3][9] = passThrough[9][3] = 6;
        passThrough[7][9] = passThrough[9][7] = 8;

        passThrough[2][8] = passThrough[8][2] = 5;
        passThrough[4][6] = passThrough[6][4] = 5;

        passThrough[1][9] = passThrough[9][1] = 5;
        passThrough[3][7] = passThrough[7][3] = 5;
    }
}
