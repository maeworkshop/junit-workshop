package com.maemresen.junit.ut.pt;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SimpleParameterizedTest {
    private static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    @ParameterizedTest
    @NullSource
    void nullSourceTest(String str) {
        assertTrue(isBlank(str));
    }

    @ParameterizedTest
    @EmptySource
    void emptySourceTest(String str) {
        assertTrue(isBlank(str));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void nullAndEmptySourceTest(String str) {
        assertTrue(isBlank(str));
    }


}