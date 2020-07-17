package String.Plan4;

public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        //sanity check
        if (strs == null || strs.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs[0].length(); i++) {  //以第一个word的每个字母为底维度遍历,i表示第几个字母
            for (int j = 1; j < strs.length; j++) {   //遍历每个word，j表示第几个word
                //chek if every word idx i is the char 'c'
                char c = strs[0].charAt(i);
                if (i == strs[j].length() || c != strs[j].charAt(i)) {
                    return sb.toString();  //不再符合，把手里有的输出即可
                }
            }
            sb.append(strs[0].charAt(i));  //说明char c也是所有word的preFix
        }
        return sb.toString();
    }
}

