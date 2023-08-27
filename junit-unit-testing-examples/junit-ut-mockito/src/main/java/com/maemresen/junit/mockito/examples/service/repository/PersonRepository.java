package com.maemresen.junit.mockito.examples.service.repository;

import com.maemresen.junit.mockito.examples.service.domain.Person;

import java.util.Optional;

public interface PersonRepository {
    Optional<Person> findByName(String name);
}
