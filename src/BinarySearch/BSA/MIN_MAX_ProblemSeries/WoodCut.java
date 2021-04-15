package BinarySearch.BSA.MIN_MAX_ProblemSeries;


/**("贪婪"(这道题其实是BF) + 二分 经典题！！！)*/
/**
 * 题目问“What’s the best strategy to cut wood so that the minimum total sweetness can be maximum?”
 * 这是一个典型的最小值最大化问题(保证："客观上"长度一致的小pieces"""能够长度最大化")。通常这类问题要先考虑用Binary Search
 * */

/** step 1：找固定区间
 left:  假设k -> +无穷大，那么木头需要被切成长度近似0了，能拿到的长度为0
 right: 假设k = 0，那么我可以拿到的就是最长的一块木头
 mid: left + (right - left) / 2;

 step 2: 找mid判断的移动规则
 （important！！！BSA中即找helper function逻辑：
 将mid类比成”实际res“，mid与实际res大：左移区间； mid与实际res小：右移区间）

 首先我们思考这样一个问题: 如果给定N块木头，切出来的每块获得的长度最少是L，所以最多能够切出来的块数是多少?
 这个问题可以用BF。我们就按照L来切每段木头，看能切出来多少块。如此循环下去，直到所有木头都切完止，所能切出来的块数即为最多能切出来的块数。

 现在反过来，因为我们已知：总共要求出来K块，要求：最大的L
 现在可以假设L（即BS中的mid）已知，带入尝试不同的L，看能切分出的块数是否>= K。
 --》BS helper function:
 假设切出来的每块获得的长度最少是L，如果计算出来：
 能够切出来的块数 > k ,说明L小了，右移区间
 能够切出来的块数 < K , 说明L大了，左移区间
 能够切出来的块数 = K , 说明L还有继续增大的空间，右移区间

 最终找到的L就是我们想要的结果
 */

/**
 * TC: O(NlogM)  N -- 木头长度，即len(L[]),   M-- 木头中最长的长度（BS中的right）
 *     因为在区间长度为M的BS操作中，each round花费o(N）计算出numOfChunks
 * */

public class WoodCut {
    public int woodCut(int[] L, int k) {
        // sanity check
        if (L == null || L.length == 0) {
            return 0;
        }
        int left = 1; /**长度至少为1（这里不能是0，因为后面mid是作为除数出现的，/0会出现错误）*/
        int right = 0;
        for (int len : L) {
            right = Math.max(right, len); //假设k = 0，那么我可以拿到的就是最长的一块木头
        }
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (numOfChunk(mid, L) >= k) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (numOfChunk(right, L) >= k) {  //尽量找大的，所以从right开始检查
            return right;
        }
        if (numOfChunk(left, L) >= k) {
            return left;
        }
        return 0; /**这道题有可能切不出来，所以BS有corner case需要输出*/
    }

    //BF
    /**要求每段木头切出来长度至少是minLen，看总共能切多少段*/
    private int numOfChunk(int minLen, int[] L) {
        int count = 0;
        for (int len : L) {
            count += (len / minLen);
        }
        return count;
    }
}

