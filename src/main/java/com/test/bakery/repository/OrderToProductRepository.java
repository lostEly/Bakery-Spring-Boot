package com.test.bakery.repository;

import com.test.bakery.model.Category;
import com.test.bakery.model.OrderToProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderToProductRepository extends JpaRepository<OrderToProduct, Long> {
}
