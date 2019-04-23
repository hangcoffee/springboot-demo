package com.example.demo;

/**
 * @Auther: Coffee
 * @Date: 2019/4/15
 */
public class Test {

    /**
     * 异或运算特点：
     * 1.真异或假的结果是真，
     * 2.假异或真的结果也是真，
     * 3.真异或真的结果是假，
     * 4.假异或假的结果是假。
     * 就是说两个值不相同-->异结果为真
     *
     * 异或的运算方法是一个二进制运算
     * 任何数与0进行异或，为它本身
     * 两者相等为0,  不等为1.
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] A = {1, 2, 5, 4, 4, 1, 2};
        int n = A[0];
        for (int i = 1; i < A.length; i++) {
            n = n ^ A[i];
        }
        System.out.println(n);
    }
}
