package com.example.SchoolOpdracht;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.example.SchoolOpdracht.model.Afwezig;
import com.example.SchoolOpdracht.repository.AfwezigRepository;

@DataJpaTest
class AfwezigRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AfwezigRepository afwezigRepository;

    @Test
    void testFindByReason() {

        //given
        LocalDate startDate = LocalDate.now(); 
        LocalDate endDate = LocalDate.now().plusDays(30L);
        Afwezig afwezig = new Afwezig("Vacation", startDate, endDate);
        entityManager.persist(afwezig);

        //when
        
    }
}
