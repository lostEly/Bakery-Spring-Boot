package com.test.bakery.repository;

import com.test.bakery.model.Category;
import com.test.bakery.model.OrderToProduct;
import com.test.bakery.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderToProductRepository extends JpaRepository<OrderToProduct, Long> {
    List<OrderToProduct> findAllByOrderOrderId (Long orderId);
    Optional<OrderToProduct> findByOrder_OrderIdAndProduct_ProductId (Long orderId, Long productId);
}
