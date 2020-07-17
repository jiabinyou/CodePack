package SW.Plan2;

/**
 * Sol1. two pointers谁小移谁
 * 因为指针可以向右走，不回头，所以可以优化到用2 pointers
 */
public class ShortestWordDistance {
    public int shortestDistance(String[] words, String word1, String word2) {
        //sanity check
        if (words == null || words.length == 0) {
            return 0;
        }
        int glbShortest = Integer.MAX_VALUE;
        int pos1 = -1;
        int pos2 = -1;
        for (int j = 0; j < words.length; j++) {
            if (word1.equals(words[j])) {
                if (pos2 != -1) {
                    glbShortest = Math.min(glbShortest, j - pos2);
                }
                pos1 = j;
            }
            if (word2.equals(words[j])) {
                if (pos1 != -1) {
                    glbShortest = Math.min(glbShortest, j - pos1);
                }
                pos2 = j;
            }
        }
        return glbShortest;
    }
}
