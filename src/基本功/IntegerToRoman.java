package 基本功;

/**
 * 已知：I(1)，V(5)，X(10)，L(50)，C(100)，D(500)，M(1000)
 * 问只给出了1000以及一下的翻译方法，是不是可以推断input是有限范围内的？
 * （个位数ones，十位数tens，百位数hundreds，千位数thousands）
 * 问这几个例子：3， 4， 99
 *
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
 *
 * 3.不可越级相减（个位和百位之间不能相减）
 * 比如99不可以用IC表示，要用XCIX表示
 *
 * 4.累加的字母可以重复，但不能超过三次，当需要超过三次时，用相减表示
 * 比如4不能够是IIII,而应该是IV
 * */


/**如果input大小限制，比如1 <= input <= 3999
 * Sol:greedy
 * 那么roman dict上限在1000就已经够用
 * TC:O(1)  -->因为dict定长
 * SC:O(1)
 *
 * **/
/**思路：根据得出的重要规律：只有 1， 4， 5， 9 开头的数字才是特殊的，小在大左边。 其他的就是简单的累加
 * step 1.将单个罗马字符拓展开来，先将特殊情况如4，40这样不能够用简单累加表示的数字单独表示出来，避免额外的处理
 * 目的：将特殊情况，和general累加的case，全部转化成直接累加case处理
 * I(1) -》 V(5)，X(10) ：     IV(4), IX(9)
 * X(10) -》 L(50)，C(100)：   XL(40), XC(90)
 * C(100）-》D(500)，M(1000) ：CD(400), CM(900)
 * 所以最后全部有：
 * M1000, CM900, D500, CD400, C100, XC90, L50, XL40, X10, IX9, V5, IV4, I1
 *
 * step 2.
 * build table to map number to roman， each round find the largest roman that we can use,
 * 如上面讨论，此时两类case：
 * 1.special "map" case：table中列出来的，如1， 4， 5， 9 开头的数字，需要减法计算
 * 2.general "add" case：如3这样，纯加法计算
 * 都转化成可以通过查表，进行直接累加得到所需要的roman字符
 * then extract this number from remained number, until the remained number equal to 0.
 *
 * e.g.99
 * ROUND1: (remain:99)
 * iterate the table to find the largest roman we can use
 * M1000, CM900, D500, CD400, C100, XC90, L50, XL40, X10, IX9, V5, IV4, I1
 * XC
 * ROUND2:(remain:9)
 * IX
 * */
public class IntegerToRoman {
    class Solution {
        public String intToRoman(int num) {
            //sanity check
            if (num == 0) {
                return "";
            }
            String[] rom = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
            int[] dic = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < dic.length; i++) {
                while (num >= dic[i]) {  //general "add" case + special "map" case都转化成"add"case来进行处理
                    sb.append(rom[i]);
                    num -= dic[i];
                }
            }
            return sb.toString();
        }
    }
}

