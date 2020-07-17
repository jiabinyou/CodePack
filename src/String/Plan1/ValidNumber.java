package String.Plan1;

/**
 * Switch Cases框架
 * switch(expression) {
 *   case x:                 //expression符合下面哪个case，就执行哪个case block
 *     // code block
 *     break;
 *   case y:
 *     // code block
 *     break;
 *   default:           //当expression没有符合的case时候，执行default
 *     // code block
 * }
 */
public class ValidNumber {
    public boolean isNumber(String s) {
        if (s == null || s.trim().length() == 0) {
            return false;
        }
        s = s.trim();
        boolean hasNum = false;
        boolean hasE = false;
        boolean hasDot = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch(c) {
                case '.':
                    if (hasDot || hasE) { //‘.'不能重复，e'只能在小数点后面
                        return false;
                    }
                    hasDot = true;
                    break;
                case 'e':
                    if (hasE || !hasNum) { //‘e'不能重复,'e'必须在数字中间，前面要有数字
                        return false;
                    }
                    hasE = true;
                    hasNum = false;
                    break;
                case '+':
                case '-':
                    if (i > 0 && s.charAt(i - 1) != 'e') { //减号1.i=0， 2.e- 只有这两种方式valid
                        return false;
                    }
                    hasNum = false;
                    break;
                default:
                    if (c < '0' || c > '9') {  //其他字符，不是0到9之间数字，都是非法的
                        return false;
                    }
                    hasNum = true;
            }
        }
        return hasNum;
    }
}

