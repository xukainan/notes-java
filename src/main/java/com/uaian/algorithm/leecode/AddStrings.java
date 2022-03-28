package com.uaian.algorithm.leecode;

/**
 * 字符串相加
 *
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
 *
 * 输入：num1 = "11", num2 = "123"
 * 输出："134"
 */
public class AddStrings {
    public static void main(String[] args) {
        String num1 = "1";
        String num2 = "9";
        String res = addStrings(num1, num2);
        System.out.println(res);
    }

    public static String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        char[] char1 = num1.toCharArray();
        char[] char2 = num2.toCharArray();
        int char1Index = num1.length() -1, char2Index = num2.length() - 1;
        int carry = 0;
        while (char1Index >= 0 && char2Index >= 0){
            int curSum = getInteger(char1[char1Index--]) + getInteger(char2[char2Index--]) + carry;
            carry = curSum / 10;
            sb.append(curSum % 10);
        }

        while (char1Index >= 0){
            if(carry != 0){
                int curSum = getInteger(char1[char1Index--]) + carry;
                sb.append(curSum%10);
                carry = curSum / 10;;
            }else {
                sb.append(char1[char1Index--]);
            }
        }

        while (char2Index >= 0){
            if(carry != 0){
                int curSum = getInteger(char2[char2Index--]) + carry;
                sb.append(curSum%10);
                carry = curSum / 10;;
            }else {
                sb.append(char2[char2Index--]);
            }
        }

        while (carry > 0){
            sb.append(1);
            carry--;
        }

        return sb.reverse().toString();
    }

    public static int getInteger(char c){
        return c  - '0';
    }
}
