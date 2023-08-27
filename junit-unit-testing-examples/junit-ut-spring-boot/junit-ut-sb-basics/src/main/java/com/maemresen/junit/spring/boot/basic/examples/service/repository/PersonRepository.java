package com.maemresen.junit.spring.boot.basic.examples.service.repository;


import com.maemresen.junit.spring.boot.basic.examples.service.domain.Person;

import java.util.Optional;

public interface PersonRepository {
    Optional<Person> findByName(String name);
}
