package String.Plan1;

public class IntegerToEnglishWords {
    private final String[] underTen = new String[]{"","One", "Two", "Three", "Four",
            "Five", "Six", "Seven", "Eight", "Nine"};
    private final String[] underTwenty = new String[]{"Ten", "Eleven", "Twelve", "Thirteen","Fourteen",
            "Fifteen","Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] underHundred = new String[]{"","Ten", "Twenty","Thirty","Forty",
            "Fifty", "Sixty", "Seventy","Eighty","Ninety"};

    public String numberToWords(int num) {
        //sanity check
        if (num == 0) {
            return "Zero";
        }
        return pureRecursion(num);
    }

    private String pureRecursion(int num) {
        String res = new String();
        if (num < 10) res = underTen[num];
        else if (num < 20) res = underTwenty[num - 10];
        else if (num < 100) res = underHundred[num / 10] + " " + underTen[num % 10];
        else if (num < 1000) res = pureRecursion(num / 100) + " Hundred " + pureRecursion(num % 100);
        else if (num < 1000000) res = pureRecursion(num / 1000) + " Thousand " + pureRecursion(num % 1000);
        else if (num < 1000000000) res = pureRecursion(num / 1000000) + " Million " + pureRecursion(num % 1000000);
        else res = pureRecursion(num / 1000000000) + " Billion " + pureRecursion(num % 1000000000);
        return res.trim();
    }
}
