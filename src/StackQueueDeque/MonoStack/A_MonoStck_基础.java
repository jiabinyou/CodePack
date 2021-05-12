package StackQueueDeque.MonoStack;
import java.util.*;

/***************************mono stack --》stack存的是index！！！！！！！！**********************************************************/

/**什么是单调栈mono stack?
 *      单调栈算法是一种借助“栈” 实现的算法 特征为栈内元素按照某种规则(一般为数字大小)单调.
 * 单调栈的最常见用法
 *      对于数组中的某个元素，在数组中找到它左侧(或右侧)离 它最近的一个比它大(或比它小)的数字
 * TC:O(N)
 * SC:O(N)
 *
 * increasing mono stack:
 *      stack保留的：从栈顶到栈底单调递增(是否包含==，由具体题目定)  --》stack存的是index！！！！！！！！
 *      应用场景：能够保证站在cur元素，能找到
 *      原input向"左"，第一个比cur数字"大"的元素;  【LL】
 *      原input向"右"，第一个比cur数字"小"的元素;  【RS】
 *
 *      e.g.   input         [1,3,4,5,2,9,6]
 *             首先压入 1，此时的栈为：[1]
 *             继续压入 3，此时的栈为：[1,3]
 *             继续压入 4，此时的栈为：[1,3,4]
 *             继续压入 5，此时的栈为：[1,3,4,5]
 *      a)     若是继续压入 2，此时的栈为：[1,3,4,5,2] 不满足单调递增栈的特性， 所以须要调整至重新符合单调增：不断 pop，直到满足单调递增为止。此时的栈为：[1]
 *             压入 2，此时的栈为：[1,2]
 *             继续压入9，此时的栈为：[1,2,9]
 *      b)     若是继续压入 6，则不知足单调递增栈的特性， 咱们故技重施，不断 pop，直到知足单调递增为止。此时的栈为：[1,2]
 *             此时的栈为：[1,2,6]
 *      重点理解：cur一旦不符合increasing,说明cur比之前压栈的元素都要小，这就找到了我们需要的应用场景，即：
 *              【1.对于increasing mono stack，一旦cur元素 < Arr[stack.peek()],说明单调增被破坏，此时：对于所有cur round被pop出来的元素，原input中右边第一个比它小的元素，即为cur】
 *              【2.所有最终保留在stack中的元素，代表原input中，它右边没有比它更小的元素】
 *      e.g.这里round a可找到，元素3，4，5右边第一个比自己小的元素均为2，round b可找到，元素9右边第一个比自己小的元素为6。最终stack中元素1，2，6右边均没有比自己小的元素
 *
 *
 *
 * decreasing mono stack:
 *      stack保留的：从栈顶到栈底单调递减(是否包含==，由具体题目定)
 *      应用场景：能够保证站在cur元素，能找到
 *      原input向"左"，第一个比cur数字"小"的元素; 【LS】
 *      原input向"右"，第一个比cur数字"大"的元素; 【RL】
 *
 *      e.g.   input         [6,9,2,5,4,3,1]
 *             首先压入 6，此时的栈为：[6]
 *      a)     若是继续压入 9，不满足单调递增栈的特性， 所以须要调整至重新符合单调减：不断 pop，直到满足单调递增为止。此时的栈为：[]
 *             压入 9，此时的栈为：[9]
 *             继续压入 2，此时的栈为：[9,2]
 *      b)     若是继续压入 5，不满足单调递增栈的特性， 所以须要调整至重新符合单调减：不断 pop，直到满足单调递增为止。此时的栈为：[9]
 *             压入 5，此时的栈为：[9,5]
 *             继续压入4，此时的栈为：[9,5,4]
 *             继续压入3，此时的栈为：[9,5,4,3]
 *             继续压入1，此时的栈为：[9,5,4,3,1]
 *      重点理解：cur一旦不符合decreasing,说明cur比之前压栈的元素都要大，这就找到了我们需要的应用场景，即：
 *              【1.对于decreasing mono stack，一旦cur元素 > Arr[stack.peek()],说明单调减被破坏，此时：对于所有cur round被pop出来的元素，原input中右边第一个比它大的元素，即为cur】
 *              【2.所有最终保留在stack中的元素，代表原input中，它右边没有比它更大的元素】
 *     e.g.这里round a可找到，元素6右边第一个比自己小的元素为9，round b可找到，元素9右边第一个比自己小的元素为6。最终stack中元素[9,5,4,3,1]右边均没有比自己大的元素
 *
 * monostack逻辑伪代码：
 * 注意：stack中装的是index！！！！！
 * //原理：当单调性不满足要求时候，就把值不停pop出来，把新值push进去
 *
 * for (i from 0 to (n-1)) {
 *     while (    stack非空  &&    Arr[stack.peek()]与cur ele比单调性"不"存在    )   {     //区间1
 *         记录此时答案；
 *         stack.pop();
 *     }
 *     stack.push(num[i]);    //区间2
 * }
 *
 * e.g.    2      1      4       6          5
 * cur     *      *      *       *          *
 * stack  [2]
 *               [1]<pop2>
 *                    [1,4]
 *                            [1,4,6]
 *                                        [1,4,5]  <pop5后，最后一轮push5>
 *
 *！！！！深刻理解代码：
 * stack的操作将元素分成了两类：
 * 区间一：此部分元素
 * */


/*********************************mono stack模板*****************************************/

import java.util.ArrayDeque;
import java.util.Stack;

/*********************************1.找右边第一个比自己小的->increasing mono stack*****************************************/
public class A_MonoStck_基础 {
    public int[] decreasingMonoStack(int[] array) {
        int[] res = new int[array.length]; /**res[i]: 右边第一个比自己小的元素大小*/
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < array.length; i++) {  //cur idx: i
            while (!stack.isEmpty() && array[stack.peek()] >= array[i]) {  //此时pop出来的元素(idx为stack.pop())，右边第一个比自己小的都是cur
                res[stack.peek()] = array[i];
                stack.pop();
            }
            stack.push(i);
        }
        return res;
    }
}