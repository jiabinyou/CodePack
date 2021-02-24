package Graph.DFS3.Plan3;

import java.util.*;

/**
 * 思路：
 * 本质：palindrome --》转化成permutationII题目
 * 制作freqMap，判断odd freq的char有几个，只要大于1个，就可以直接快锁判断无法构成palindrome
 * else：确定能构成palindrome前提下
 * 通过freqMap取出一半的char，即每种char都取出一半的频率：left list
 * 如果有odd freq的char，使用mid单独记录一下落单的char
 * 使用backTracking找到left list所有的permutation（这里input有dup，我们用permutationII方法dedup），最后的res即为：
 *  res = mid == null ? left + reverse left : left + mid + reverse left
 */
public class PalindromePermutationII {
    public List<String> generatePalindromes(String s) {
        List<String> res = new ArrayList<>();
        //sanity check
        if (s == null || s.length() == 0) {
            return res;
        }
        /**build freqMap*/
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        /**check # odd freq char + 取出落单char + 制作left list*/
        List<Character> left = new ArrayList<>();
        int oddNum = 0;
        char mid = ' ';
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            char key = entry.getKey();
            int freq = entry.getValue();
            if (freq % 2 != 0) {
                oddNum++;
                mid = key;
            }
            for (int i = 0; i < freq / 2; i++) {
                left.add(key);
            }
        }
        if (oddNum > 1) { //#odd freq char大于1个，无法构成palindrome
            return res;
        }

        /**对left list进行backTracking，找到left所有permutation，构建最终panlindrome*/
        //预处理：left list转化成char array，并sort
        char[] arr = new char[left.size()];
        for (int i = 0; i < left.size(); i++) {
            arr[i] = left.get(i);
        }
        Arrays.sort(arr);
        backTracking(arr, mid, 0, res);
        return res;
    }

    private void backTracking(char[] arr, char mid, int index, List<String> res) {
        //base case
        if (index == arr.length) {
            addOneRes(arr, mid, res);
            return;
        }
        //recursion
        Set<Character> visited = new HashSet<>();
        for (int i = index; i < arr.length; i++) {
            if (visited.add(arr[i])) {  //!!!每一层的hashset都是新的，不需要recovery
                swap(arr, i, index);
                backTracking(arr, mid, index + 1, res);
                swap(arr, i, index);
            }
        }
    }

    private void addOneRes(char[] arr, char mid, List<String> res) {
        String l = new String(arr);
        String r = new StringBuilder(l).reverse().toString();
        String oneRes = mid == ' ' ? l + r : l + mid + r;
        res.add(oneRes);
    }

    private void swap(char[] array, int a, int b) {
        char temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}

