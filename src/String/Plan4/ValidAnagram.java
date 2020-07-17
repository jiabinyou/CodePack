package String.Plan4;

public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        //sanity check
        if (s == null && t == null || s.length() == 0 && t.length() == 0) {
            return true;
        } else if (s == null || t == null || s.length() == 0 || t.length() == 0 ||
                s.length() != t.length()) {
            return false;
        }
        int[] map = new int[26];
        //put s in map, delete t in map
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i) - 'a']++;
            map[t.charAt(i) - 'a']--;
        }
        //check res
        for (int i = 0; i < map.length; i++) {
            if (map[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
