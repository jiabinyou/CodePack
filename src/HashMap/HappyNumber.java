package HashMap;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {
    public boolean isHappy(int n) {
        Set<Integer> loop = new HashSet<>();
        while(loop.add(n)) {   //不同加入19, 82, 68..如果计算过的可以直接得结果，所以用set
            int squareSum = getSquareSum(n);
            if (squareSum == 1) {
                return true;
            }
            n = squareSum;
        }
        return false;
    }

    private int getSquareSum(int n) {
        int sum = 0;
        int digit = 0;
        while (n > 0) {  //将1和9剥离出来，平方相加
            digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;  //返回的是82
    }
}

