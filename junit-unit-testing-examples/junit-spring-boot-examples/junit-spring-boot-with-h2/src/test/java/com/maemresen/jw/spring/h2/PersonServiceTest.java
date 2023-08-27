
package com.maemresen.jw.spring.h2;

import com.maemresen.jw.spring.h2.service.domain.Person;
import com.maemresen.jw.spring.h2.service.exception.BusinessException;
import com.maemresen.jw.spring.h2.service.impl.PersonServiceImpl;
import com.maemresen.jw.spring.h2.service.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(properties = {
        // H2 database configuration
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.datasource.driverClassName=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=",

        // Disabling ddl-auto, hence repository will be mocked and not needed to create tables
        "spring.jpa.hibernate.ddl-auto=none"
})
class PersonServiceTest {

    @Autowired
    PersonServiceImpl personService;

    @MockBean
    PersonRepository personRepository;

    @Test
    void givenExistingPersonName_adultContentAllowed() throws BusinessException {
        Person mockPerson = new Person();
        mockPerson.setName("Mock");
        mockPerson.setAge(19);
        when(personRepository.findByName(any())).thenReturn(Optional.of(mockPerson));
        Assertions.assertTrue(personService.isAdultContentAllowed("dummy"));
    }

    @Test
    void givenExistingPersonName_adultContentNotAllowed() throws BusinessException {
        Person mockPerson = new Person();
        mockPerson.setName("Mock");
        mockPerson.setAge(10);
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
