package BinarySearch.BSA;

/**(贪婪 + 二分 经典题！！！)*/
/**
 * 题目问“What’s the best strategy to assign books so that the slowest copier can finish at earliest time?”
 * 这是一个典型的最大值最小化问题(保证："抄写最慢的那个""能够最快完成")。通常这类问题要先考虑用Binary Search
 * */

/**     step 1：找固定区间
            left: （最快的）假设每本书都有一个人抄，则抄得最慢的时间点是最厚的那本书的厚度
            right:（最慢的）假设只有一个人抄书，那么最慢完成时间就是所有书厚度之和
            mid: left + (right - left) / 2;

        step 2: 找mid判断的移动规则
            （important！！！BSA中即找helper function逻辑：
            将mid类比成”实际res“，mid与实际res大：左移区间； mid与实际res小：右移区间）

            首先我们思考这样一个问题: 如果给定N本书，每个人限定时间不超过T，所需最少的抄写员是多少?
            这个问题可以用贪婪法。因为题目规定了，**同一本书只能由一个人编辑，并且只是连续编辑多本书。**所以，我们让第一个人从第一本书开始抄，
            一直抄到时间超过T的时候某本书(假设为第x本书)还没抄完，则第1个人就只能抄到第x-1本书，第x本书则由第2个人负责抄，一直到他抄到某本书又超时了为止。
            如此循环下去，直到所有书都抄完为止，所需要人数即为所需最少抄写员。

            现在反过来，因为我们已知：总共只有k个抄写员，要求：最小的T
            现在可以假设T（即BS中的mid）已知，带入尝试不同的T，看所需最少抄写员是不是超过了k。
            --》BS helper function:
            假设每个人按照限定时间T来抄书，如果计算出来：
            需要的抄书员 > k,说明T小了，右移区间
            需要的抄书员 < K, 说明T大了，左移区间
            需要的抄书员 = K, 说明T还有继续增大的空间，左移区间

    最终找到的T就是我们想要的结果
 */

/**
 * TC: O(NlogM)  N -- 书本数目，即len(pages[]),   M--总页数（BS中的right）
 *     因为在区间长度为M的BS操作中，each round花费o(N）计算出numOfWorkers
 * */
public class CopyBooks {
    //BS
    public int copyBooks(int[] pages, int k) {
        // sanity check
        if (pages == null || pages.length == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        for (int page : pages) {
            left = Math.max(left, page);   //左区间：最厚书的厚度
            right += page; //右区间：所有书厚度之和
        }
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (numOfWorker(mid, pages) <= k) {   //need to many people, mid can be setted smaller
                right = mid;
            } else { //need to many people, mid can be setted larger
                left = mid;
            }
        }
        if (numOfWorker(left, pages) <= k) {  //尽量拿更小的时间，所以从left开始检查
            return left;
        }
        return right;
    }

    //Greedy
    /**time:每个人可以抄书的时间限制；
     return：在每个人可以抄书的时间限制为time条件下，所需要的最少的worker数量
     */
    private int numOfWorker(int timeLimit, int[] pages) {
        int count = 0;
        int timePer = 0;  //当前每个人所用时间
        for (int page : pages) {  /**易错点：timePer + page不可直接执行，因为如果timePer + page > k, timePer就要从page大小开始，而不是累加*/
            if (timePer + page > timeLimit) {
                count++;
                timePer = page;  //重置
            } else {
                timePer += page;
            }
        }
        count++; /**易错点：最后一个区间一定是timePer < timeLimit, 这个区间仍需要一个人来完成*/
        return count;
    }
}
