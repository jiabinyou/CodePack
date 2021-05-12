package 基本功;

/**题目：
 *Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * e.g.
 * Input: s = "III"
 * Output: 3
 * Input: s = "IX"
 * Output: 9
 * Input: s = "MCMXCIV"
 * Output: 1994
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 * */

/**
 * From given exm, 可以发现下面规律：
 * 1.一个罗马数字重复几次，就表示这个数的几倍
 * Input: num = 3
 * Output: "III"
 *
 * 2.左减右加
 * 大的左边记上一个小的，表示大数字减去小数字
 *Input: num = 4
 * Output: "IV"
 *
 * 大的右边记上一个小的，表示大数字加上小数字
 * Input: num = 58
 * Output: "LVIII"
 * */

import java.util.HashMap;
import java.util.Map;

/**思路：
 * 使用HashMap记录roman与int的对应关系
 * 使用一个pointer一个一个digit去遍历所给的roman string，并且每次查看一下cur右边一位所代表的的数字next
 * 分成两种case处理：
 * 1.cur >= next： 直接累加cur  ，指针向右update一位
 * 2.cur < next ： 直接 - cur， 指针向右update一位
 * 最后一位：直接累加
 *  （因为始终要找next，所以pointer只能走到倒数第二位。可以把末尾假想成数字0，那么最后一位始终>0,直接加上即可）
 *
 * e.g.
 * s    = "M      C     M     X    C     I    V"
 *        1000   100  1000   10   100    1    5
 * p       c      c     c     c    c     c
 * cur    1000   100  1000   10   100    1
 * res    1000   900  1900  1890  1990  1989 1994
 *
 * TC: O(1)  -->because there is limit range for roman string, hence the length of the hashmap is a constant
 * SC:O(1)
 * */


public class RomanToInteger {
    public int romanToInt(String s) {
        //sanity check
        if (s == null || s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int result = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            int cur = map.get(s.charAt(i));
            int next = map.get(s.charAt(i + 1));
            if (cur >= next) {
                result += cur;
            } else {
                result -= cur;
            }
        }
        //post-processing
        result += map.get(s.charAt(s.length() - 1));
        return result;
    }
}
