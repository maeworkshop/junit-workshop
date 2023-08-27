package com.maemresen.junit.spring.boot.basic.examples.service;


import com.maemresen.junit.spring.boot.basic.examples.service.exception.BusinessException;

public interface PersonService {
    boolean isAdultContentAllowed(String personName) throws BusinessException;
}
