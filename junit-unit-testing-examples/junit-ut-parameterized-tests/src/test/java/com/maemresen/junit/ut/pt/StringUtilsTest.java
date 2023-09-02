package com.maemresen.junit.ut.pt;

import com.maemresen.junit.ut.pt.helper.NonBlankRandomStringArgumentProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringUtilsTest {

    private static Stream<Arguments> provideStringsForIsBlank() {
        return Stream.of(
                Arguments.of((String) null),
                Arguments.of(""),
                Arguments.of("  ")
        );
    }

    @ParameterizedTest
    @NullSource
    void isBlank_ShouldReturnTrueForNullStrings(String str) {
        assertTrue(StringUtils.isBlank(str));
    }

    @ParameterizedTest
    @EmptySource
    void isBlank_ShouldReturnTrueForEmptyStrings(String str) {
        assertTrue(StringUtils.isBlank(str));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void isBlank_ShouldReturnTrueForNullAndEmptyStrings(String str) {
        assertTrue(StringUtils.isBlank(str));
    }

    @ParameterizedTest
    @MethodSource("provideStringsForIsBlank")
    void isBlank_ShouldReturnTrueForNullOrBlankStrings(String str) {
        assertTrue(StringUtils.isBlank(str));
    }

    @ParameterizedTest
    @ArgumentsSource(NonBlankRandomStringArgumentProvider.class)
    void isBlank_ShouldReturnFalseForNonBlankStrings(String str) {
        assertFalse(StringUtils.isBlank(str));
    }
}