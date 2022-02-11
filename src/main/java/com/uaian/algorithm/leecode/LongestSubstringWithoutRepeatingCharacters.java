package com.uaian.algorithm.leecode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        String str= "abba";
        int len = getLongestSubstringWithoutRepeatingCharacters(str);
        System.out.println(len);
    }

    private static int getLongestSubstringWithoutRepeatingCharacters(String str) {
//        if(str.length() == 1) return 1;
//        HashMap<Character, Integer> map = new HashMap<>();
//        char[] chars = str.toCharArray();
//        int maxLen = 0;
//        for (int i = 0; i < chars.length - 1; i++) {
//            int tmp_len = 1;
//            map.put(chars[i], i);
//            for (int j = i + 1; j < chars.length; j++) {
//                if(map.containsKey(chars[j])){
//                    maxLen = maxLen > tmp_len? maxLen:tmp_len;
//                    tmp_len = 0;
//                    map.clear();
//                }
//                map.put(chars[j], j);
//                tmp_len++;
//
//            }
//            map.clear();
//            maxLen = maxLen > tmp_len? maxLen:tmp_len;
//        }
//        return maxLen;
        HashMap<Character, Integer> map = new HashMap<>();
        int maxLen = 0;
        int left = 0;
        int right;

        for (right = 0; right < str.length(); right++) {
            if(map.containsKey(str.charAt(right))) {
                maxLen = maxLen > right - left? maxLen:right - left;
                left = map.get(str.charAt(right)) + 1 > left? map.get(str.charAt(right)) + 1:left;
            }
            map.put(str.charAt(right), right);

        }
        maxLen = maxLen > right - left? maxLen:right - left;
        return maxLen;

    }
}
