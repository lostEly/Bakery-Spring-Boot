package com.test.bakery.service;

import com.test.bakery.model.OrderToProduct;
import com.test.bakery.model.Product;
import com.test.bakery.repository.OrderRepository;
import com.test.bakery.repository.OrderToProductRepository;
import com.test.bakery.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final OrderToProductRepository orderToProductRepository;
    private final OrderRepository orderRepository;

    public ProductService(ProductRepository productRepository, OrderToProductRepository orderToProductRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderToProductRepository = orderToProductRepository;
        this.orderRepository = orderRepository;
    }

    public Product findByProductName(String name)
    {
        return productRepository.findByProductName(name);
    }

    public List<Product> findAll()
    {
        return productRepository.findAll();
    }


    public OrderToProduct addProductToBasket(long orderId, String productName, int amount){
        OrderToProduct otp = new OrderToProduct();
        orderRepository.findById(orderId).ifPresent(otp::setOrder);
        otp.setProduct(productRepository.findByProductName(productName));
        otp.setAmount(amount);
        orderToProductRepository.save(otp);
        return otp;
    }





}
