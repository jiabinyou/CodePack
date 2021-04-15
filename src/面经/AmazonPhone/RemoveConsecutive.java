package 面经.AmazonPhone;

import java.util.ArrayDeque;
import java.util.Stack;
import java.util.*;

/**
 * Using Stack
 * Time Complexity : O(n)
 * Space Complexity : O(n)
 * */
public class RemoveConsecutive {
    private static int[] removeDups(int[] arr){
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(arr[0]);
        for(int index = 1; index < arr.length; index++){
            if (stack.peek() != arr[index]){
                stack.push(arr[index]);
            }else{
                stack.pop();
            }
        }
        int[] res = new int[stack.size()];
        for(int index = stack.size() - 1; index >= 0; index--) {
            while(!stack.isEmpty()) {
                res[index--] = stack.pop();
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(" Result for [0,9,9,9,7,7,9,6,6,8] is " + Arrays.toString(removeDups(new int[]{0,9,9,9,7,7,9,6,6,8})));
        System.out.println(" Result for [0,9,9,9,7,7,6,6,8] is " + Arrays.toString(removeDups(new int[]{0,9,9,9,7,7,6,6,8})));
    }
}

