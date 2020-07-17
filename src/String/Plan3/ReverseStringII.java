package String.Plan3;

public class ReverseStringII {
    public String reverseStr(String s, int k) {
        //sanity check
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] array = s.toCharArray();
        for (int left = 0; left < array.length; left += 2 * k) {
            int right = Math.min(left + k - 1, array.length - 1);
            reverse(array, left, right);
        }
        return new String(array);
    }
    private void reverse(char[] array, int left, int right) {
        while (left < right) {
            char tmp = array[left];
            array[left++] = array[right];
            array[right--] = tmp;
        }
    }
}

