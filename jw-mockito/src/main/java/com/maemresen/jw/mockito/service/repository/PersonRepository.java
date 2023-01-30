package com.maemresen.jw.mockito.service.repository;

import com.maemresen.jw.mockito.service.domain.Person;

import java.util.Optional;

public interface PersonRepository {
    Optional<Person> findByName(String name);
}
