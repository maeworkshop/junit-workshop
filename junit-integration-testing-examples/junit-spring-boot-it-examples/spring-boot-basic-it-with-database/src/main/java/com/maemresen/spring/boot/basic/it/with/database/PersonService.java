package com.maemresen.spring.boot.basic.it.with.database;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PersonService {

    private final PersonRepository personRepository;

    public Person register(Person person) {
        return personRepository.save(person);
    }

    public Person findByUsername(String username) {
        return personRepository.findByUsername(username);
    }
}
