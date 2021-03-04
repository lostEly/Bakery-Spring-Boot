package com.test.bakery.repository;

import com.test.bakery.model.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
@ActiveProfiles("test")
@Sql(value = {"/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-order-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-order-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(value = {"/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void getAllByUserr_UserrId() {

        List<Order> list = orderRepository.findAllByUserr_UserrId(1L);
        System.out.println(list.isEmpty());
        assertFalse(list.isEmpty());
    }

    @Test
    void getOrderByOrderId() {
        Optional<Order> order = orderRepository.findOrderByOrderId(1L);
        assertTrue(order.isPresent());
        System.out.println(order.get().getDateOfCompletion());
    }
}