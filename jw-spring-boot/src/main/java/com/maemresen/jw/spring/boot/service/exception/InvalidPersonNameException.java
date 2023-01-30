package com.maemresen.jw.spring.boot.service.exception;

public class InvalidPersonNameException extends BusinessException {
    public InvalidPersonNameException(String message) {
        super(message);
    }
}
