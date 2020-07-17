package Array.Plan1;

import java.util.Arrays;
/**
 多个arr找4sum，要应用pointers，必须保证每个arr都sorted
 */

/**
 input dup, res dup!!
 */
public class FourSumII {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        //prepare input to apply pointers
        Arrays.sort(A);
        Arrays.sort(B);
        Arrays.sort(C);
        Arrays.sort(D);

        int res = 0;
        //fix l in A
        for (int l = 0; l < A.length; l++) {
            //fix k in B
            for (int k = 0; k < B.length; k++) {
                //find 2 sum in C & D
                int i = 0;
                int j = D.length - 1;
                while (i < C.length && j >= 0) {
                    int sum = A[l] + B[k] + C[i] + D[j];
                    if (sum == 0) {  //res contains dup
                        int leftCount = 1;
                        while (i + 1 < C.length && C[i] == C[i +1]) {
                            leftCount++;
                            i++;
                        }
                        int rightCount = 1;
                        while (j - 1 >= 0 && D[j] == D[j - 1]) {
                            rightCount++;
                            j--;
                        }
                        i++;
                        j--;
                        res += leftCount * rightCount;
                    } else if (sum < 0) {
                        i++;
                    } else {
                        j--;
                    }
                }
            }
        }
        return res;
    }
}
