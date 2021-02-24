package TreeBST.Plan3;

import LinkedList.ListNode;
import Tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/***
 *
 *难点在于：linkedlist中，无法使用index直接定位到mid元素的位置
 *
 */

/**
 * Sol1.先将linkedlist转成list，再用index计算
 */
public class ConvertSortedListToBinarySearchTree {
    private ListNode curInRoot;
    public TreeNode sortedListToBST(ListNode head) {
        //sanity check
        if (head == null) {
            return null;
        }
        List<Integer> list = getList(head);
        return construct(list, 0, list.size() - 1);
    }

    private TreeNode construct(List<Integer> list, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(list.get(mid));
        root.left = construct(list, left, mid - 1);
        root.right = construct(list, mid + 1, right);
        return root;
    }

    private List<Integer> getList(ListNode head) {
        List<Integer> result = new ArrayList<>();
        while (head != null) {
            result.add(head.val);
            head = head.next;
        }
        return result;
    }
}


/**
 * Sol2. -->  use a global pointer to inorder traverse the linkedlist and
 *  *      construct tree at the same time
 *  （种种方法不太理解）
 */
class SolCSBST2 {
    private ListNode curInRoot;
    public TreeNode sortedListToBST(ListNode head) {
        //sanity check
        if (head == null) {
            return null;
        }
        //find len
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            len++;
        }
        //recursion
        curInRoot = head;
        return construct(0, len - 1);
    }

    private TreeNode construct(int start, int end) {
        //base case
        if (start > end) {
            return null;
        }

        //recursion + induction rule
        int mid = start + (end - start) / 2;

        TreeNode left = construct(start, mid - 1);


        TreeNode root = new TreeNode(curInRoot.val);
        root.left = left; //connect
        curInRoot = curInRoot.next;

        TreeNode right = construct(mid + 1, end);
        root.right = right; //connect

        return root;
    }
}
