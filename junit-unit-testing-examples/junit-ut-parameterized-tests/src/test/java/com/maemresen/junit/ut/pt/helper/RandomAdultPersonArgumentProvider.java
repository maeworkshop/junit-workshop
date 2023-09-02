package com.maemresen.junit.ut.pt.helper;

import com.maemresen.junit.ut.pt.Person;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Random;
import java.util.stream.Stream;

public class RandomAdultPersonArgumentProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of(new Person("John", getAdultAge())),
                Arguments.of(new Person("Jane", getAdultAge())),
                Arguments.of(new Person("Jack", getAdultAge()))
        );
    }

    private int getAdultAge() {
        return new Random().nextInt(100) + 18;
    }
}
