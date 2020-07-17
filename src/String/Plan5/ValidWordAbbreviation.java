package String.Plan5;

/**
 难点：
 1.0是非法的encode”开头“数字，所以数字encode部分，只要 <1 || >9,都是非法的
 2.中间encode的数字可能是多位，比如13， 15，所以需要累加，但这时候0作为后面位置出现是合法的，
 所以直接判断Character.isDigit(abbr.charAt(p2)
 */
public class ValidWordAbbreviation {
    public boolean validWordAbbreviation(String word, String abbr) {
        //sanity check
        if (word == null || abbr == null || abbr.length() > word.length()) {
            return false;
        }
        int p1 = 0;  //iterate word
        int p2 = 0;  //ieterate abbr
        while (p1 < word.length() && p2 < abbr.length()) {
            if (word.charAt(p1) == abbr.charAt(p2)) {
                p1++;
                p2++;
                /**encode首位 == 0是invalid*/
            } else if (abbr.charAt(p2) < '1' || abbr.charAt(p2) > '9' ) {
                return false;
            } else {
                int skipNum = 0;
                while (p2 < abbr.length() && abbr.charAt(p2) >= '0' && abbr.charAt(p2) <= '9') { //可替换成Character.isDigit(abbr.charAt(p2)
                    skipNum = skipNum * 10 + abbr.charAt(p2) - '0';
                    p2++;
                }
                p1 += skipNum;
            }
        }
        return p1 == word.length() && p2 == abbr.length();
    }
}
