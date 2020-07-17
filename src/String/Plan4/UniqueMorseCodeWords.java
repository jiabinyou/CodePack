package String.Plan4;

import java.util.HashSet;
import java.util.Set;

/**问所给words array翻译成morse密码后，有多少种不同的code*/
public class UniqueMorseCodeWords {
    public int uniqueMorseRepresentations(String[] words) {
        //sanity check
        if (words == null || words.length == 0) {
            return 0;
        }
        String[] dict = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        Set<String> set = new HashSet<>();
        for (String str : words) {
            StringBuilder sb = new StringBuilder();
            for (char c : str.toCharArray()) {
                sb.append(dict[c - 'a']);
            }
            set.add(sb.toString());
        }
        return set.size();
    }
}
