package String;

/**
 * String题目难点：
 * 1.与不同类型的转化
 * 很多题目用arr，list作为中间处理的ds都可，关键看最后输出怎样转化，包括
 * String 与 char array：
 *      char[] array = s.toCharArray();
 *      String s = new String(array);        //转换char[]
 *      String s = Arrays.toString(array);   //转换int[]
 *
 * String 与 int：
 *      String s = String.valueOf(num);  //int i
 *      int num = Integer.valueOf(s);
 *      long num = Long.valueOf(s);
 *
 * char 与 int:
 *      char - '0' = int;  //拿到与字面相同的int值，比如char '9' 转化成 int '9'
 *      char - 'a' = int;  //当只存在小写字母，拿到连续的ASCII码，用于bit map，比如将所有input放进int[26]的array中
 *      char - 'A' = int;  //当只存在大写字母，拿到连续的ASCII码，用于bit map
 *
 * String 与 List：
 *
 */

/**
 * 只有arra能用
 *      for (char c : charArray) {}  ,所以输入是string要先转化：
 *      for (char c : s.toCharArray()) {}
 */

/**
 * char和string的常用API:
 *      s = s.trim();
 *      s.substring(begin, end);    //[begin, end)
 *      Character.isDigit(c);        //char c
 *      Character.isLetterOrDigit(C);
 *      Character.isLowerCase(c);
 *      Character.isUpperCase(c);
 *      s.contains(s1);           //注意！！string也有查找功能
 *      boolean s.startsWith(str);  // substring str， 查找str是否是s的prefix
 *      boolean s.startsWith(str, beginIdx);  //查找str是否是s从beginIdx开始的的prefix
 */

/**
 * 同一个ds上的pointers：
 *      使用i, j, k...
 * 多个ds上的pointers：
 *      使用P1, P2, P3...
 */

/**
 * 小写与大写char的转化：
 * 小写转大写：
 *      if (c >= 'a' && c <= 'z') {
 *             c -= 32;
 *      }
 *      《==》
 *      s = s.toUpperCase();
 *      char array[idx] = Character.toUpperCase();
 *      *
 * 大写转小写：
 *      if (c >= 'A' && c <= 'Z') {
 *            c += 32;
 *      }
 *      《==》
 *      s = s.toLowerCase();
 *      char array[idx] = Character.toLowerCase();
 */


/**clone hashmap方法*/
/**
 * clone hashmap:  putAll()
 *      Map<Character, Integer> map = new HashMap<>();
 *      //build map
 *      Map<Character, Integer> curMap = new HashMap();  //为每个word制作新的map
 *      curMap.putAll(map);      //用curMap复制了map
 *
 * clone HashSet: addAll
 *     curSet.addAll(set);
 */
public class A_Str_STEP {
}
