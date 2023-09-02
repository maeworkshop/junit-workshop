package com.maemresen.junit.ut.pt;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberUtilsTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, -3, 15, Integer.MAX_VALUE})
    void isOdd_ShouldReturnTrueForOddNumbers(int number) {
        assertTrue(NumberUtils.isOdd(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, -2, 0, Integer.MIN_VALUE})
    void isOdd_ShouldReturnFalseForEvenNumbers(int number) {
        assertFalse(NumberUtils.isOdd(number));
    }

    @ParameterizedTest
    @CsvSource(
            value = {
                    "1:true",
                    "2:false",
                    "3:true",
                    "4:false",
                    "5:true"
            },
            delimiter = ':'
    )
    void isOdd_ShouldReturnTrueForOddNumbersAndFalseForEvenNumbers(int number, boolean expected) {
        assertEquals(expected, NumberUtils.isOdd(number));
    }

    @ParameterizedTest
    @CsvSource(
            value = {
                    "1:2:3",
                    "2:3:5",
                    "3:4:7",
                    "4:5:9",
                    "5:6:11"
            },
            delimiter = ':'
    )
    void add_ShouldReturnSumOfTwoNumbers(int a, int b, int expected) {
        assertEquals(expected, NumberUtils.add(a, b));
    }
}

