package com.maemresen.jw.spring.h2.service;


import com.maemresen.jw.spring.h2.service.exception.BusinessException;

public interface PersonService {
    boolean isAdultContentAllowed(String personName) throws BusinessException;
}
