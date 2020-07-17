package Tree.Plan8;

import java.util.List;

public class NestedListWeightSumII {
    int sum = 0;
    int flatSum = 0;
    int maxDepth = 1;
    public int depthSumInverse(List<NestedInteger> nestedList) {
        //sanity check
        if (nestedList == null || nestedList.isEmpty()) {
            return 0;
        }
        depthSum(nestedList, 1);
        return flatSum * (maxDepth + 1) - sum;
    }

    private void depthSum(List<NestedInteger> nestedList, int level) {
        for (NestedInteger ele : nestedList) {
            //base case
            if (ele.getList() == null) {
                return;
            }
            //update res
            if (ele.isInteger()) {
                sum += level * ele.getInteger();
                flatSum += ele.getInteger();
                maxDepth = Math.max(maxDepth, level);
            }
            depthSum(ele.getList(), level + 1);
        }
    }
}