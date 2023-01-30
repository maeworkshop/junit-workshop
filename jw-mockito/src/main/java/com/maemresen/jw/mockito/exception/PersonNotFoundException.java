package com.maemresen.jw.mockito.exception;

public class PersonNotFoundException extends BusinessException {
    public PersonNotFoundException(String personName) {
        super(String.format("Person with name '%s' not found", personName));
    }
}
