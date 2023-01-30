package com.maemresen.jw.mockito.repository;

import com.maemresen.jw.mockito.domain.Person;
import com.maemresen.jw.mockito.exception.BusinessException;

import java.util.Optional;

public interface PersonRepository {
    Optional<Person> findByName(String name);

    boolean isAdultContentAllowedForPerson(String personName) throws BusinessException;
}
