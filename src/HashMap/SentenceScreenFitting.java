package HashMap;

import java.util.HashMap;
import java.util.Map;

/**
 * word不能断，每个word之间至少有一个空格
 *
 * 观察例3可以发现，当句子在屏幕上重复展现时，会呈现周期性的规律：
 *
 * I-had
 * apple
 * pie-I
 * had--
 * apple
 * pie-I
 * had--
 * apple
 * 上例中apple单词的相对位置从第二行开始循环，因此只需要找到单词相对位置的“循环节”，即可将问题简化。
 *
 * 利用字典map记录循环节的起始位置，具体记录方式为：map[(pc, pw)] = pr, ans
 * 其中pw为单词在句子中出现时的下标，pc为单词出现在屏幕上的列数
 *
 * 以数对(pr, ans)为值，其中pr为单词出现在屏幕上的行数，ans为此时已经出现过的完整句子数
 */
public class SentenceScreenFitting {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        //Create the entire sentence with a space between each words.
        String entireSentence = String.join(" ", sentence) + " "; //
        int sentenceLength = entireSentence.length();


        int total = 0; //到目前为止实际放入的sentances中字母长度，包括word之间的空格数
        for (int i = 0; i < rows; i++) {
            //先给total加上一整列的宽度
            total += cols;
            //检查如果加上一整列，正好停在空格，说明此行可以直接结束，total++以移到下一行
            if (entireSentence.charAt(total % sentenceLength) == ' ')
                total++;
                //此时不是空格，说明卡在word中间，需要一直退回上一个空格
            else {
                while (total > 0 && entireSentence.charAt((total - 1) % sentenceLength) != ' ')
                    total--;
            }
        }
        return total / sentenceLength;
    }
}

/**
 * Sol2.hashmap解法
 * 这个解法没看懂！！！
 */

class Solution {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        Map<Integer, Integer> map = new HashMap<>(); //<index, count>
        int n = sentence.length;
        int totalCount = 0;
        for (int i = 0; i < rows; i++) {
            int index = totalCount % n;
            Integer count = map.get(index);
            if (count != null) {
                totalCount += count;
                continue;
            }
            int remain = cols;
            int curCount = 0;
            while (remain > 0) {
                String word = sentence[totalCount % n];
                if (word.length() <= remain) {
                    remain -= word.length() + 1;
                    curCount++;
                    totalCount++;
                } else {
                    break;
                }
            }
            map.put(index, curCount);
        }
        return totalCount / n;
    }
}

