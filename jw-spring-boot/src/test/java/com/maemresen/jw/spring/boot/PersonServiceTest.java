
package com.maemresen.jw.spring.boot;

import com.maemresen.jw.spring.boot.service.PersonService;
import com.maemresen.jw.spring.boot.service.domain.Person;
import com.maemresen.jw.spring.boot.service.exception.BusinessException;
import com.maemresen.jw.spring.boot.service.impl.PersonServiceImpl;
import com.maemresen.jw.spring.boot.service.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
