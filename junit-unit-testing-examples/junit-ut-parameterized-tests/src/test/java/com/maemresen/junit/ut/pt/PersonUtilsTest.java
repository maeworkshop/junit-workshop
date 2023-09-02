package com.maemresen.junit.ut.pt;

import com.maemresen.junit.ut.pt.helper.RandomAdultPersonArgumentProvider;
import com.maemresen.junit.ut.pt.helper.RandomAdultPersonSource;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PersonUtilsTest {

    @ParameterizedTest(name = "isAdult({0}) should return true")
    @ArgumentsSource(RandomAdultPersonArgumentProvider.class)
    void isAdult_ShouldReturnTrue_WhenAgeIsGreaterThan18(Person person) {
        assertTrue(PersonUtils.isAdult(person));
    }

    @ParameterizedTest(name = "isAdult({0}) should return true")
    @RandomAdultPersonSource
    void isAdult_ShouldReturnTrue_WhenAgeIsGreaterThan18_withCustomAnnotation(Person person) {
        assertTrue(PersonUtils.isAdult(person));
    }
}