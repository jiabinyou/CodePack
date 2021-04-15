package BinarySearch.BSA.MIN_MAX_ProblemSeries;

/**(贪婪 + 二分 经典题！！！)*/
/**
 * 题目问“What’s the best strategy to divide so that the minimum total sweetness can be maximum?”
 * 这是一个典型的最小值最大化问题(保证："客观上甜度最小的那个""能够最大化")。通常这类问题要先考虑用Binary Search
 * */

/** step 1：找固定区间
        left: （最快的）假设k -> +无穷大，那么我能拿到的巧克力长度近似为0， sweetness也是0/ 或者看成自己拿甜度最小的那一块也可以
        right:（最慢的）假设k=0，那么我可以拿到一整块巧克力，可以拿到的sweetness就是所有sweetness之和
        mid: left + (right - left) / 2;

    step 2: 找mid判断的移动规则
        （important！！！BSA中即找helper function逻辑：
        将mid类比成”实际res“，mid与实际res大：左移区间； mid与实际res小：右移区间）

        首先我们思考这样一个问题: 如果给定N块巧克力，每个人限定获得的甜度最少都是S，所能够分给的人数最多是多少?
        这个问题可以用贪婪法。因为题目规定了，**同一个人获得的巧克力甜度是连续的。**所以，我们让第一个人从第一块巧克力开始分，
        每个人分到的甜度至少为S。如此循环下去，直到所有巧克力都分完止，所能分给的人数即为最多能够分给的人数。

        现在反过来，因为我们已知：总共要分给k + 1个人，要求：最大的S
        现在可以假设S（即BS中的mid）已知，带入尝试不同的S，看能分给的最多的人数是不是少于K + 1。
        --》BS helper function:
        假设每个人获得的甜度最少都是S来分巧克力，如果计算出来：
        能分给的人数 > k + 1,说明S小了，右移区间
        能分给的人数 < K + 1, 说明S大了，左移区间
        能分给的人数 = K + 1, 说明S还有继续增大的空间，右移区间

 最终找到的S就是我们想要的结果
 */

/**
 * TC: O(NlogM)  N -- 巧克力长度，即len(sweetness[]),   M--总甜度（BS中的right）
 *     因为在区间长度为M的BS操作中，each round花费o(N）计算出numOfWorkers
 * */
public class DivideChocolate {
    public int maximizeSweetness(int[] sweetness, int K) {
        // sanity check
        if (sweetness == null || sweetness.length == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        for (int sweet : sweetness) {
            //left = Math.min(left, sweet); -->(加上也可)
            right += sweet;
        }
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (numOfPeople(mid, sweetness) >= K + 1) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (numOfPeople(right, sweetness) >= K + 1) {  //尽量找大的，所以从right开始检查
            return right;
        }
        return left;
    }

    //Greedy
    /**minSweetness:每个人至少要分到的甜度；
     return：在每个人至少要分到的甜度限制为minSweetness条件下，最多能分给的人数
     */
    /**难点:这部分和CopyBooks在细节上是不同的
     * 1.巧克力每块是不能分开的，所以需要先计算出sweetPer += sweet，一旦超过甜度限制，就分给下个人，sweetPer直接重置0，而不是继承下一个值
     * 2.最后分给了多少人就是多少人。最后一部分如果甜度不够S，是不能算进有效人数内的（copybook最后一部分还需要多一个count++，因为最后一部分还需要一个人完成）
     * */
    private int numOfPeople(int minSweetness, int[] sweetness) {
        int count = 0;
        int sweetPer = 0;
        for (int sweet : sweetness) {
            sweetPer += sweet;
            if (sweetPer >= minSweetness) {  //一旦完成S了，就再分给下个人
                count++;
                sweetPer = 0;
            }
        }
        return count;
    }
}
