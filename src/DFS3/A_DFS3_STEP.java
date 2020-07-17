package DFS3;

/**
 * 设计步骤：
 *
 *
 * 解法二：
 *      多少层，每层物理意义
 *      每层多少branch，每个branch的物理意义
 *      base case：
 *          1.定义停止层：到哪一层以后outOfBound越界了，必须停止
 *          2.check res点/层：哪些地方需要取出res（可能合并在每个recursion节点中）
 *          3.pruning层：到哪一层可以结束/提前结束，再往下recursion没有意义
 *      recursion：
 *          首层begin到end的范围--》推及每一层begin到end的范围
 *          当前层到下一层怎样构建branch
 *      优化：pruning
 *          dedup：
 *          在需要dedup题目中，其实就是对basic recursion tree pruning，画出tree后，看哪些是重复的
 *          根据需要分成
 *          index dedup
 *          val dedup
 *          再看在每层怎样skip/set 等dedup方法，可以达到目的
 */
public class A_DFS3_STEP {
}
