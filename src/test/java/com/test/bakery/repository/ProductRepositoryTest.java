package com.test.bakery.repository;

import com.test.bakery.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@ActiveProfiles("test")
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findByProductName() {
//        Product product = new Product();
//        product.setProductName("testName");
//        product.setDescription("testDescription");
//        product.setImage("https://www.lanworks.com/wp-content/uploads/2017/02/test-button-1024x1024.png");
//        product.setPrice(5d);
//        product.setCategory(categoryRepository.findByCategoryName("Bread"));
//        product.setCount(1);
//        productRepository.save(product);

//        assertNotNull(product);
//            assertTrue(product.getProductId()>0);
        Optional<Product> prod = productRepository.findByProductName("testName");
        assertNotNull(prod.get());
        assertEquals("testDescription", prod.get().getDescription());
    }

    @Test
    void findByProductId() {
        Optional<Product> prod = productRepository.findByProductId(1L);
        assertNotNull(prod.get());
        assertEquals("testDescription", prod.get().getDescription());
    }
}