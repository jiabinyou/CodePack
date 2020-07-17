package String.Plan3;

public class ReverseWordsInAStringIII {
    public String reverseWords(String s) {
        //sanity check
        if (s == null || s.length() == 0) {
            return new String("");
        }
        char[] array = s.toCharArray();
        int left = 0;
        int right = array.length - 1;
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
        return new String(array);
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
}

