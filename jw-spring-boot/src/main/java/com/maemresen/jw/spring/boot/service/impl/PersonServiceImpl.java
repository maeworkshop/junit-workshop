package com.maemresen.jw.spring.boot.service.impl;


import com.maemresen.jw.spring.boot.service.PersonService;
import com.maemresen.jw.spring.boot.service.domain.Person;
import com.maemresen.jw.spring.boot.service.exception.BusinessException;
import com.maemresen.jw.spring.boot.service.exception.InvalidPersonNameException;
import com.maemresen.jw.spring.boot.service.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

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
