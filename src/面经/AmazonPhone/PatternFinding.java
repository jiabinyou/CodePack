package 面经.AmazonPhone;
import java.util.*;

/**问s是否可以使某一个substring重复多次得来*/
/**
 这里可能不一定是重复整数次，所以string的后面会有小尾巴
 step 1：
 头部从左往右，尾部从右向左，使用两个pointer去检查是否一致，如果一致，说明右边pointer开始的位置到末尾可能就是小尾巴。
 将这个小尾巴去掉，剩下的就是重复了整数次的string
 e.g.a a c d a a c d a a c
         i           j        (j一直向左走到a的位置，与[0,i]是相同的，那么j后面的可能就是小尾巴，去掉)

 step 2：
 剩下的string就是：
     a a c d a a c d
 针对"已知完整重复了n次pattern"的string，我们的做法是：
 记录下index = 0位置的char出现的所有位置，例如a出现的index有：[0, 1, 4, 5],分别去查找0到后面这些index之间的substring
 是否可以组成pattern。
 具体做法：将取得的substring重复input.length() / substring.length() + 1遍，然后去查原来的input是否是新的input的substring
 */
public class PatternFinding {
    public Character checkPattern(String s) {
        //sanity check
        if (s == null || s.length() <= 1) {
            throw new IllegalArgumentException("input has no pattern");
        }
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            String one = s.substring(0, i + 1);
            String two = s.substring(j, s.length());
            if (!one.equals(two)) {
                i++;
                j--;
            } else {
                break;
            }
        }
        String newS = s.substring(0, j);
        for (int idx = 1; idx < newS.length(); idx++) {  //check all S[0] char index
            char start = newS.charAt(0);
            if (newS.charAt(idx) == newS.charAt(0)) {
                String candidate = newS.substring(0, newS.charAt(idx));
                int num = newS.length() / candidate.length() + 1; //最多可重复次数
                String dupS = "";
                while (num > 0) {
                    dupS += candidate;
                    num--;
                }
                //check if pattern
                if (dupS.indexOf(newS) != 0) {  //说明newS是拼合后string的子string，说明candidate是pattern
                    return candidate.charAt(s.length() - j + 1);
                }
            }
        }
        throw new IllegalArgumentException("input has no pattern");   //都找完还没找到，说明没有pattern
    }
}
