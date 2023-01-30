package com.memresen.jw.mockito;

import com.maemresen.jw.mockito.service.PersonService;
import com.maemresen.jw.mockito.service.domain.Person;
import com.maemresen.jw.mockito.service.exception.BusinessException;
import com.maemresen.jw.mockito.service.exception.InvalidPersonNameException;
import com.maemresen.jw.mockito.service.impl.PersonServiceImpl;
import com.maemresen.jw.mockito.service.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    PersonService personService;

    @Mock
    PersonRepository personRepository;


    @BeforeEach
    void init(){
        personService = new PersonServiceImpl(personRepository);
    }


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
