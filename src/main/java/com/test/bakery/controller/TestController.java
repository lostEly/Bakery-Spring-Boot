package com.test.bakery.controller;

import com.test.bakery.DTO.OrderIdDTO;
import com.test.bakery.DTO.OrderInfoDTO;
import com.test.bakery.model.OrderToProduct;
import com.test.bakery.repository.OrderRepository;
import com.test.bakery.repository.OrderToProductRepository;
import com.test.bakery.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
@CrossOrigin("*")
public class TestController {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final OrderToProductRepository orderToProductRepository;

    public TestController(OrderRepository orderRepository, ProductService productService, OrderToProductRepository orderToProductRepository) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.orderToProductRepository = orderToProductRepository;
    }

    @PostMapping("")
    public Long getOrderId(@RequestBody OrderInfoDTO login) {
        return productService.getOrderInfo(login.getLogin());
    }

    @PostMapping("/q")
    public List<OrderToProduct> getOTPrbyOrderrId (@RequestBody OrderIdDTO orderId)
    {
        return orderToProductRepository.findAllByOrderOrderId(orderId.getOrderId());
    }
}
