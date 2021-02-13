package com.test.bakery.repository;


import com.test.bakery.model.Order;
import com.test.bakery.model.OrderToProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> getAllByUserr_UserrId (Long userrId);
    Optional<Order> getOrderByOrderId(Long orderId);
}