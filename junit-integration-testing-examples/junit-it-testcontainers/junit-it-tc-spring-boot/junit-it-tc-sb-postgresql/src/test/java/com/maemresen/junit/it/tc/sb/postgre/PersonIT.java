package com.maemresen.junit.it.tc.sb.postgre;

import com.maemresen.junit.it.tc.sb.postgre.domain.Person;
import com.maemresen.junit.it.tc.sb.postgre.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
@SpringBootTest
class PersonIT {

    @Container
    static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15.1");

    static {
        postgreSQLContainer.withDatabaseName("integration-tests-db");
        postgreSQLContainer.withUsername("sa");
        postgreSQLContainer.withPassword("sa");
    }

    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create");
    }

    @Autowired
    private PersonRepository personRepository;

    private Person savePerson(String name) {
        var person = new Person();
        person.setName(name);
        return personRepository.save(person);
    }

    private String randomName() {
        return "name-" + System.currentTimeMillis();
    }

    @Test
    void whenFindByNameIgnoreCase_thenReturnPerson() {
        String name = randomName();
        Person savedPerson = savePerson(name);
        var findPerson = personRepository.findTopByNameIgnoreCase(name.toUpperCase());

        assertTrue(findPerson.isPresent(), "Person is not found");
        assertEquals(savedPerson.getId(), findPerson.get().getId());
    }

    @Test
    void whenFindByNameNonExistingName_thenReturnEmpty() {
        String name = randomName();
        String nonExistingName = "non-existing-name";

        savePerson(name);
        var findPerson = personRepository.findTopByNameIgnoreCase(nonExistingName);

        assertTrue(findPerson.isEmpty(), "Person is found");
    }
}
