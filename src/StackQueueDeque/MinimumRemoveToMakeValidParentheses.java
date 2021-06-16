package StackQueueDeque;

import java.util.HashSet;
import java.util.Stack;

/**解法：stack
 * Stack : final stack contains all brackets with their indexes which are invalid
 *
 * while traversing String
 * case 1：if char = '(' then simply push into stack;
 * case 2：else if char = ')' we will check top of the stack
 *         if top of stack is '(' then we found "()", we pop '(' from stack,    //说明是valid
 *                                                    else we push ')' into the stack;  //说明不valid
 * Using HashSet to record all indexes which are invalid, so they can be ignored while building final string.
 **/

class pair {
    char c;
    int pos;
    pair(char c, int pos) {
        this.c = c;
        this.pos = pos;
    }
}

public class MinimumRemoveToMakeValidParentheses {
    public String minRemoveToMakeValid(String s) {
        Stack<pair> st = new Stack<>();
        HashSet<Integer> set = new HashSet<>();

        for(int i =0; i< s.length();i++) {
            if(s.charAt(i) == '(' || s.charAt(i) == ')') {
                if(s.charAt(i) == '(') {
                    st.push(new pair('(', i));
                }
                else {
                    if(!st.isEmpty() && st.peek().c == '(') {
                        st.pop();
                    }
                    else {
                        st.push(new pair(')', i));
                    }

                }

            }
        }

        //storing indexes of the characters in set which are still(invalid) there in stack;
        while(!st.isEmpty()) {
            pair p = st.peek();
            set.add(p.pos);
            st.pop();
        }

        StringBuilder sb = new StringBuilder();

        //traversing through string and checking in set for index
        // if index present in set then it is invalid and no need to consider it in final string.
        for(int i =0; i< s.length() ; i++) {
            if(!set.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
