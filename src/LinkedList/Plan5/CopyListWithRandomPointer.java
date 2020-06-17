package LinkedList.Plan5;

import java.util.HashMap;
import java.util.Map;

/**
 * 本质：用iterative方法写graph traverse
 */
public class CopyListWithRandomPointer {
    public Node copyRandomList(Node head) {
        //sanity check
        if (head == null) {
            return head;
        }
        Node newHead = new Node(head.val);
        Node old = head;
        Node cop = newHead; //copied
        //mark visited
        Map<Node, Node> map = new HashMap<>();
        map.put(old, cop);     //mark visited at generation，所以head要单独mark visited
        while (old != null) {
            //copy next
            if (old.next != null) {
                if (!map.containsKey(old.next)) {
                    map.put(old.next, new Node(old.next.val));  //build edge
                }
                cop.next = map.get(old.next);
            }
            //copy next
            if (old.random != null) {
                if (!map.containsKey(old.random)) {
                    map.put(old.random, new Node(old.random.val));
                }
                cop.random = map.get(old.random);
            }
            //update pointer
            old = old.next;
            cop = cop.next;
        }
        return newHead;
    }
}
