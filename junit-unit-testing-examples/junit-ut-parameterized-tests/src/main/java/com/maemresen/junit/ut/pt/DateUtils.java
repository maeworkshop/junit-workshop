package com.maemresen.junit.ut.pt;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;

@UtilityClass
public class DateUtils {

    public static int getYear(LocalDate localDate) {
        return localDate.getYear();
    }
}
