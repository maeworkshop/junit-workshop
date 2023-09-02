package com.maemresen.junit.ut.pt;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MonthTest {

    @ParameterizedTest(name = "{0}.getValue() must be between 1 and 12")
    @EnumSource(Month.class)
    void getValue_ShouldReturnValidRangeForMonths(Month month) {
        int monthNumber = month.getValue();
        assertAll(
                () -> assertTrue(monthNumber >= 1),
                () -> assertTrue(monthNumber <= 12));
    }

    @ParameterizedTest(name = "{0} must have 30 days")
    @EnumSource(value = Month.class, names = {"APRIL", "JUNE", "SEPTEMBER", "NOVEMBER"})
    void getLength_ShouldReturn30ForMonthsHaving30Days(Month month) {
        assertEquals(30, month.length(false));
    }

    @ParameterizedTest(name = "{0} must have 31 days")
    @EnumSource(
            value = Month.class,
            names = {"APRIL", "JUNE", "SEPTEMBER", "NOVEMBER", "FEBRUARY"},
            mode = EnumSource.Mode.EXCLUDE
    )
    void getLength_ShouldReturn31ForMonthsHaving31Days(Month month) {
        assertEquals(31, month.length(false));
    }

    @ParameterizedTest(name = "{0} must ends with BER")
    @EnumSource(
            value = Month.class,
            names = ".+BER",
            mode = EnumSource.Mode.MATCH_ANY
    )
    void monthsWithNamesEndingWithBer_ShouldReturnMonthsEndingWithBer(Month month) {
        assertTrue(month.name().endsWith("BER"));
    }
}
