package String.Plan1;

public class IntegerToRoman {
    public String intToRoman(int num) {
        String[] str = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] dict = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dict.length; i++) {
            while (num >= dict[i]) { //同一个字母可用多个，所以是while
                num -= dict[i];
                sb.append(str[i]);
            }
        }
        return new String(sb);
    }
}
