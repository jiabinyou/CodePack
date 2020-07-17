package String.Plan2;

public class CompareVersionNumbers {
    public int compareVersion(String version1, String version2) {
        int l1 = version1.length();
        int l2 = version2.length();
        int num1 = 0;
        int num2 = 0;
        int i = 0;
        int j = 0;
        while (i < l1 || j < l2) {
            while (i < l1 && Character.isDigit(version1.charAt(i))) {
                num1 = num1 * 10 + version1.charAt(i) - '0';
                i++;
            }
            while (j < l2 && Character.isDigit(version2.charAt(j))) {
                num2 = num2 * 10 + version2.charAt(j) - '0';
                j++;
            }
            if (num1 < num2) {
                return -1;
            } else if (num1 > num2) {
                return 1;
            } else {
                i++;
                j++;
                num1 = 0;
                num2 = 0;
            }
        }
        return 0;
    }
}
