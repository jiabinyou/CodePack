package BinarySearch;
public class A_BS_Step {
    /**
     * 1.观察题目是否有sort特性-》没有sorted的要先sort
     * 2.clarify --》确定sanity check做哪些
     * 3.一定有res / 不一定有res ？
     * 4.确定induction rule （包括go left/right边界的的including & excluding）
     * 5.根据induction rule确定while截止条件
     *      自己走size = 3, 2, 1例子来确定
     *      小技巧：
     *      不出现left = mid, right = mid,能用left<= right, left < right, left < right - 1;
     *      不出现left = mid，能用left < right, left < right - 1;
     *      一旦出现left = mid,只能搭配 left < right - 1;
     * 6.post-processing
     *      截止条件left < right，post-process : T[left]  ||  T[right]
     *          -》 (因为跳出循环时候 l,m,r,   剩一个元素未处理)
     *      截止条件left < right - 1，post-process : T[left]  && T[right]
     *          -》(因为跳出循环时候 l,m    r， 剩两个元素未处理)
     *      小技巧：
     *      这里已经跳出while{}，所以T[mid]失效，所以只能处理T[left]，T[right]
     *7.写下output
     *      一定有res: 输出一定是T[left]  ||  T[right]
     *      不一定有res: 如果post-process没找到符合要求结果，输出corner case output / throw exception
     */



    /**不会死循环的通用模板*/
    int binarySearch(int[] nums, int target) {
        // corner case 处理
        if (nums == null || nums.length == 0) {
            return -1;
        }


        int left = 0;
        int right = nums.length - 1;

        // 要点1: start + 1 < end
        while (left  < right - 1) {
            // 要点2：start + (end - start) / 2
            int mid = left + (right - left) / 2;
            // 要点3：=, <, > 分开讨论，mid 不 +1 也不 -1
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }

        // 要点4: 循环结束后，单独处理start和end
        if (nums[left] == target) {
            return left;
        }
        if (nums[right] == target) {
            return right;
        }
        return -1;
    }

}



