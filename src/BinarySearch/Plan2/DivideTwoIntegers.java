package BinarySearch.Plan2;

public class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        //sanity check
        if (dividend == 0) {
            return 0;
        }
        if (divisor == 0) {
            return Integer.MAX_VALUE;
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        int sign = 1;
        if (dividend > 0 && divisor < 0 || dividend < 0 && divisor > 0) {
            sign = -1;
        }

        long newDividend = Math.abs((long)dividend);
        long newDivisor = Math.abs((long)divisor);
        long left = 0;
        long right = newDividend;
        while (left < right - 1) {
            long mid = left + (right - left) / 2;
            if (mid * newDivisor == newDividend) {
                return (int) (mid * sign);
            } else if (mid * newDivisor < newDividend) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return right * newDivisor <= newDividend ? (int) (right * sign) : (int)(left * sign);
    }
}
