package SortingAlgorithm.BucketSort;

/**
 * 题意：
 * input：
 * 第一个二维数组是worker的坐标，第二个二维数组是bikes的坐标
 * 曼哈顿距离：(p​1,p2)=∣p1.x−p​2.x∣+∣p​1.y−p​2.y∣
 *
 * 要求：
 * 按照最短曼哈顿距离，为每一位worker分配bikes。如果有多个pair距离相同，按照优先worker index从小到大，再bikes index从小到大分配
 *
 * output：int[] （分配好的pair信息）
 * idx：bike index
 * val：worker index
 * */

/**
 * 本质：Brute Force -- 计算所有pair的曼哈顿距离，对于每一个init node，找到唯一最近的goal node
 * 将input信息化为graph信息如下：
 * 2D平面上，有m个人(P)，n辆自行车（B），还有空白(O)满足以下条件
 1.m   <   n.
 2，距离用abs(x1-x2)+abs(y1-y2)定义
 3．每个人尽量找离自己最近的自行车，一旦某辆自行车被占，其他人只能找别的自行车。
 题目关键词：多个人，多个自行车，一辆自行车配对只能和一个人
 题目目标：给每个人找到自行车，如果这辆车被占用，找下一辆距离最近的车

 注意：这道题虽然是最基本的shortest path，但并不是用BFS求解，
 因为我们配对方法并不是只在乎距离的最短，而是考虑配对的唯一的，即每个worker都要分配上一个合适的bike

 High level idea：遍历图，找到人的坐标，自行车的坐标，算出所有距离组合，放到一个对
 距离进行比较的minheap里，依次pop，如果自行车和人都没有被visited过，说明这个距离是一个有效的距离
 * */

import java.util.Comparator;
import java.util.PriorityQueue;

/**Sol1: BF方法
 * 1.计算中所有worker，bikes的distance pair
 * 2.重新定义minHeap的排序方法，根据
 *      worker bike distance 从小到大 -》worker idx从小到大 -》bikeIdx从小到大 重新定义pq的comparator
 * 3.将上面的pairs全部BF扔进minHeap中进行排序，同时标记worker，bike的visited情况，
 *   each round从minHeap取出的第一对unvisited worker + unvisited bikes，就是找到了一组答案（如果poll出来是visited过的就直接跳过）
 *
 * 假设M worker， N bikes
 * TC:
 * 找到all pair distance：M*N
 * 将all pair放进minHeap排序：log(M*N)
 * 最终共MNlog(M*N)
 *
 * SC: 需要minHeap存放all pair，空间复杂度O(M*N)
 * */
public class CampusBikes {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        //sanity check
        if (workers == null || workers.length == 0 || bikes == null || bikes.length == 0) {
            return new int[0];
        }
        PriorityQueue<Pair> minHeap = new PriorityQueue<>(11, new Comparator<Pair>() {
            @ Override
            public int compare(Pair p1, Pair p2) {
                if (p1.distance == p2.distance) {
                    if (p1.wIdx == p2.wIdx) {
                        return p1.bIdx - p2.bIdx;
                    }
                    return p1.wIdx - p2.wIdx;
                }
                return p1.distance - p2.distance;
            }
        });
        //BF: put all pair's distance into minHeap
        for (int i = 0; i < workers.length; i++) {
            for (int j = 0; j < bikes.length; j++) {
                int distance = Math.abs(workers[i][0] - bikes[j][0])
                        + Math.abs(workers[i][1] - bikes[j][1]);
                Pair newPair = new Pair(i, j, distance);
                minHeap.offer(newPair);
            }
        }
        //build Res
        int[] res = new int[workers.length];  //idx: worker idx, val: bike idx
        boolean[] wVisited = new boolean[workers.length];
        boolean[] bVisited = new boolean[bikes.length];
        while (!minHeap.isEmpty()) {
            Pair cur = minHeap.poll();
            int wIdx = cur.wIdx;
            int bIdx = cur.bIdx;
            /**易错：mark visit最好别用set，在判断时候直接add图方便，因为只有worker和bike两个一起unvisited，
             才能进行下一步一起mark visit操作，不能落单，所以使用set并不能简化代码*/
            if (!wVisited[wIdx] && !bVisited[bIdx]) {
                wVisited[wIdx] = true;
                bVisited[bIdx] = true;
                res[cur.wIdx] = cur.bIdx;
            } else {
                continue; //跳过当前pair
            }
        }
        return res;
    }
}

class Pair {
    int wIdx;  //worker index
    int bIdx;  //bike index
    int distance;  //distance

    public Pair(int wIdx, int bIdx, int distance) {
        this.wIdx = wIdx;
        this.bIdx = bIdx;
        this.distance = distance;
    }
}


