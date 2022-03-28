package com.uaian.algorithm.leecode;

import scala.Char;

import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *
 *
 * 输入：s = "([)]"
 * 输出：false
 *
 * 输入：s = "{[]}"
 * 输出：true
 */
public class ValidParentheses {
    public static void main(String[] args) {
        String s = "{[]}";
        boolean valid = isValid(s);
        System.out.println(valid);
    }

    private static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (Character c: s.toCharArray()) {
            if(c == '(') stack.push(')');
            else if(c == '[') stack.push(']');
            else if(c == '{') stack.push('}');
            else if(stack.isEmpty() || stack.pop() != c){
                return false;
            }
        }
        return stack.isEmpty();
    }

//    public static boolean isValid(String s) {
//        while (s.contains("{}") || s.contains("[]") || s.contains("()")){
//            s = s.replace("{}", "");
//            s = s.replace("[]", "");
//            s = s.replace("()", "");
//        }
//        return s.length() == 0;
//    }
}
