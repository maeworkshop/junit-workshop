package com.maemresen.junit.it.tc.sb.postgresql.repository;

import com.maemresen.junit.it.tc.sb.postgresql.domain.Person;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Emre Şen (maemresen@yazilim.vip), 11/12/2022
 */
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findTopByNameIgnoreCase(String name);
}
