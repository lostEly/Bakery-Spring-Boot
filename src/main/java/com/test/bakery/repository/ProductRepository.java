package com.test.bakery.repository;

import com.test.bakery.model.Product;
import com.test.bakery.model.Userr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductName (String name);
    Optional<Product> findByProductId (Long productId);
}
