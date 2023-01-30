package com.maemresen.jw.spring.boot.service;


import com.maemresen.jw.spring.boot.service.exception.BusinessException;

public interface PersonService {
    boolean isAdultContentAllowed(String personName) throws BusinessException;
}
