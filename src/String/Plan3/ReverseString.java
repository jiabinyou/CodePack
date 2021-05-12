package String.Plan3;

/**思路：
 "hello world"
 step 1： reverse whole String
 dlrow olleh ->finish

 TC:O(N)
 SC:O(N)
 */

/**过例子：
 * "h  e  l  l  o  w  o  r  l  d"                       res
 *  i                          j            "d  e  l  l  o  w  o  r  l  h"
 *    i                     j               "d  l  l  l  o  w  o  r  e  h"
 *       i               j                  "d  l  r  l  o  w  o  l  e  h"
 *          i         j                     "d  l  r  o  o  w  l  l  e  h"
 *             i   j                        "d  l  r  o  w  o  l  l  e  h"
 *
 *
 * */
public class ReverseString {
    public void reverseString(char[] s) {
        //sanity check
        if (s == null || s.length == 0) {
            return;
        }
        int i = 0;
        int j = s.length - 1;
        while (i < j) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
    }
}
