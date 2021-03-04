package com.test.bakery.controller;

import com.test.bakery.DTO.OrderIdDTO;
import com.test.bakery.DTO.OrderInfoDTO;
import com.test.bakery.model.OrderToProduct;
import com.test.bakery.model.Product;
import com.test.bakery.repository.OrderRepository;
import com.test.bakery.repository.OrderToProductRepository;
import com.test.bakery.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/index/")
@CrossOrigin("*")
public class IndexController {
    private final ProductService productService;

    public IndexController(ProductService productService) {

        this.productService = productService;
    }
    @GetMapping("")
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

}

