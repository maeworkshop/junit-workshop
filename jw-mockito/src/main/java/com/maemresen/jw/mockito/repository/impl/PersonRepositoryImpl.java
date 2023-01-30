package com.maemresen.jw.mockito.repository.impl;

import com.maemresen.jw.mockito.domain.Person;
import com.maemresen.jw.mockito.exception.BusinessException;
import com.maemresen.jw.mockito.exception.InvalidArgumentException;
import com.maemresen.jw.mockito.exception.PersonNotFoundException;
import com.maemresen.jw.mockito.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

public class PersonRepositoryImpl implements PersonRepository {

    private static final List<Person> PERSON_DATABASE = List.of(
            new Person("Emre", 18),
            new Person("Mehmet", 35),
            new Person("Arif", 10)
    );

    @Override
    public Optional<Person> findByName(String name) {
        return PERSON_DATABASE.stream()
                .filter(person -> person.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    @Override
    public boolean isAdultContentAllowedForPerson(String personName) throws BusinessException {
        if (personName == null) {
            throw new InvalidArgumentException("Person name cannot be null.");
        }

        Person person = findByName(personName).orElseThrow(() -> new PersonNotFoundException(personName));
        int age = person.getAge();
        return age > 18;
    }
}
