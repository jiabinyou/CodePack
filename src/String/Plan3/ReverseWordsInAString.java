package String.Plan3;

public class ReverseWordsInAString {
    public String reverseWords(String s) {
        //sanity check
        if (s == null || s.length() == 0) {
            return new String("");
        }
        //reverse whole string first
        char[] array = s.toCharArray();
        int left = 0;
        int right = array.length - 1;
        reverse(array, left, right);
        //detect word border then reverse each word
        for (int i = 0; i < array.length; i++) {
            if (i == 0 || array[i - 1] == ' ') {
                left = i;
            }
            if (i == array.length - 1 || array[i + 1] == ' ') {
                right = i;
                reverse(array, left, right);
            }
        }
        return cleanSpaces(array);
    }

    private void reverse(char[] array, int left, int right) {
        while (left < right) {
            swap(array, left, right);
            left++;
            right--;
        }
    }

    private void swap(char[] array, int a, int b) {
        char temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    /**难点：用s.trim()是不够的，因为会出现word中间的空格>1个，也需要处理*/
    //clean space at both ends & leave only one space in middle
    private String cleanSpaces(char[] a) {
        int slow = 0;
        int fast = 0;
        int n = a.length;
        while (fast < n) {
            while (fast < n && a[fast] == ' ') fast++;             // skip spaces before word
            while (fast < n && a[fast] != ' ') a[slow++] = a[fast++]; // keep non spaces
            while (fast < n && a[fast] == ' ') fast++;             // skip spaces after word
            if (fast < n) a[slow++] = ' ';                      // add one space after word
        }
        String s = new String(a);
        return s.substring(0, slow);
    }
}
