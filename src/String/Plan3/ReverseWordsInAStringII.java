package String.Plan3;

public class ReverseWordsInAStringII {
    public void reverseWords(char[] s) {
        //sanity check
        if (s == null || s.length == 0) {
            return;
        }
        int i = 0;
        int j = s.length - 1;
        reverse(s, i, j);

        int left = 0;
        int right = 0;
        //find left & right border of one word
        for (; i < s.length; i++) {
            if (i == 0 || s[i - 1] == ' ') {
                left = i;
            }
            if (i == s.length - 1 || s[i + 1] == ' ') {
                right = i;
                reverse(s, left, right);
            }
        }
    }

    private void reverse(char[] s, int i, int j) {
        while (i < j) {
            swap(s, i, j);
            i++;
            j--;
        }
    }

    private void swap(char[] s, int a, int b) {
        char temp = s[a];
        s[a] = s[b];
        s[b] = temp;
    }
}


