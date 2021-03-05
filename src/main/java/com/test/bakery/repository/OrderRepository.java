package com.test.bakery.repository;


import com.test.bakery.model.Order;
import com.test.bakery.model.OrderToProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserr_UserrId(Long userrId);
    Optional<Order> findOrderByOrderId(Long orderId);
    @Query(value="select * from orderr where userr_id= :userr_id and status_id= :status_id", nativeQuery=true)
    List<Order> findAllOngoingOrders(@Param("userr_id") Long userr_id, @Param("status_id") Long status_id);
}