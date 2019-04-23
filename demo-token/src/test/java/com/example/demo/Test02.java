package com.example.demo;

/**
 * @Auther: Coffee
 * @Date: 2019/4/15
 *
 * java中有三种移位运算符
 *
 * 对于：>>>          无符号右移，忽略符号位，空位都以0补齐
 * value >>> num     --   num 指定要移位值value 移动的位数。
 * 无符号右移的规则只记住一点：忽略了符号位扩展，0补最高位  无符号右移运算符>>>
 * 只是对32位和64位的值有意义
 */
public class Test02 {

    /**
     * @author Jone Hongten
     * @create date：2013-11-2
     * @version 1.0
     *
     *1.左移运算符，num << 1,相当于num乘以2
     *2.右移运算符，num >> 1,相当于num除以2
     *3.无符号右移，忽略符号位，空位都以0补齐
     */

    public static void main(String[] args) {
        int number = 10;
        //原始数二进制
        printInfo(number);
        number = number << 1;
        //左移一位
        printInfo(number);
        number = number >> 1;
        //右移一位
        printInfo(number);

        System.out.println(32 >> 4);
    }

    /**
     * 输出一个int的二进制数
     *
     * @param num
     */
    private static void printInfo(int num) {
        System.out.println(Integer.toBinaryString(num));
        System.out.println(num);
    }
}
