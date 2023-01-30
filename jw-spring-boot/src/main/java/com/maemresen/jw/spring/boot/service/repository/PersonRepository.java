package com.maemresen.jw.spring.boot.service.repository;


import com.maemresen.jw.spring.boot.service.domain.Person;

import java.util.Optional;

public interface PersonRepository {
    Optional<Person> findByName(String name);
}
