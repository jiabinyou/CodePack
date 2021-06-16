package StackQueueDeque.MonoStack;

/**
 * 解法：单调栈。
 * 因为海是在数组的右边，数组最后一个元素是没有被挡住的，所以它有oceanview。
 * 照着这个思路，我们从数组的最右边开始往左扫描，如果遇到更高的楼，那么这个更高的楼就是有oceanview的；反之则是没有。
 * --》这个问题显然可以转化成从左向右扫描的monotonic decreasing stack
 * builds a stack in decreasing order for element value and increasing order for index，
 * Remove Last from the stack to build result in increasing index order
 *
 * 时间O(n)
 * 空间O(n)
 * */

import java.util.Deque;
import java.util.LinkedList;

/**
 * decreasing mono stack: 《stack记录的是index！！！》
 *  *      stack保留的：从栈顶到栈底单调递减(是否包含==，由具体题目定)
 *  *      应用场景：能够保证站在cur元素，能找到
 *  *      原input向"左"，第一个比cur数字"小"的元素; 【LS】
 *  *      原input向"右"，第一个比cur数字"大"的元素; 【RL】
 *  *
 *  *      e.g.   input         [4,2,3,1]
 *                idx            0 1 2 3
 *
 *  *             首先压入 0，此时的栈为：[0]
 *  *      a)     若是继续压入 1，满足单调递增栈的特性(因为A[0]=4 < A[1] = 2)， 此时的栈为：[0,1]
 *  *      b)     若是继续压入 2，不满足单调递增栈的特性(因为A[2]=3 > A[1] = 2)， 所以须要调整至重新符合单调减：不断 pop，直到满足单调递增为止。
 *                此时的栈为：[0,2]
 *  *             若是继续压入 3,满足单调递增栈的特性(因为A[3]=1 < A[2] = 3)， 此时的栈为：[0,2, 3]
 * */
public class BuildingsWithAnOceanView {
    public int[] findBuildings(int[] heights) {
        Deque<Integer> stack = new LinkedList<>();
        int n = heights.length;

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] <= heights[i]) stack.pop();
            if (stack.isEmpty() || heights[stack.peek()] > heights[i]) stack.push(i);
        }

        int[] result = new int[stack.size()];

        int i = stack.size() - 1;
        while (i >= 0) {
            result[i] = stack.pop();
            i--;
        }

        return result;
    }
}
