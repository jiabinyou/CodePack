package String.Plan5;

import java.util.ArrayList;
import java.util.List;

public class EncodeAndDecodeStrings {
    private static final String SPECIAL = "#";
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        if (strs == null || strs.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            sb.append(s.length()).append(SPECIAL).append(s);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return result;
        }
        int i = 0;
        while (i < s.length()) {
            int index = s.indexOf(SPECIAL, i);
            int len = Integer.valueOf(s.substring(i, index));
            result.add(s.substring(index + 1, index + 1 + len));
            i = index + 1 + len;
        }
        return result;
    }
}


// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));
