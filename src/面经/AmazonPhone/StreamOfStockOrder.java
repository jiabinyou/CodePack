package 面经.AmazonPhone;

/**题目：
 *You have access to a stream of stock orders. Each order includes the stock name, volume and timestamp.
 * Write a program, that can be queried at any time, to find the top 5 stocks traded by volume in the last 5 minutes.
 * stockStream(String stockName, int volume, long timestamp)
 * (AAPL, 12, timestamp)
 * (GOOG, 14, timestamp)
 * (AMZN, 15, timestamp)
 * (MSFT, 10, timestamp)
 * */

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.*;

/**
 题目理解：
 在每个timeStamp都不停地有大量的新的trade涌入进来，比如：
 (AAPL, 12, timestamp)
 (GOOG, 14, timestamp)
 (AMZN, 5, timestamp)
 (AAPL, 10, timestamp)
 (AMZN, 15, timestamp)
 (MSFT, 10, timestamp)
 (AMZN, 15, timestamp)。。。。
 我们需要求解的是5min之内，总vol top 5的5支股票。

 思路：取用的date structure为：
 1.List<List<Cell(Integer, Data)>> list ->外层list，每个index代表一种stock，里面放的是该stck在不同time的vol preSum，即Cell(PreSum, timeStamp)
 2.hashMap<String, Integer> -> key:stock name, val:在ArrayList中该stock的index，这样做是为了加快stock的查找速度
 3.minHeap<Cell(PreSum, timeStamp)> ->为了最终找到top 5 stack

 总vol可以使用prefixSum来求解，优化TC
 e.g.比如所有AAPL的trade先放在一起
 (AAPL, 10, 9:00)
 (AAPL, 10, 9:02)
 (AAPL,  5, 9:03)
 (AAPL, 11, 9:05)
 (AAPL, 10, 9:06)
 使用preSum记录成：
 (AAPL, 10, 9:00)
 (AAPL, 20, 9:02)
 (AAPL, 25, 9:03)
 (AAPL, 36, 9:05)
 (AAPL, 46, 9:06)
 我们现在需要取5min之内的，使用binary search找到smallestLargerThan（cur time - 5min）的数据，使用O（1）就可以得到5 min之内的total volumn
 不停地addNewStock，并且放入minHeap<Cell(PreSum, timeStamp)>中，按照preSum排序，最终就可以拿到top 5 的stock

 步骤：
 1.addNewStock
 新的stock trade进来，首先查找map中是否有该key：
 如果没有，map中新建index（使用list.size()），并且在list中也新添加一个位置；
 如果有，就利用map找到该stock在list中的位置，在该位置将新的信息添加进来
 2.topFiveStock
 遍历list中所有stock，使用binarySearch取出所有stock 5min内的total stock vol，【O(NLOGK)】
 放入”新的size = 5“minHeap中进行排序，最终得到的就是5min内total vol 内top 5的stock。【O(NLOG5)】
 TC: N--目前为止的总stock种类，K：一种stock最多的trade数量
 总TC:O(NLOGK)+O(NLOG5) = O(NLOGK)
 SC: list<list> :o(NK) ,map: O(N), minHeap:O(5) -->o(NK)

*/

public class StreamOfStockOrder {
    private static final int TIMEINTERVAL = 300;  //300s = 5min
    List<List<Pair>> list;
    Map<String, Integer> map;
    public StreamOfStockOrder() {
        list = new ArrayList<>();
        map = new HashMap<>();
    }

    public void addNewStock(Stock s) {
        if (!map.containsKey(s.name)) {
            int newIdx = list.size();  //当前list中新stock的位置
            map.put(s.name, newIdx);
            list.add(new ArrayList<>());
            list.get(newIdx).add(new Pair(0, -1));  //init preSmu list
            list.get(newIdx).add(new Pair(s.vol, s.time));
        } else {    //stock已经存在，找到位置并将新trade信息更新进去
            int oldIdx = map.get(s.name);
            List<Pair> stockList = list.get(oldIdx);
            int size = stockList.size();
            int lastPreSum = stockList.get(size - 1).preSum;
            list.get(oldIdx).add(new Pair(lastPreSum + s.vol , s.time));  //cal preSum
        }
    }

    public List<String> topFiveStocks() {
        PriorityQueue<PairTwo> minHeap = new PriorityQueue<>(5, new Comparator<PairTwo>() {
            @Override
            public int compare(PairTwo one, PairTwo two) {
                if (one.totalSum == two.totalSum) {
                    return 0;
                }
                return one.totalSum - two.totalSum;
            }
        });
        //遍历所有stock，使用binarySearch取出所有stock 5min内的total stock vol
        for (String stockName : map.keySet()) {
            List<Pair> stockList = list.get(map.get(stockName));
            int size = stockList.size();
            long nowTime = System.currentTimeMillis()/1000;  //date 转 int
            //bs找到5min内该stock totalSum
            Pair firstPair = binarySearch(stockList,nowTime - TIMEINTERVAL);  //largestSmallerThanOrEqual 5min的时间戳pair(因为计算preSum，往前多找一个)
            int totalVol = stockList.get(size - 1).preSum - firstPair.preSum;
            //top k:放入”新的size = 5“minHeap中进行排序
            minHeap.offer(new PairTwo(stockName, totalVol));
            if (!minHeap.isEmpty() && minHeap.size() > 5) { //先offer再丢出来多的那一个即可。（小细节：如果totalName相同，讨论都保留/随便输出一个/按照字母排序）
                minHeap.poll();
            }
        }
        //build res
        List<String> res = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            PairTwo cur = minHeap.poll();
            res.add(cur.name);
        }
        Collections.reverse(res);
        return res;
    }

    //largestSmallerThan 5min的时间戳pair(因为计算preSum，往前多找一个)
    /**易错：left，right，mid是list的idx，而targetTime是time，我们要把对应位置time取出来和target比较*/
    private Pair binarySearch(List<Pair> stockList, long targetTime) {
        int size = stockList.size();
        int left = 0; //该stock第一个timeStamp
        int right = stockList.size() - 1; //该stock最新的timeStamp
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (stockList.get(mid).time < targetTime) {  //我们要把对应位置time取出来和target比较
                left = mid;
            } else {
                right = mid;
            }
        }
        if(stockList.get(right).time < targetTime) {
            return stockList.get(right);
        }
        return stockList.get(left);
    }

    public static void main(String[] args) {
        StreamOfStockOrder obj = new StreamOfStockOrder();
        obj.addNewStock(new Stock("AAPL", 12, 9000));
        obj.addNewStock(new Stock("AAZN", 10, 9004));
        obj.addNewStock(new Stock("DDFG", 10, 9101));
        obj.addNewStock(new Stock("CCBS", 10, 9204));
        obj.addNewStock(new Stock("DDFG", 10, 9207));
        obj.addNewStock(new Stock("DDFG", 20, 9301));
        obj.addNewStock(new Stock("AAZN", 12, 9302));
        obj.addNewStock(new Stock("CCBS", 10, 9305));
        System.out.println(obj.topFiveStocks());
    }
}

class Pair {
    int preSum;
    long time;
    public Pair(int preSum, long time) {
        this.preSum = preSum;
        this.time = time;
    }
}

class PairTwo {
    String name;
    int totalSum;
    public PairTwo(String name, int totalSum) {
        this.name = name;
        this.totalSum = totalSum;
    }
}

class Stock {
    String name;
    int vol;
    long time;
    public Stock(String name, int vol, long time) {
        this.name = name;
        this.vol = vol;
        this.time = time;
    }
}































