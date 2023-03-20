package com.example.SchoolOpdracht;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.example.SchoolOpdracht.model.Afwezig;
import com.example.SchoolOpdracht.repository.AfwezigRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
class AfwezigRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AfwezigRepository afwezigRepository;

    @Test
    void testIdGetsAssigned() {

        //adds the Model to the Database.
        LocalDate startDate = LocalDate.now(); 
        LocalDate endDate = LocalDate.now().plusDays(30L);
        Afwezig afwezig = new Afwezig("Vacation", startDate, endDate);
        afwezigRepository.save(afwezig);
        entityManager.flush();

        //checks if an ID is being given back
        assertNotNull(afwezig.getAfwezigId());
    }
}
