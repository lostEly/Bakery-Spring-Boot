package com.test.bakery.services;

import com.test.bakery.DTO.AddProductDTO;
import com.test.bakery.exceptions.ResourceNotFoundException;
import com.test.bakery.model.*;
import com.test.bakery.repository.CategoryRepository;
import com.test.bakery.repository.OrderRepository;
import com.test.bakery.repository.OrderToProductRepository;
import com.test.bakery.repository.ProductRepository;
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

    @Test
    void findByProductName_ShouldReturnProduct() {
        Product product = new Product();
        Optional<Product> prod = Optional.of(product);
        Mockito.doReturn(prod)
                .when(productRepository)
                .findByProductName(anyString());
        assertSame(Product.class, productService.getByProductName(anyString()).getClass());
    }
    @Test
    void findByProductName_FailTest()
    {
        assertThrows(ResourceNotFoundException.class, ()->productService.getByProductName(null));
    }

    @Test
    void findAll_ShouldReturnListOfProducts() {
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
        assertSame(OrderToProduct.class, productService.getByOrderOrderIdAndProductProductId(anyLong(),anyLong()).getClass());
    }

    @Test
    void findByOrderOrderIdAndProductProductId_ShouldReturnResourceNotFoundException() {
        assertThrows(ResourceNotFoundException.class, () -> productService.getByOrderOrderIdAndProductProductId(null, null));
    }

    @Test
    void deleteOtp_OrderToProductGiven_ShouldDeleteOtp() {
        OrderToProduct otp = new OrderToProduct();
        productService.deleteOtp(otp);
        Mockito.verify(orderToProductRepository, Mockito.times(1)).delete(otp);
    }

    @Test
    void getOrderInfo_LoginGiven_ShouldReturnNewOrderId() {
        Userr userr = new Userr();
        Mockito.doReturn(userr)
                .when(userService)
                .findByLogin(anyString());
        List<Order> list = Collections.emptyList();
        Mockito.doReturn(list)
                .when(orderRepository)
                .findAllByUserr_UserrId(anyLong());
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
                .findAllByUserr_UserrId(anyLong());
        assertNull(productService.getOrderInfo(anyString()));
    }

    @Test
    void addProductToBasket() {

        ProductService productService1 = Mockito.spy(productService);
        Product product = new Product();
        Mockito.doReturn(product)
                .when(productService1)
                .getProductByProductId(anyLong());
        Order order = new Order();
        Mockito.doReturn(order)
                .when(productService1)
                .findOrderByOrderId(anyLong());
        List<OrderToProduct> list = Collections.singletonList(new OrderToProduct());
        Mockito.doReturn(list)
                .when(orderToProductRepository)
                .findAllByOrderOrderId(anyLong());
        assertEquals(1, productService1.addProductToBasket(1L, 1L, 5));
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

}