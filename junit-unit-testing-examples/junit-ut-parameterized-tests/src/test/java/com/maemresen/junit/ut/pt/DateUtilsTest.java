package com.maemresen.junit.ut.pt;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.JavaTimeConversionPattern;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateUtilsTest {

    @DisplayName("getYear should return year of date")
    @ParameterizedTest(name = "getYear({0}) should return {1}")
    @CsvSource(value = {"2012/12/25:2012", "2013/12/25:2013", "2014/12/25:2014"}, delimiter = ':')
    void getYear_ShouldReturnYearOfDate(@ConvertWith(LocalDateConverter.class) LocalDate date, int expected) {
        assertEquals(expected, DateUtils.getYear(date));
    }

    @DisplayName("getYear should return year of date")
    @ParameterizedTest(name = "getYear({0}) should return {1}")
    @CsvSource(value = {"2012/12/25:2012", "2013/12/25:2013", "2014/12/25:2014"}, delimiter = ':')
    void getYear_ShouldReturnYearOfDate2(@JavaTimeConversionPattern("yyyy/MM/dd") LocalDate date, int expected) {
        assertEquals(expected, DateUtils.getYear(date));
    }
}