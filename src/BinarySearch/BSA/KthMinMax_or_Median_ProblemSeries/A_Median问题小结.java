package BinarySearch.BSA.KthMinMax_or_Median_ProblemSeries;

/**Median系列问题的最优解法取决于input是静态还是动态*/

/**类型一: input为动态时求median相关问题
    这类问题由于必须要用对于stram类型输入处理高效的算法，所以解法更加固定，反而更好处理）
    Sol：max + min漏斗heap  【TC:O(nlogn)   SC: o(n)】

    e.g. Sliding Window Median ： 求一整个array上，固定大小的SW中的median(SW需要不停移动)
         Find Median from Data ： Stream求data stream的实时meadian值
 */


/**类型二: input为静态时求median相关问题
 * 这类问题  find median 《=》find Kth largest element 《=》find (len - K)th smallest element
 *         median = （len /2)th 或 （（len/2-1) +（len/2+1))/2
 * 而这一类问题的解法就非常多了，时间复杂度也能够优化到更极致。下面来看"Kth smallest/largest系列问题"解法
 * https://docs.google.com/document/d/1gzd5_dcw8hvq0DPDsdPs_nfUK8uCFRrxoLTzW2KC0pM/edit
 *
 * Sol1：sort array，然后根据index取出kth smallest element              【TC:O(nlogn)   SC: o(1)】
 * Sol2：使用"一致"PQ，即求kth smallest用minHeap，求kth largest用maxHeap 【TC:nlogn + klogn, SC:o(n)】
 *       将n个元素全部放入minHeap中，再poll出第k个
 * Sol3：使用"相反"PQ，即求kth smallest用maxHeap，求kth largest用minHeap 【TC:nlogk, SC:o(k)】
 *       找kth smallest《-》找第n-k个largest element，
 *       我们可以维护一个size为k的maxHeap，挨个遍历n个元素。
 *       每次如果遇到的比maxHeap顶部元素小，就把顶部元素poll出来，把新元素放进去，
 *       那么最后能放进manHeap的就是n个元素中前n-k大的。
 *       最后只要用o(1)poll出PQ顶部的元素，就是第k小的
 * Sol4：quick select （结束时候两边子问题不一定sorted）                   【TC:最优o(n),最差o(n^2), SC:o(1)】     ---------》input unsorted最优解法
 *       找kth smallest《-》找第n-k个largest element，对input使用partition，
 *       最终stop at the point where the pivot itself is the kth smallest element
 *
 * Sol5.BSA思想 （前提：只能应用于sorted数据）                             【TC:log(max - min), SC:o(1)】
 *      helper function使用经典BS
 *      找kth smallest《-》找第n-k个largest element，
 *      使用helper function找到比#elements<=mid,最终BS stop when mid is kth smallest                          ----------》input sorted最优解法
 */

/**类型二小结：
 * 这一类型题目大多数一定是unsorted input，求kth problem/median（因为本身如果sorted并且自带index，那么直接就取到了没必要用算法）。所以：
 * 1.unsorted input：
 *   最优解法：quick select （但是TC有浮动）
 *   最稳定的最优解法：使用"相反"PQ （这也是为什么很多人答案喜欢写这种）
 * 2.sorted input，并且无index可用
 *   最优解法：BSA
 * 3.top k一类问题：
 *   最优解法：使用"相反"PQ  （因为quick select只能保证取到第k个元素在自己位置上，但自己的两边仍然undorted）
 * */


public class A_Median问题小结 {
}
