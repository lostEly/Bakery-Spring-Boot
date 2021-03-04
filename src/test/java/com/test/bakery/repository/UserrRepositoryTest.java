package com.test.bakery.repository;

import com.test.bakery.model.Userr;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
@ActiveProfiles("test")
@Sql(value = {"/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class UserrRepositoryTest {

    @Autowired
    private UserrRepository userrRepository;

    @Test
    void getAllUsers() {
        List<Userr> list = userrRepository.getAllUsers();
        assertNotNull(list);
        System.out.println(list.size());
    }

    @Test
    void findByLogin() {
        Optional<Userr> userr = userrRepository.findByLogin("test_login");
//        System.out.println(userr.isPresent() );
        assertTrue(userr.isPresent());
    }

    @Test
    void findByEmail() {
        Optional<Userr> userr = userrRepository.findByEmail("testt@gmail.com");
        assertTrue(userr.isPresent());
        System.out.println(userr.get().getEmail());
    }
}