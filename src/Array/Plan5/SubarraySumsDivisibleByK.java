package Array.Plan5;

/**
 Sol1.
 这道题我们要操作的是(Presum[j] - presum[i]) % k， 即使input全部正数，操作对象也不再有sorted性质
 所以使用arr记录PreSumi意义不大
 所以使用BF + MEMO ：
 因为要求 #subarray sums
 preSum DS选用map<preSum % k, fre>
 */

import java.util.HashMap;
import java.util.Map;

/**
 input含有负数， K是正数且 >= 2
 special case: preSum < 0, 得到remainder < 0,需要转化成正数
 */
public class SubarraySumsDivisibleByK {
    public int subarraysDivByK(int[] A, int K) {
        //sanity check
        if (A == null || A.length == 0) {
            return 0;
        }
        //preSum ds
        Map<Integer, Integer> map = new HashMap<>(); //<preSum % k, fre>
        //init
        map.put(0, 1);

        //fix j, iterate
        int count = 0;
        int sum = 0;
        for (int j = 0; j < A.length; j++) {
            sum += A[j];
            int remainder = sum % K < 0 ? sum % K + K : sum % K;
            count += map.getOrDefault(remainder, 0);
            map.put(remainder, map.getOrDefault(remainder, 0) + 1);
        }
        return count;
    }
}


/**
 Sol1b.
 因为remainder范围是固定的[0, k -1],可用int[]来优化map
 O(N) O(1)
 */
class SolSSDK1b {
    public int subarraysDivByK(int[] A, int K) {
        //sanity check
        if (A == null || A.length == 0) {
            return 0;
        }
        //preSum ds
        int[] psr = new int[K]; //preSumRemainder <idx: preSum % k, val: fre>
        //init
        psr[0] = 1;

        //fix j, iterate
        int count = 0;
        int sum = 0;
        for (int j = 0; j < A.length; j++) {
            sum += A[j];
            int remainder = sum % K < 0 ? sum % K + K : sum % K;
            count += psr[remainder];
            psr[remainder]++;
        }
        return count;
    }
}
