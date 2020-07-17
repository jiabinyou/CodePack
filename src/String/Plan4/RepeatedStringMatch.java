package String.Plan4;

/**
 题目所求：问A最少重复几次，能够让B成为A的substring
 如果A通过重复仍然不成立，返回-1

 技巧：
 count只有可能是两个数字
 1.当重复A，使得A长度正好大于B时候
 2.当重复A，使得A长度正好大于B时候，再重复一次A

 如果这两种情况都不含B，那么就再没有可能了
 */
public class RepeatedStringMatch {
    public int repeatedStringMatch(String A, String B) {
        //sanity check
        if (A == null && B == null || B.length() == 0) {
            return 0;
        }
        if (A == null || B == null || A.length() == 0) {
            return -1;
        }
        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (sb.length() < B.length()) {
            sb.append(A);
            count++;
        }
        //case 1
        if(sb.toString().contains(B)) {
            return count;
        }
        //case 2
        if (sb.append(A).toString().contains(B)) {
            return count + 1;
        }
        return -1;
    }
}


