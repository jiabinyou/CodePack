package Interval.SegmentTree;
public class A_SegmentTree_基础 {
}
//import java.util.*;
/**SegmentTree：
 * 主要用途：
 *      存放对应区间(线段)的信息（所以会发现很多时候，interval的题目可以使用segment tree来解决）
 * 基本操作:
 *      区间查询(如:区间极值等) • 单点修改(修改某个点的值)
 *      进阶操作:
 *      区间修改(如:更新一段区间的所有值)
 * 特点:
 *      高度为 log(总区间长度)
 *      树上节点个数量级为 区间大小 * 2
 * */

/**segment tree的基本结构：
 * 线段数是一棵二叉树
 * 线段树节点
 *      非叶子节点:表示区间 [i, j]
 *      叶子节点:表示单点 [i, i]
 * 节点维护的值
 *      左、右区间端点 [L, R]
 *      左、右儿子
 *      !!每个节点可附加其他权值(如:区间和、区间极值等)（即所说的"夹带私货"）--》解题关键
 * 左右儿子表示的区间各为根节点的一半长
 *
 * e.g.segment tree最基本结构(每个节点仅为数据范围，无任何附件辅助值)
 *                                      [0, 6]
 *                           /                               \
 *                        [0,3]                            [4,6]
 *                  /               \              /                   \
 *               [0,1]             [2,3]          [4,5]               [6,6]
 *           /         \         /        \     /       \           /      \
 *         [0,0]     [1,1]     [2,2]    [3,3]  [4,4]   [5,5]
 * */

/** 常见问题类型：
 * 和遇到的"TrieTree"问题类似，基本结构大多是满足不了要求的，主要靠改变结构，"夹带私货"来解决问题
 * e.g.每个节点要储存：该segment的数据的sum/average/medium/max/min,用以帮助最快速度解决某一区间的问题
 * */

/**segment tree的基本操作
 * 1.线段树的构造 construct segment tree
 * 2.线段树的单点修改 segment tree single root modify
 * 3.线段树的区间查询 segment tree range search
 * */

/***************************************线段树的构造模板 construct segment tree********************************/
/**
 * e.g.设存在数组[5,2,4,6,1,3,7]，现要构造range sum segment tree
 *                                      [0, 6] 28
 *                              /                                     \
 *                        [0,3] 17                                 [4,6] 11
 *                     /               \                       /                   \
 *               [0,1] 7             [2,3] 10               [4,5] 4              [6,6] 7
 *           /         \              /        \           /       \
 *         [0,0] 5    [1,1] 2     [2,2] 4    [3,3] 6    [4,4] 1   [5,5] 3
 *
 *
 * 具体步骤：
 * recursion(int[] nums, int start, int end) //数组，赋值区间起点，赋值区间终点
 *      声明新的线段树节点 node
 *      更新 node 的管辖区间
 *      左端点 == 右端点(递归出口)
 *          单点赋值:node.val = nums[start]
 *      左端点 < 右端点
 *          求区间中点 mid
 *          递归 [start, mid] 和 [mid + 1, end] 获得儿子
 *          更新 node 的左右儿子 • 返回 node
 *      返回 node
 */
class SegmentTreeNode {
    public int start;
    public int end;
    public SegmentTreeNode left;
    public SegmentTreeNode right;
    public int intervalSum;   //附加值：该range的sum值
    public SegmentTreeNode(int start, int end, int x) {
        this.start = start;
        this.end = end;
        this.intervalSum = x;
        this.left = null;
        this.right = null;
    }
}

class SegmentTree {
    //field
    public SegmentTreeNode root;

    //constructor
    public SegmentTree(int[] nums, int start, int end) {
        root = construct(nums, start, end);
    }

    /**construct segment tree(with range sum)*/
    public SegmentTreeNode construct(int[] nums, int start, int end) {
        //base case(已经超过recursion范围，什么都不需要做，直接返回)
        if (start > end) {
            return null;
        }

        //construct cur Node（此时制作好range左右边界，但附加值range sum还没有计算）
        SegmentTreeNode newNode = new SegmentTreeNode(start, end, 0);

        //case 1: 到了叶子节点，不用计算sum，放该index值即可，然后可提前返回
        if (start == end) {
            newNode.intervalSum = nums[start];
            return newNode;
        }
        //case 2: 非叶子节点，需要recursion，并且range sum为左右子树累加结果
        int mid = start + (end - start) / 2;
        newNode.left = construct(nums, start, mid);
        newNode.right = construct(nums, mid + 1, end);
        if (newNode.left != null) {
            newNode.intervalSum += newNode.left.intervalSum;
        }
        if (newNode.right != null) {
            newNode.intervalSum += newNode.right.intervalSum;
        }
        return newNode;
    }

/***************************************线段树的单点修改模板 segment tree single root modifye********************************/
/**
 * e.g.设存在数组[5,2,4,'6',1,3,7]，构造出range sum segment tree后，将将数组修改为[5,2,4,'2',1,3,7]
 * 现在segment tree上要修改的部分用<>标记出来 --》是从leaf向着root方向修改的过程
 *                                      [0, 6] <24>                                                             step 4
 *                              /                                     \
 *                        [0,3] <13>                                [4,6] 11                                    step 3
 *                     /               \                       /                   \
 *               [0,1] 7             [2,3] <6>               [4,5] 4              [6,6] 7                       step 2
 *           /         \              /        \             /       \
 *         [0,0] 5    [1,1] 2     [2,2] 4    [3,3] <2>      [4,4] 1   [5,5] 3                                   step 1
 *
 * 具体步骤：
 * recursion(SegmentTreeNode root, int index, int value) //当前节点，待修改索引，修改值
 *  分(Divide):
 *      node.start == node.end(递归出口)
 *          叶子节点修改: node.val = value
 *      node.start != node.end
 *          待修改索引在当前节点的左子树
 *              向左子树递归
 *          待修改索引在当前节点的右子树
 *              向右子树递归
 *  治(Conquer):
 *      非叶子节点修改:node.val = node.left.val + node.right.val
 */
    public void modify(SegmentTreeNode root, int index, int value) {
        //base case:在segment tree的叶子节点中找到要修改的index，从这里开始返回，从下往上修改
        if (root.start == index && root.end == index) {
            root.intervalSum = value;
            return ;
        }
        int mid = root.start + (root.end - root.start) / 2;
        if (root.start <= index && index <= mid) { // [start, index, mid, end]说明待修改索引在当前节点的左子树
            modify(root.left, index, value);
        }
        if (mid < index && index <= root.end) { //[start, mid, index, end]说明待修改索引在当前节点的右子树
            modify(root.right, index, value);
        }
        root.intervalSum = root.left.intervalSum + root.right.intervalSum;
    }

/***************************************线段树的区间查询模板 segment tree range search********************************/
/**
 * e.g.设存在数组[5,2,4,6,1,3,7]，构造出range sum segment tree后，查询区间[2,4]
 * 从root往leaf一步一步，从上往下筛选区间，一直筛选到，标记的区间中没有"废区间"为止，比如这一题最终选出的是[2,3][4,4]区间
 * 用《》代表筛选出来的区间
 *                                      《[0, 6] 28》
 *                              /                                             \
 *                        《[0,3] 17 》                                   《 [4,6] 11》
 *                     /               \                             /                   \
 *               [0,1] 7             《[2,3] 10》               《[4,5] 4 》             [6,6] 7
 *           /         \              /        \                 /       \
 *         [0,0] 5    [1,1] 2     [2,2] 4    [3,3] 6       《[4,4] 1》   [5,5] 3
 *
 *
 * 具体步骤：
 * recursion(SegmentTreeNode root, int start, int end) //当前节点，待查询起点，待查询终点
 *      当前节点管辖区间 == 待查询区间
 *          返回 node.val
 *      当前节点管辖区间 != 待查询区间
 *          计算当前节点管辖区间中点
 *          分类讨论并递归计算
 *              待查询区间完全处于 当前节点左子树中, 查询左子树，区间为 [start, end]
 *              待查询区间完全处于 当前节点右子树中,查询右子树，区间为 [start, end]
 *              左右子树均存在查询区间的子区间, 查询左右子树，区间分别为 [start, mid] 和 [mid + 1, end]
 *      返回结果
 */
    public int query(SegmentTreeNode root, int start, int end) {
        //base case:到达叶子节点，说明都找完了
        if (root.start == start && root.end == end) {
            return root.intervalSum;
        }
        int mid = root.start + (root.end - root.start) / 2;
        int result = 0;
        if (start <= mid && mid < end) { //[start, mid, end]->说明待查区间在mid两边，左右子树都要继续recursion
            result += query(root.left, start, mid);
            result += query(root.right, mid + 1, end);
        } else if(start > mid) { //[mid, start]->说明待查区间完全在mid右边，右子树继续recursion即可
            result += query(root.right, start, end);
        } else if(end <= mid) { //[end, mid]->说明待查区间完全在mid左边，左子树继续recursion即可
            result += query(root.left, start, end);
        }
        return result;
    }
}





















