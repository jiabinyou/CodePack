package Tree.Plan8;

import java.util.List;

public class NestedListWeightSum {int sum = 0;
    public int depthSum(List<NestedInteger> nestedList) {
        //sanity check
        if (nestedList == null || nestedList.isEmpty()) {
            return 0;
        }
        depthSum(nestedList, 1);
        return sum;
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
            }
            //recursion
            depthSum(ele.getList(), level + 1);
        }
    }
}

