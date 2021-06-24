package 基本功;

/**
 * Time complexity : O(\log n).
 * Space complexity : O(\log n).
 * */
public class PowXN {
    //注意：这里没有说n是正还是负数，如果是负数，计算的是分数
    public double myPow(double x, int n) {
        //if n < 0
        long N = n;            //这一步的作用是把n变成long type，并且如果n<0,变成计算分数
        if (n < 0) {
            x = 1/x;
            N = -N;
        }
        return findPow(x, N);
    }

    //recursive
    private double findPow(double x, long N) {
        //base case
        if (N == 0) {
            return 1.0;
        }

        //recursive rule
        double half = findPow(x, N/2);
        if (N % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }
}
