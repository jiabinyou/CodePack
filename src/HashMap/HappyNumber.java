package HashMap;

import java.util.HashSet;
import java.util.Set;

/**tip: 使用set提高效率。不同加入19, 82, 68..如果计算过的可以直接得结果，所以用set*/
public class HappyNumber {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (set.add(n)) {
            int squareSum = getSquareSum(n);
            if (squareSum == 1) {
                return true;
            }
            n = squareSum;
        }
        return false;
    }

    private int getSquareSum(int n) {  //将每一位剥离出来，平方相加
        int sum = 0;
        while (n > 0) {
            sum += (n % 10) * (n % 10);
            n /= 10;
        }
        return sum;
    }
}


