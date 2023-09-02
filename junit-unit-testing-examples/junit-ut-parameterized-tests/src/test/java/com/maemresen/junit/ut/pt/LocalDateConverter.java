package com.maemresen.junit.ut.pt;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateConverter implements ArgumentConverter {
    @Override
    public Object convert(Object source, ParameterContext context) throws ArgumentConversionException {
        if (source instanceof String dateString) {
            return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        }
        throw new ArgumentConversionException(source + " is not a string");
    }
}
