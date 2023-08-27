package com.maemresen.junit.mockito.examples.service;

import com.maemresen.junit.mockito.examples.service.exception.BusinessException;

public interface PersonService {
    boolean isAdultContentAllowed(String personName) throws BusinessException;
}
