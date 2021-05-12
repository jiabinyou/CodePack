package 面经.AmazonVO;

import java.util.List;



/*
Please go to the link and read the explanation
http://www.geeksforgeeks.org/design-a-stack-with-find-middle-operation/

How to implement a stack which will support following operations in O(1) time complexity?
1) push() which adds an element to the top of stack.
2) pop() which removes an element from top of stack.
3) findMiddle() which will return middle element of the stack.
4) deleteMiddle() which will delete the middle element.
Push and pop are standard stack operations.
 */

/**
 * 利用double linkedlist来实现可以找到中位数的stack
 * 过例子：【定义偶数时候，middle为中间左边的一个】
 * 1.1 push后奇数
 *   2 <-> 3
 * head,middle
 * -》push：1
     1 <-> 2 <-> 3  (奇数miidle不变)
 *  head  middle
 *
 * 1.1 push后偶数
 *   2 <-> 3 <-> 4
 *   h     m
 * -》push：1
     1 <-> 2 <-> 3 <-> 4 (偶数miidle左移)
 *   h     m
 *
 *
 * */
class DLLNode {
    int val;
    DLLNode left;
    DLLNode right;
    public DLLNode(int val, DLLNode left, DLLNode right) {
        this.val = val;
        this.left = left;
        this.right =right;
    }
}

public class implementStack {
    DLLNode head;
    DLLNode middle;
    int size = 0;
    /**push:将新进来元素连接在top位置，*新元素变成head.如果此时size偶数，middle指针需要左移一位*/
    public void push(int val) {
        size++;
        if(head == null) {
            head = new DLLNode(val, null, null);
            middle = head;
        }else {
            DLLNode node = new DLLNode(val, null, null);
            node.right = head;
            head.left = node;
            head = node;
            List<Integer> list = null;
            if(size%2 == 0) {
                middle = middle.left;
            }
        }
    }

    /**push:将top位置删除。如果新的size为奇数，middle指针左移一位*/
    public int pop() {
        if(head == null) {
            return -1;
        }
        size--;
        int temp;
        temp = head.val;
        if(size == 0) {
            head = null;
            middle = null;
        }else {
            head = head.right;
            head.left = null;
            if(size%2 == 1) {
                middle = middle.right;
            }
        }
        return temp;
    }

    public int findMiddle() {
        return middle.val;
    }


    public void deleteMiddle() {
        size--;
        if(middle.left != null) {
            middle.left.right = middle.right;
        }
        if(middle.right != null) {
            middle.right.left = middle.left;
        }
        if(size%2 == 1) {
            middle = middle.right;
        }else {
            middle = middle.left;
        }
    }
}