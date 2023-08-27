
package com.maemresen.junit.spring.boot.basic.examples;

import com.maemresen.junit.spring.boot.basic.examples.service.domain.Person;
import com.maemresen.junit.spring.boot.basic.examples.service.exception.BusinessException;
import com.maemresen.junit.spring.boot.basic.examples.service.impl.PersonServiceImpl;
import com.maemresen.junit.spring.boot.basic.examples.service.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class PersonServiceTest {

    @Autowired
    PersonServiceImpl personService;

    @MockBean
    PersonRepository personRepository;

    @Test
    void givenExistingPersonName_adultContentAllowed() throws BusinessException {
        Person mockPerson = new Person("Mock", 19);
        when(personRepository.findByName(any())).thenReturn(Optional.of(mockPerson));
        Assertions.assertTrue(personService.isAdultContentAllowed("dummy"));
    }

    @Test
    void givenExistingPersonName_adultContentNotAllowed() throws BusinessException {
        Person mockPerson = new Person("Mock", 10);
        when(personRepository.findByName(any())).thenReturn(Optional.of(mockPerson));
        Assertions.assertFalse(personService.isAdultContentAllowed("dummy"));
    }

    @Test
    void givenNonExistingPersonName_adultContentNotAllowed() {
        when(personRepository.findByName(any())).thenReturn(Optional.empty());
        Assertions.assertThrows(BusinessException.class, () -> personService.isAdultContentAllowed("dummy"));
    }

    @Test
    void givenEmptyPersonName_adultContentNotAllowed() {
        Assertions.assertThrows(BusinessException.class, () -> personService.isAdultContentAllowed(null));
    }
}
