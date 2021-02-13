package com.test.bakery.service;

import com.test.bakery.exceptions.ResourceNotFoundException;
import com.test.bakery.model.Order;
import com.test.bakery.model.OrderToProduct;
import com.test.bakery.model.Product;
import com.test.bakery.model.Userr;
import com.test.bakery.repository.CategoryRepository;
import com.test.bakery.repository.OrderRepository;
import com.test.bakery.repository.OrderToProductRepository;
import com.test.bakery.repository.ProductRepository;
import org.checkerframework.checker.nullness.Opt;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private  ProductRepository productRepository;

    @MockBean
    private  OrderToProductRepository orderToProductRepository;

    @MockBean
    private  OrderRepository orderRepository;

    @MockBean
    private  UserService userService;

    @MockBean
    private  CategoryRepository categoryRepository;

    @Test
    void findByProductName() {
        Product product = new Product();
        Optional<Product> prod = Optional.of(product);
        Mockito.doReturn(prod)
                .when(productRepository)
                .findByProductName(anyString());
        assertSame(Product.class, productService.findByProductName(anyString()).getClass());
    }

    @Test
    void findAll() {
        assertNotNull(productService.findAll());
        Mockito.verify(productRepository, Mockito.times(1)).findAll();
    }

    @Test
    void findByOrderOrderIdAndProductProductId_ShouldReturnOrderToProduct() {
        OrderToProduct otp = new OrderToProduct();
        otp.setOtpId(1L);
        Product prod = new Product();
        prod.setProductId(1L);
        otp.setProduct(prod);
        Optional<OrderToProduct> orderToProduct = Optional.of(otp);
        Mockito.doReturn(orderToProduct)
                .when(orderToProductRepository)
                .findByOrder_OrderIdAndProduct_ProductId(anyLong(),anyLong());
        assertSame(OrderToProduct.class, productService.findByOrderOrderIdAndProductProductId(anyLong(),anyLong()).getClass());
    }

    @Test
    void findByOrderOrderIdAndProductProductId_ShouldReturnResourceNotFoundException() {
        assertThrows(ResourceNotFoundException.class, () -> productService.findByOrderOrderIdAndProductProductId(null, null));
    }

    @Test
    void deleteOtp_OrderToProductGiven_ShouldDeleteOtp() {
        OrderToProduct otp = new OrderToProduct();
        productService.deleteOtp(otp);
        Mockito.verify(orderToProductRepository, Mockito.times(1)).delete(otp);
    }

    /**
     * Test method with given login.
     * Return null because OrderId AutoGenerates after save method,
     */
    @Test
    void getOrderInfo_LoginGiven_ShouldReturnNewOrderId() {
        Userr userr = new Userr();
        Mockito.doReturn(userr)
                .when(userService)
                .findByLogin(anyString());
        List<Order> list = Collections.emptyList();
        Mockito.doReturn(list)
                .when(orderRepository)
                .getAllByUserr_UserrId(anyLong());
        assertNull(productService.getOrderInfo(anyString()));
    }

    @Test
    void getOrderInfoWithEmptyList_LoginGiven_ShouldReturnNewOrderId() {
        Userr userr = new Userr();
        Mockito.doReturn(userr)
                .when(userService)
                .findByLogin(anyString());
        List<Order> list = Collections.singletonList(new Order());
        Mockito.doReturn(list)
                .when(orderRepository)
                .getAllByUserr_UserrId(anyLong());
        assertNull(productService.getOrderInfo(anyString()));
    }

    @Test
    void addProductToBasket() {
        Order order = new Order();
        Optional<Order> optionalOrder = Optional.of(order);
        Mockito.doReturn(optionalOrder)
                .when(orderRepository)
                .findById(anyLong());
        Product product = new Product();
        Optional<Product> optionalProduct = Optional.of(product);
        Mockito.doReturn(optionalProduct)
                .when(productRepository)
                .findById(anyLong());
        List<OrderToProduct> list = Collections.singletonList(new OrderToProduct());
        Mockito.doReturn(list)
                .when(orderToProductRepository)
                .findAllByOrderOrderId(anyLong());
        assertEquals(1, productService.addProductToBasket(1L, 1L, 5));
    }

    @Test
    void updateOTP() {

    }

    @Test
    void updateOrder() {
    }

    @Test
    void getProductByProductId() {
        Product product = new Product();
        Optional<Product> optionalProduct = Optional.of(product);
        Mockito.doReturn(optionalProduct)
                .when(productRepository)
                .findByProductId(anyLong());
        assertSame(Product.class, productService.getProductByProductId(1L).getClass());
    }

    @Test
    void changeProduct() {

    }

    @Test
    void deleteProduct() {
    }

    @Test
    void addProduct() {
    }
}