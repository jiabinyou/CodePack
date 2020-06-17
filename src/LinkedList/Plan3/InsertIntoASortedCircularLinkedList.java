package LinkedList.Plan3;

/**
 sanity：
 head == null --> 需要用insertVal新做Circular List

 三种情况下，可以在cur后面插入insertVal
 1.cur不在转折点，全部升序，找到insert Pos
 （cur.val < cur.next.val） && （cur.val <= insertVal && insertVal <= cur.next.val）
 2.cur在转折点位置，唯一降序点
 （cur.val > cur.next.val） &&  (cur.val <= insertVal || insertVal <= cur.next.val)
 3.遇到dup，只有要插入的元素和cur，cur.next值一致，才能在cur后面插入
 (cur.val == cur.next.val） &&   (cur.next == head)

 其余情况：就是没找到插入点，返回head即可
 */

/**
 * 易错点：
 * sanity check中，head == null时候，需要做CL，而不是一个Node
 *
 * Coding难点：
 * while(true), 因为CL一直循环，所以这里放true即可，但是一旦找到就要用break退出来
 * 最后和其他所有情况一起，再return head;
 */
public class InsertIntoASortedCircularLinkedList {
    public Node insert(Node head, int insertVal) {
        //sanity check
        if (head == null) {  //易错点
            Node newNode = new Node(insertVal);
            newNode.next = newNode;
            return newNode;
        }

        Node cur = head;
        while (true) {
            if (((cur.val < cur.next.val) && (cur.val <= insertVal && insertVal <= cur.next.val))  //case 1
                    || ((cur.val > cur.next.val) && (cur.val <= insertVal || insertVal <= cur.next.val))   //case 2
                    || ((cur.val == cur.next.val) && (cur.next == head))) {  //case 3
                cur.next = new Node(insertVal, cur.next);
                break;  //难点
            }
            cur = cur.next;
        }
        return head;        //包括其他所有情况，都统一返回head
    }
}
