package com.example.service.utils;

import java.util.Arrays;

/**
 * @Author ltx
 * @Date 21:51 2020/5/9
 */
public class Test {
    public static void main(String[] args) {
        String s = "1.2.3";
        String[] split = s.split("\\.");
        System.out.println(Arrays.toString(split));
    }
}
