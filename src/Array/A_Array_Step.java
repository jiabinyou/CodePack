package Array;

public class A_Array_Step {
}

/**
 * Subarray Sum求解相关：（不是都直接上prefixSum就能解决）
 *
 * 第一种.任意subArray Sum
 * 宗旨：通过preSum[]将calculate转化成look up操作。
 * 常用步骤：
 * 1.计算preSum[]
 * 2.判断input情况：
 *      a)如果all positive ，说明preSum[]单调，可在preSum[]上使用two pointer解决问题
 *      b)如果非all positive，需要使用memo解决问题(e.g.HashSet, HashMap记录preSum相关信息)
 *              如果是使用memo，通常可判断一下是否真的需要单独ds先记录preSum[],通常如果不需要回头看preSum[],那么我们只需要
 *              （记录preSum相关信息）方便做查找操作的set/map，而preSum可以边遍历边计算。
 *
 *
 * 2.non-overlapping subarray sum
 * 外层处理：需要用分割思想，需要几段，就将input分割成几段，在这些小段中分别计算prefixSum
 * 代表例题：
 * MaximumSubarrayDifference
 * MaximumSubarrayII
 *
 * */
