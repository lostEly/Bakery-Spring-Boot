package com.test.bakery.controller;

import com.test.bakery.DTO.BasketDTO;
import com.test.bakery.model.OrderToProduct;
import com.test.bakery.model.Product;
import com.test.bakery.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/index")
@CrossOrigin("*")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productRepository) {
        this.productService = productRepository;
    }

    @GetMapping("")
    public List<Product> getAllProducts(){
        return productService.findAll();
    }

    @GetMapping("/{name}")
    public Product getProductByName(@PathVariable (value = "name") String name)
    {
        return productService.findByProductName(name);
    }
    @PostMapping("")
    public int addProductToBasket(@RequestBody BasketDTO basket)
    {
        return productService.addProductToBasket(basket.getOrderId(),
                basket.getProductName(), basket.getAmount()).getAmount();
    }

}
