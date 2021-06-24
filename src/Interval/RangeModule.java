package Interval;


import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 TreeMap<Integer, Integer>, key : the starting index, value : the ending index of the interval.
 Maintain no overlap intervals exist in the Map.

 case 1:  [5. 6] ->input[1, 2]
 case 2:  [1, 3], [4， 6] ->input[2, 5]  ->此时start != null && map.get(start) >= left
 说明有overlap，需要找到3（map.get(start)），4 （map.get(end)），5（right））这三个值比较谁大，merge interval

 case 3：
 */
class RangeModule {
    TreeMap<Integer, Integer> map;
    public RangeModule() {
        map = new TreeMap<>();
    }

    public void addRange(int left, int right) {
        if (right <= left) {
            return;
        }
        Integer start = map.floorKey(left); //the greatest key less than or equal to left
        Integer end = map.floorKey(right);  //the greatest key less than or equal to right
        if (start == null && end == null) {  //case 1：说明此时新加入interval无任何overlap，放入map即可
            map.put(left, right);
        } else if (start != null && map.get(start) >= left) {  //case 2:[ { ] } 有交集,merge interval({}是新加入的)
            map.put(start, Math.max(map.get(end), Math.max(map.get(start), right)));
        } else {    //case 3:[ { ] } 有交集,merge interval({}是新加入的)
            map.put(left, Math.max(map.get(end), right));
        }
        // clean up intermediate intervals
        Map<Integer, Integer> subMap = map.subMap(left, false, right, true);
        Set<Integer> set = new HashSet(subMap.keySet());
        map.keySet().removeAll(set);
    }

    public boolean queryRange(int left, int right) {
        Integer start = map.floorKey(left);
        if (start == null) return false;
        return map.get(start) >= right;
    }

    public void removeRange(int left, int right) {
        if (right <= left) return;
        Integer start = map.floorKey(left);
        Integer end = map.floorKey(right);
        if (end != null && map.get(end) > right) {
            map.put(right, map.get(end));
        }
        if (start != null && map.get(start) > left) {
            map.put(start, left);
        }
        // clean up intermediate intervals
        Map<Integer, Integer> subMap = map.subMap(left, true, right, false);
        Set<Integer> set = new HashSet(subMap.keySet());
        map.keySet().removeAll(set);
    }
}
