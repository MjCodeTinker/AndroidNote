package com.mj.android_note.data_structure.algorithm.base.str;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Author      : MJ
 * Date        : 2020-04-02--23:09
 * Email       : miaojian_666@126.com
 * Description :
 */
public class TestStringAlgorithm {

    public static void main(String[] args) {

        //##1，把这个字符串拷贝到androidStudio中  遇到一个大坑，网页中的空格与编译器里的编码格式不一样？？？，这个空格要重新写一下
        String reverseWords = reverseWords("  hello world!  ");
        print("reverseWords :" + "##" + reverseWords + "##");
        print("" + Character.isDigit('1') + "--" + Character.isDigit('a'));
        char judgeChar1 = '1';
        char judgeCHar2 = 'x';
        print("target 1 : " + isLetter(judgeChar1) + "--target 2 : " + isLetter(judgeCHar2));
//        print("字符串转Int : " +  strToInt("160"));
        print("查找字符串中最大数 ： " + findStrMaxNumber("a - #" + 10 + "a16&18"));
        List<Integer> a = new ArrayList<>();
        String[] objects = (String[]) a.toArray();
        Collections.reverse(a);

    }

    /**
     * 判断一个字符是否为数字
     *
     * @param target 目标字符
     * @return 是否为字母
     */
    private static boolean isLetter(char target) {
        return Character.isLowerCase(target) || Character.isUpperCase(target);
    }


    /**
     * 反转一个英文句子
     *
     * @param s 输入一个字符串
     * @return 输入: "  hello world!  "
     * 输出: "world! hello"
     * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
     */
    private static String reverseWords(String s) {
        StringBuilder result = new StringBuilder();
        String[] wordArray = s.trim().split(" ");
        int length = wordArray.length;
        for (int i = length - 1; i >= 0; i--) {
            if (!wordArray[i].equals("") && !wordArray[i].contains(" ")) {
                result.append(wordArray[i]);
                if (i != 0) {
                    result.append(" ");
                }
            }
        }
        return result.toString();
    }


    private static void print(String str) {
        System.out.println(str);
        System.out.println("#########---分隔---#########");
    }


    private static int strToInt(String str) {
        str = str.trim();
        if (str.length() == 0) {
            return 0;
        }
        // 如果第一个字符不是一个数字，并且也不是 + -
        if (!Character.isDigit(str.charAt(0)) && !isPlusOrMinus(str.charAt(0))) {
            return 0;
        }

        StringBuilder result = new StringBuilder();
        result.append(str.charAt(0));
        String findResult = String.valueOf(findStrMaxNumber(str));
        result.append(findResult);

        if (isMinus(str.charAt(0)) && String.valueOf(Integer.MIN_VALUE).length() < result.length()) {
            return Integer.MIN_VALUE;
        }
        if (!isMinus(str.charAt(0)) && String.valueOf(Integer.MAX_VALUE).length() < result.length()) {
            return Integer.MAX_VALUE;
        }
        try {
            return Integer.parseInt(result.toString());
        } catch (Exception e) {
            return 0;
        }
    }

    // 判断是否为正负号
    private static boolean isPlusOrMinus(char target) {
        return isPlus(target) || isMinus(target);
    }

    private static boolean isPlus(char target) {
        return target == '+' || target > '0';
    }

    private static boolean isMinus(char target) {
        return target == '-';
    }

    // 找出字符串中最长数字的字符串
    private static int findStrMaxNumber(String str) {
        str = str.trim();
        int result = 0;
        if (str.equals("")) {
            return result;
        }
        // 以非数字进行分割
        String[] splitArray = str.split("\\D");
        boolean isFirstMinus = true;

        for (String s : splitArray) {
            String tempStr = s.trim();
            if (!tempStr.equals("")) {
                int tempInt = 0;
                try {
                    tempInt = Integer.parseInt(tempStr);
                } catch (Exception e) {
                    // 不做处理，
                }
                if (tempInt < 0) {
                    // 第一次拿到负值
                    if (isFirstMinus) {
                        result = tempInt;
                        isFirstMinus = false;
                    } else if (tempInt > result) {
                        result = tempInt;
                    }
                } else if (tempInt > result) {
                    result = tempInt;
                }
            }
        }
        return result;
    }

}
