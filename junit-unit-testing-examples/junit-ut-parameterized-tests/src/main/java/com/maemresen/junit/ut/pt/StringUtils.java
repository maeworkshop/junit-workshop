package com.maemresen.junit.ut.pt;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtils {
    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }
}
