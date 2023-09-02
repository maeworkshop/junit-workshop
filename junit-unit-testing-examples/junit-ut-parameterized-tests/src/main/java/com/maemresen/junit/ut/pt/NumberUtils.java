package com.maemresen.junit.ut.pt;

import lombok.experimental.UtilityClass;

@UtilityClass
public class NumberUtils {
    public static boolean isOdd(int number) {
        return number % 2 != 0;
    }

    public static int add(int a, int b) {
        return a + b;
    }
}
