package com.maemresen.junit.mockito.examples.service.impl;

import com.maemresen.junit.mockito.examples.service.PersonService;
import com.maemresen.junit.mockito.examples.service.repository.PersonRepository;
import com.maemresen.junit.mockito.examples.service.domain.Person;
import com.maemresen.junit.mockito.examples.service.exception.BusinessException;
import com.maemresen.junit.mockito.examples.service.exception.InvalidPersonNameException;

public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public boolean isAdultContentAllowed(String personName) throws BusinessException {
        if (personName == null) {
            throw new InvalidPersonNameException("Person name cannot be null.");
        }

        Person person = personRepository.findByName(personName).orElseThrow(() -> new InvalidPersonNameException(String.format("Person with name '%s' not found", personName)));
        int age = person.getAge();
        return age > 18;
    }
}
