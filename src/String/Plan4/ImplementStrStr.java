package String.Plan4;

public class ImplementStrStr {
    public int strStr(String haystack, String needle) {
        //sanity check
        if (needle.length() == 0) {
            return 0;
        }
        if (haystack == null || needle == null || haystack.length() == 0) {
            return -1;
        }
        //FS-SW
        int k = needle.length();
        for (int j = 0; j < haystack.length(); j++) {
            //add fast （noting to do）
            //remove slow （noting to do）
            //check res
            if( j >= k - 1) {
                if (haystack.substring(j - k + 1, j + 1).equals(needle)) {
                    return j - k + 1;
                }
            }
        }
        return -1;
    }
}
