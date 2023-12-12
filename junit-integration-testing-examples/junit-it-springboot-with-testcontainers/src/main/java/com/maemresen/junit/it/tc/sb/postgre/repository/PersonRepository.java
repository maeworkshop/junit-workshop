package com.maemresen.junit.it.tc.sb.postgre.repository;

import com.maemresen.junit.it.tc.sb.postgre.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Emre Şen (maemresen@yazilim.vip), 11/12/2022
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findTopByNameIgnoreCase(String name);
}
