package Interval.SegmentTree;

/**题目：
 * query(start, end)： return the sum from index start to index end in the given array.
 * For modify(index, value)： modify the number in the given index to value
 * */
public class IntervalSumII {
    SegmentTree st;
    public IntervalSumII(int[] A) {
        st = new SegmentTree(A, 0, A.length - 1);
    }

    public long query(int start, int end) {
        return st.query(st.root, start, end);
    }

    public void modify(int index, int value) {
        st.modify(st.root, index, value);
    }
}

class SegmentTreeNodeWithSum {
    public int start, end;
    public SegmentTreeNode left, right;
    public int intervalSum;
    public SegmentTreeNodeWithSum(int start, int end, int x) {
        this.start = start;
        this.end = end;
        this.intervalSum = x;
        this.left = null;
        this.right = null;
    }
}

class SegmentTreeWithSum {
    public SegmentTreeNode root;
    public SegmentTreeWithSum(int[] nums, int start, int end) {
        root = construct(nums, start, end);
    }

    public SegmentTreeNode construct(int[] nums, int start, int end) {
        //base case(已经超过recursion范围，什么都不需要做，直接返回)
        if (start > end) {
            return null;
        }

        //constrcut cur Node（此时制作好range左右边界，但附加值range sum还没有计算）
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

    public void modify(SegmentTreeNode root, int index, int value) {
        if (root.start == index && root.end == index) {
            root.intervalSum = value;
            return ;
        }
        int mid = root.start + (root.end - root.start) / 2;
        if (root.start <= index && index <= mid) {
            modify(root.left, index, value);
        }
        if (mid < index && index <= root.end) {
            modify(root.right, index, value);
        }
        root.intervalSum = root.left.intervalSum + root.right.intervalSum;
    }

    public int query(SegmentTreeNode root, int start, int end) {
        if (root.start == start && root.end == end) {
            return root.intervalSum;
        }
        int mid = root.start + (root.end - root.start) / 2;
        int result = 0;
        if (start <= mid && mid < end) {
            result += query(root.left, start, mid);
            result += query(root.right, mid + 1, end);
        }
        else if(start > mid) {
            result += query(root.right, start, end);
        }
        else if(end <= mid) {
            result += query(root.left, start, end);
        }
        return result;
    }
}
