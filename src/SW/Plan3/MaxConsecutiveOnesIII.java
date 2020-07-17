package SW.Plan3;

public class MaxConsecutiveOnesIII {
    public int longestOnes(int[] A, int K) {
        int glbLongest = 0;
        //representative ds
        int zeroNum = 0;

        int slow = 0;
        for (int fast = 0; fast < A.length; fast++) {
            //add fast
            if (A[fast] == 0)  {
                zeroNum++;
            }
            //remove slow
            while (zeroNum > K) {         //SW size是1
                if (A[slow] == 0) {
                    zeroNum--;
                }
                //updatw slow
                slow++;
            }
            //extra: update glb
            glbLongest = Math.max(glbLongest, fast - slow + 1);

        }
        return glbLongest;
    }
}
