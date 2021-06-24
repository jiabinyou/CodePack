package BinarySearch.Plan2;

public class SqrtX {
    public int mySqrt(int x) {
        //sanity check
        if (x <= 1) {
            return x;
        }
        long left = 1;
        long right = x;
        while (left < right - 1) {
            long mid = left + (right - left) / 2;
            if (mid * mid == x) {
                return (int)mid;
            } else if (mid * mid < x) { //keep go right
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        if (right * right < x) {
            return (int)right;
        }
        return (int)left;
    }
}

