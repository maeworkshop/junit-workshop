package com.maemresen.jw.mockito.service;

import com.maemresen.jw.mockito.service.exception.BusinessException;

public interface PersonService {
    boolean isAdultContentAllowed(String personName) throws BusinessException;
}
