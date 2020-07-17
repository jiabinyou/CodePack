package SW.Plan3;

public class MaxConsecutiveOnes {
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
            while (zeroNum > 0) {         //SW size是1
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
