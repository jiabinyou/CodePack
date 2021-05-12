package Graph.DFS3.Plan5;
import java.util.*;


/**题意：找到将债务全清（所有人debt为0）的minimum transaction方法
 e.g.1最终债务
 person    0      1      2
 debt      -5    10     -5
 Two transactions are needed.
 1->0 $5
 1->2 $5

 e.g.2最终债务
 0       1        2
 -4      4        0
 one transactions is needed
 1->0 $4

 e.g.[[0,1,1],[1,2,1],[2,0,1]]
 0       1        2
 0       0        0
 output:0

 e.g.[[2,0,5],[3,4,4]]
 0     1    2     3      4
 5         -5    -4     4
 output:2
 */

/**
 Sol1.
 Map<Integer, Integer> ->  <personID, moneyAmount>
 遍历input，fromID的moneyAmout - amount, toID的moneyAmout + amount

 最终遍历一遍，将所有非0的moneyAmount放进array中，接下来的问题就变成：（graph backTracking）
 因为使用array进行DFS会更加方便，这里有两种方法：
 1.使用list，放置所有非0 moneyAmount，最后转成array
 2.直接使用map长度一致的array，把所有moneyAmount都放进来，所有为0的位置，也正好可以当做是DFS的base case处理了（推荐！）


 给定一个数组，每一次操作可以将任意两数相加，使用dfs try all ways to find 使得数组全变成0的最少操作次数。
 (tip:也可以先提出正负绝对值相等的一对数值，但是我们并不确定有没有dup，所以干脆直接dfs更加方便一些)
 DFS:
 层数：array.length, 每个array idx即为一层
 branch: 遍历index + 1到末尾的每个位置，目的是让array[idx]的money数量在当前dfs path中补成0

 base case：当idx == arr.length - 1,说明走到了最后，array中所有位置都为0，all clear了
 */
public class OptimalAccountBalancing {
    public int minTransfers(int[][] transactions) {
        //sanity check
        if (transactions == null || transactions.length == 0 || transactions[0].length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>(); //<personID, money>
        for (int[] trans : transactions) {
            int fromID = trans[0];
            int toID = trans[1];
            int amount = trans[2];
             map.putIfAbsent(fromID, 0);
             map.putIfAbsent(toID, 0);
             map.put(fromID, map.get(fromID) - amount);
             map.put(toID, map.get(toID) + amount);
//            map.put(fromID, map.getOrDefault(fromID, 0) + amount);
//            map.put(toID, map.getOrDefault(toID, 0) - amount);
        }
        //iterate <personID, money> map
        List<Integer> list = new ArrayList<>();
        for (int e : map.values()) {
            if (e != 0) {
                list.add(e);
            }
        }
        int[] glbMin = new int[]{Integer.MAX_VALUE};
        backTracking(list, 0, 0, glbMin);
        return glbMin[0];
    }

    /**zeroNum: #zero in array, curCount: cur DFS path #transactions*/
    private void backTracking(List<Integer> list, int curCount, int zeroNum, int[] glbMin) {
        //base case
        if (zeroNum == list.size()) {
            glbMin[0] = Math.min(glbMin[0], curCount);
            return;
        }
        if (curCount > glbMin[0]) {  //pruning
            return;
        }
        //recurison
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != 0) {
                int moneyI = list.get(i);  //cur DFS path: 用I位置钱去补上后面位置的(i全部用完)
                list.set(i, 0);

                for (int j = i + 1; j < list.size(); j++) {
                    int moneyJ = list.get(j);
                    if ((moneyI < 0 && moneyJ > 0) || (moneyI > 0 && moneyJ < 0)) {  //只有i位置和j位置的符号相反，才能用i位置补上j
                        int sum = moneyI + moneyJ;  //用i补上j以后，j的money数目
                        list.set(j, sum);
                        backTracking(list, curCount + 1, zeroNum + 1 + (sum == 0 ? 1 : 0), glbMin);  //sum等于0，那么j位置也会变成0
                        list.set(j, moneyJ); //recover
                    }
                }

                list.set(i, moneyI); //recover
                break; /**pruning: 此时loop i s done its job. A new start would begin inside j loop*/
            }
        }
    }

    public static void main(String[] args) {
        OptimalAccountBalancing obj = new OptimalAccountBalancing();
        int[][] transactions = new int[][]{{0, 2, 5}, {3, 1, 3}, {4, 1, 7}};
        System.out.println(obj.minTransfers(transactions));
    }
}

/**过例子：
 *  person    0      1      2     3     4
 *  debt      -5     10     5     -3   -7
 *  list    [-5,10,-5]
 *  idx       0      1     2      3    4
 *
 *    1st Path:
 *    i     monetI   j   moneyJ    sum     list             curCount    zeroNum                glbMin
 *    0       -5    1     10        5    [0,5,5,-3,-7]         1           1
 *    1       5     2     5       ALL POS
 *                  3     -3        2    [0,0,5,2,-7]         2            2
 *    2       5     3     2       ALL POS
 *                  4     -7       -2    [0,0,0,2,-2]         3            3
 *    3       2     4     -2       0     [0,0,0,0,0]          4            5-》finish             4
 *
 *    2nd Path:
 *    i     monetI   j   moneyJ    sum     list             curCount    zeroNum                glbMin
 *    0       -5     2     5        0    [0,10,0,-3,-7]       1           2
 *    1       10     2     0      ALL POS
 *                   3     -3      -7    [0,7,0,0,-7]         2           3
 *    1       7      2     0      ALL POS
 *                   3     0      ALL POS
 *                   4     -7       0    [0,0,0,0,0]          3           5                 Math.min(3, 4)
 * */


/**
 * Sol2:设计return val优化代码
 *
 * Time : O(n!) = n * (n - 1) * (n - 2) * ... *1  《阶乘factorial》
 * Space : O(n) for recursion call stack
 * */
class OptimalAccountBalancingSol2 {
    public int minTransfers(int[][] transactions) {
        //sanity check
        if (transactions == null || transactions.length == 0 || transactions[0].length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>(); //<personID, money>
        for (int[] trans : transactions) {
            int fromID = trans[0];
            int toID = trans[1];
            int amount = trans[2];
            map.putIfAbsent(fromID, 0);
            map.putIfAbsent(toID, 0);
            map.put(fromID, map.get(fromID) - amount);
            map.put(toID, map.get(toID) + amount);
        }
        //build list
        List<Integer> list = new ArrayList<>();
        for (int e : map.values()) {
            if (e != 0) {
                list.add(e);
            }
        }
        return DFS(list, 0);
    }

    /**优化：设计return value
     * for each position i, we recursion all the position from (i + 1) to end, to check how the position i could be settle down,
     * until all position debt have been settle down, we could terminate the recursion.
     return val：the minimum transactions required to settle the debt from cur pos --> end
     */
    private int DFS(List<Integer> list, int idx) {
        //base case
        if (idx == list.size()) {  //all clear, no more debts to settle
            return 0;
        }
        if (list.get(idx) == 0) {  //当前位置无需要settle的，从后一位开始
            return DFS(list, idx + 1);
        }
        int minCount = Integer.MAX_VALUE;
        //recurison
        for (int i = idx + 1; i < list.size(); i++) {
            int remainMoney = list.get(idx);
            int moneyI = list.get(i);  //cur DFS path: 用idx位置钱去补上i位置的
            if ((moneyI < 0 && remainMoney > 0) || (moneyI > 0 && remainMoney < 0)) {  //只有i位置和idx位置的符号相反，才能用idx位置补上i
                int sum = moneyI + remainMoney;  //用idx补上以后，i的money数目
                list.set(i, sum);
                minCount = Math.min(minCount, 1 + DFS(list, idx + 1));  //sum等于0，那么j位置也会变成0
                list.set(i, moneyI); //recover
            }
        }
        return minCount;
    }

    public static void main(String[] args) {
        OptimalAccountBalancingSol2 obj = new OptimalAccountBalancingSol2();
        int[][] transactions = new int[][]{{0, 2, 5}, {3, 1, 3}, {4, 1, 7}};
        System.out.println(obj.minTransfers(transactions));
    }
}
/**过例子：
 *  person    0      1      2     3     4
 *  debt      -5     10     5     -3   -7
 *  list    [-5,10,5,-3,-7]
 *  idx       0      1     2      3    4
 *
 *      1st Path:
 *      idx     remainMoney     i       monetI          sum          		list                      minCount
 *       0         -5           1         10             5       		[-5,5,5,-3,-7]           1+DFS(list, 1)
      Go down:
        1           5           2         5          ALL POS
        3          -3           2      		                            [-5,5,5,2,-7]              2+DFS(list, 2)
        Go down:
        2          5            3         2          ALL POS
                                4          -7           -2     		    [-5,5,5,2,-2]             3+DFS(list, 3)
        Go down:
        3           2           4          -2            0      		 [-5,5,5,2,0]              4+DFS(list, 4) ->4


 *    2nd Path:
     recover:    [-5,10,5,-3,-7]
 *      idx         remainMoney     i       monetI          sum          		list                      minCount
        0 	          -5			2         5               0        	[-5,10,0,-3,-7]         1+DFS(list, 1)
        Go down:
        1             10            2         0          ALL POS
                                    3         -3            7     		     [-5,10,0,7,-7]              2+DFS(list, 2)
        Go down:
        2              0           -> return dfs(list, 3)                                            2+DFS(list, 3)

        Go down:
        3              7            4          -7           0      		[-5,10,0,7,0]               3+DFS(list, 4) ->3
 * */
