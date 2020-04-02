package com.mj.android_note.data_structure.algorithm.base.str;

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

}
