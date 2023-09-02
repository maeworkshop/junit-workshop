package com.maemresen.junit.ut.pt;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PersonUtils {

    public static boolean isAdult(Person person) {
        return person.getAge() >= 18;
    }
}
