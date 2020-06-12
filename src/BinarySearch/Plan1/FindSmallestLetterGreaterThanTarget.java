package BinarySearch.Plan1;

/**
 * clr:
 * 1.       if (letters == null || letters.length == 0)
 * 2.       如果所有字母都比target大怎么办？
 *          这里假设输出的是letters[0] --> 所以才会看到target = 'z'时候，输出'a',
 *          实际上是letters[0]
 */
public class FindSmallestLetterGreaterThanTarget {
    public char nextGreatestLetter(char[] letters, char target) {
        //sanity check
        // if (letters == null || letters.length == 0) {
        //     return '';
        // }
        // if (target == 'z') {
        //     return letters[0];  //sanity check中就能处理
        // }
        //BS
        int left = 0;
        int right = letters.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (letters[mid] <= target) {
                left = mid + 1;
            }  else {
                right = mid;
            }
        }
        if (letters[left] > target) {
            return letters[left];
        }
        return letters[0];
    }
}
