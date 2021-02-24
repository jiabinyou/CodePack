package Tree.Plan8;

import java.util.List;

public class NestedListWeightSum {
    int sum = 0;
    public int depthSum(List<NestedInteger> nestedList) {
        //sanity check
        if (nestedList.size() == 0) {
            return 0;
        }
        recursion(nestedList, 1);  //idx, level
        return sum;
    }

    /**这道题其实非常简单
     * 实际上可以看成是在linkedlist上的backtracking，从上往下有且只有一个branch*/
    private void recursion(List<NestedInteger> nestedList, int level) {
        for (NestedInteger ele : nestedList) {
            //base case (可省略)
       //     if (ele.getList() == null) {  //null说明括号里什么都没有，与包含一个integer是不同的
       //         return;
       //     }
            if (ele.isInteger()) {
                sum += level * ele.getInteger();
            }
            recursion(ele.getList(), level + 1);
        }
    }
}

/**
 * Sol2:将glb变量sum优化到backtraking的return value中
 * */
class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
        //sanity check
        if (nestedList == null || nestedList.isEmpty()) {
            return 0;
        }
        return depthSum(nestedList, 1);
    }

    //return value: sum --> the prefixSum of all the previous subtree
    private int depthSum(List<NestedInteger> nestedList, int level) {
        /**难点：sum定义的位置在for循环之前，而不是我们常写的放在backtracking的update cur path之前
         * 这里使用for循环模拟recurison中每一层，sum在初始为0，经过整个recurison的累加，最终猜得到整个recurison的sum
         * */
        int sum = 0;
        for (NestedInteger ele : nestedList) {
            //base case(可省略)
            //   if (ele.getList() == null) {
            //        return 0;
            //    }

            if (ele.isInteger()) {
                sum += level * ele.getInteger();
            }
            sum += depthSum(ele.getList(), level + 1);
        }
        return sum;
    }
}

