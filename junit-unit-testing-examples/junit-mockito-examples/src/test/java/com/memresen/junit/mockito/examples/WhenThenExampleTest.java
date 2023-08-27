package com.memresen.junit.mockito.examples;

import com.maemresen.junit.mockito.examples.service.PersonService;
import com.maemresen.junit.mockito.examples.service.domain.Person;
import com.maemresen.junit.mockito.examples.service.exception.BusinessException;
import com.maemresen.junit.mockito.examples.service.impl.PersonServiceImpl;
import com.maemresen.junit.mockito.examples.service.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WhenThenExampleTest {

    PersonService personService;

    @Mock
    PersonRepository personRepository;

    @BeforeEach
    void init(){
        personService = new PersonServiceImpl(personRepository);
    }

    @Test
    @DisplayName("Given existing person name and above age 18, when isAdultContentAllowed, then true")
    void givenExistingPersonNameAndAboveAge18_whenIsAdultContentAllowed_thenTrue() throws BusinessException {
        final var personName = "Mock";
        final var mockPerson = new Person("Mock", 19);
        when(personRepository.findByName(personName)).thenReturn(Optional.of(mockPerson));
        Assertions.assertTrue(personService.isAdultContentAllowed(personName), "Adult content should be allowed");
    }

    @Test
    @DisplayName("Given existing person name and below age 18, when isAdultContentAllowed, then false")
    void givenExistingPersonNameAndBelowAge18_whenIsAdultContentAllowed_thenFalse() throws BusinessException {
        Person mockPerson = new Person("Mock", 10);

        when(personRepository.findByName(any())).thenReturn(Optional.of(mockPerson));
        Assertions.assertFalse(personService.isAdultContentAllowed("dummy"), "Adult content should not be allowed");
    }

    @Test
    @DisplayName("Given non existing person name, when isAdultContentAllowed, then throw exception")
    void givenNonExistingPersonName_whenIsAdultContentAllowed_thenThrowException() {
        when(personRepository.findByName(any())).thenReturn(Optional.empty());
        Assertions.assertThrows(BusinessException.class, () -> personService.isAdultContentAllowed("dummy"));
    }

    @Test
    @DisplayName("Given empty person name, when isAdultContentAllowed, then throw exception")
    void givenEmptyPersonName_whenIsAdultContentAllowed_thenThrowException() {
        Assertions.assertThrows(BusinessException.class, () -> personService.isAdultContentAllowed(null));
    }
}
