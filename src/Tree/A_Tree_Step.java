package Tree;

public class A_Tree_Step {
}
/**Tree的基本概念：
 1.balanced
 对于每一个节点，左右子树高度差不大于1
 2.complete
 除了最后一层，其它层节点必须全满；最后一层，子树必须靠左边
 3.full
 每一个tree中存在的node节点，只要有两个孩子，或者是没有孩子（叶节点）即可（即不存在只有一个孩子的node）
 4.perfect
 全满的tree（从上到倒数第二层，每个node都有左右两个孩子）（最后一层全部是leaf node）
 5.binary search tree	 BST	（最简单稳定的一种tree）
 对于tree里面的每一个node，所有左子树的node都小于当前节点；所有右子树node都大于当前节点

 6.depth : A root node will have a depth of 0.    【backtracking求解】
 7.height : root 的height是最大的                   【pure recursion求解】
      1               depth 0
 /        \
 2          3         level 0, height 0

 8.smallestLarger ---- floor()
 9.largestSmaller ---- ceiling()
 */

/**关于判断Tree Same/Symmetric
 * 方法一：以isSame(TreeNode one, TreeNode two)解法为核心，在外面套上更多层recursion以解决问题
 * 代表例题：
 * SameTree
 * SubTreeOfAnotherTree
 * SymmetricTree
 *
 * 方法二：求解path，并且使用memo(e.g.map)来记录和判断是否有相等path，如果有，就有same subtree
 * 如果直上直下：可用backTracking
 * 如果可以整颗subtree比较，有人字形path：需要使用pure recursion  【--》很特殊的，在pureRecursion中求解path】
 * 代表例题：
 * FindDuplicateSubtree
 */

