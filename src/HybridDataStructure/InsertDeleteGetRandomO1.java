package HybridDataStructure;
import java.util.*;

/**题意：实现一个可以random get val，add， remove的set（所以不能直接使用hashSet）
 * 使用hashmap + arraylist实现
hashmap: 用来做lookup操作  <value, index in ArrayList>
arraylist: 用来做add和delete操作

 分析：需要以下几个功能
 1.add   ->O(1)
 2.delete  ->array进行删除操作比较费时间，需要进行优化
 3.search
   可以使用map<Integer, Integer> ->val, val idx in arrayList存储val在arrayList中对应的位置  -》O(1)

 ArrayList delete的优化：
 我们可以固定删除末尾的元素。
 如果需要删除的元素不在末尾，就将末尾的元素lastVal复制到从map中查找到的要删除元素的idx上，再将末位位置删除即可。
 --》实现了o(1)的删除

*/
public class InsertDeleteGetRandomO1 {
    Map<Integer, Integer> map;
    List<Integer> list;
    Random rand = new Random();
    /** Initialize your data structure here. */
    public InsertDeleteGetRandomO1() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    //在map中不存在时候，才将信息加入map，并加入list的最后一位
    public boolean insert(int val) {
        boolean exist = map.containsKey(val);
        if (exist) {
            return false;
        }
        map.put(val, list.size()); //此时的size就是上一个回合的size，正好是index
        list.add(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    //如果map中存在，才进行处理：
    //如果在list的最后一位，就直接删除；
    //如果不在list的最后一位，在前面，就先把最后一位值复制到val在的位置，再删除array的最后一位
    public boolean remove(int val) {
        boolean exist = map.containsKey(val);
        if (!exist) {
            return false;
        }
        int idx = map.get(val);
        if (idx < list.size()) {
            int lastVal = list.get(list.size() - 1);
            list.set(idx, lastVal);
            map.put(lastVal, idx);  //同时更新lastVal在map中信息，不能忘！！！
        }
        list.remove(list.size() - 1);
        map.remove(val);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}