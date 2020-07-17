package SW.Plan3;

/**
 * Sol1. NFS-SW
 */
public class MaxConsecutiveOnesII {
    public int findMaxConsecutiveOnes(int[] nums) {
        int glbLongest = 0;
        //representative ds
        int zeroNum = 0;

        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            //add fast
            if (nums[fast] == 0)  {
                zeroNum++;
            }
            //remove slow
            while (zeroNum > 1) {         //SW size是1
                if (nums[slow] == 0) {
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

/**
 * sOL2.dp
 */
class SolMCO2 {
    public int findMaxConsecutiveOnes(int[] nums) {
        int flip = 0;
        int unflip = 0;
        int glbLongest = 0;
        for (int n : nums){
            flip = n == 1 ? flip + 1 : unflip + 1;
            unflip = n == 1 ? unflip + 1 : 0;
            glbLongest = Math.max(Math.max(flip, unflip), glbLongest);
        }
        return glbLongest;
    }
}


