package HybridDataStructure;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * get(key)：根据键获取值。如果该键位于缓存中，返回该键对应的值，否则返回-1。
   put(key, value)：设置缓存键值对。如果该键已存在缓存中，更新其值，如果不存在，直接插入。
                    如果缓存容量达到上限，淘汰最近最少使用的键值对。
   delete(key)：根据键,删除该键值对。


 因为要求：get and put must each run in O(1) average time complexity.
 为了O(1)完成get：可以使用hashmap
 但是hashmap无法在O(1)时间内完成key pair的位置移动，即排序过程-》想到可以键入double linked list
 -》
 哈希表用于以O(1)的算法复杂度获取与插入数据，双向链表使原本无序的哈希表有序排列，最新插入与访问的数据会被移动到双向链表头部head，
 缓存容量满时淘汰数据即淘汰链表尾部tail数据。
 * */
public class LRUCache {
    class Node {
        int key;
        int value;
        Node prev;
        Node next;
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        private void update(int key, int value) {  //helper
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    Node head;
    Node tail;
    Map<Integer, Node> map;
    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = null;
        if (!map.containsKey(key)) {
            return -1;
        }
        node = map.get(key);
        delete(node);
        insert(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = null;
        if (map.containsKey(key)) {
            node = map.get(key);
            node.value = value;
            delete(node);
        } else {
            if (map.size() < capacity) {
                node = new Node(key, value);
            } else {
                node = tail;
                delete(node);
                node.update(key, value);
            }
        }
        insert(node);
    }

    private void delete(Node node) {
        map.remove(node.key);
        if (node.prev != null) {
            node.prev.next = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        }
        if (node == head) {
            head = head.next;
        }
        if (node == tail) {
            tail = tail.prev;
        }
        node.prev = node.next = null;
    }

    //insert at head
    private void insert(Node node) {   //helper
        map.put(node.key, node);
        if (head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
    }
}


