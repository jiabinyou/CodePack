package Graph.DFS3.Plan5;

import java.util.HashSet;
import java.util.Set;

public class TravelPlan {
    /**
     * @param arr: the distance between any two cities
     * @return: the minimum distance Alice needs to walk to complete the travel plan
     */
    /**
     DFS3！！因为要找所有的path！！
     已给出adjacent matrix， 不需要自己建图，且遍历一行/一列即遍历所有node
     需要mark visit(因为不能重复遍历node)，需extra space：用set
     */
    public int travelPlan(int[][] arr) {
        // sanity check
        if (arr == null || arr.length <= 1 || arr[0].length <= 1) {
            return 0;
        }
        int[] glbMin = new int[]{Integer.MAX_VALUE};

        Set<Integer> visited = new HashSet<>();
        visited.add(0);  //单独mark visit root
        dfs(arr, visited, 0, 0, glbMin);
        return glbMin[0];
    }

    /**pathLen: 当前path所用的cost*/
    /**mark at generation更加方便,所以要配合mark visit root（即node 0）*/
    private void dfs(int[][] arr, Set<Integer> visited, int curCost, int lastNode, int[] glbMin) {
        //base case
        if (curCost > glbMin[0]) {  //pruning, 写不写都行
            return;
        }
        if (visited.size() == arr.length) {  //说明所有node已经全部visit一遍
            curCost += arr[lastNode][0];  //最后还要回到0
            glbMin[0] = Math.min(glbMin[0], curCost);
            return;
        }
        //induction(从1到arr.len,这些node都可能是nei，都要走一遍)
        /**易错点：如果单独计算curCost，变量是需要recover的(只有string不需要)*/
        for (int i = 0; i < arr.length; i++) {
            if (visited.contains(i)) { //base case
                continue;
            }
            visited.add(i);  //mark visited
            //curCost += arr[lastNode][i];
            dfs(arr, visited, curCost + arr[lastNode][i], i, glbMin);
            //curCost -= arr[lastNode][i];  //recover
            visited.remove(i); //recover
        }
    }
}
