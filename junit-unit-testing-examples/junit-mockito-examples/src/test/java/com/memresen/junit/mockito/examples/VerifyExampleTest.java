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
import static org.mockito.Mockito.description;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VerifyExampleTest {

    PersonService personService;

    @Mock
    PersonRepository personRepository;

    @BeforeEach
    void init() {
        personService = new PersonServiceImpl(personRepository);
    }

    @Test
    @DisplayName("Given existing person name and above age 18, when isAdultContentAllowed, then true")
    void givenExistingPersonNameAndAboveAge18_whenIsAdultContentAllowed_thenTrue() throws BusinessException {
        final var personName = "Mock";
        final var mockPerson = new Person("Mock", 19);
        when(personRepository.findByName(personName)).thenReturn(Optional.of(mockPerson));
        verify(personRepository, description("Person should be searched by name")).findByName(personName);
        Assertions.assertTrue(personService.isAdultContentAllowed(personName), "Adult content should be allowed");
    }

}
