package com.test.bakery.controller;

import com.test.bakery.DTO.BasketDTO;
import com.test.bakery.DTO.DeleteOTP;
import com.test.bakery.DTO.UpdateOTP;
import com.test.bakery.exceptions.ResourceNotFoundException;
import com.test.bakery.model.OrderToProduct;
import com.test.bakery.model.Product;
import com.test.bakery.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/index")
@CrossOrigin()
public class ProductController {
    Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public List<Product> getAllProducts() { logger.error("MESSAGE");
         return productService.findAll();
    }

    @GetMapping("/{name}")
    public Product getProductByName(@PathVariable (value = "name") String name) {
            return productService.findByProductName(name);
    }
    @PostMapping("")
    public int addProductToBasket(@RequestBody BasketDTO basket)
    {
        Long orderId = productService.getOrderInfo(basket.getLogin());
        return productService.addProductToBasket(orderId,
                basket.getProductId(), basket.getAmount());
    }

    @DeleteMapping("/cart") // получаем оредИД, ПродИД, по ним находим ОТП и удаляем
    public String deleteOtp(@RequestBody DeleteOTP deleteOTP) {
        Long orderId = productService.getOrderInfo(deleteOTP.getLogin());
        OrderToProduct otp = productService.findByOrderOrderIdAndProductProductId(
                orderId,
                deleteOTP.getProductId());
        productService.deleteOtp(otp);
        return "OK";
    }

    //@TODO
    //Вместо спика продуктов передавать мапу из продактИД - продактЭмаунт
    @PutMapping("/cart")
    public Object updateOtp(@RequestBody UpdateOTP updateOTP)
    {
        Long orderId = productService.getOrderInfo(updateOTP.getLogin());
        productService.updateOTP(updateOTP.getProducts(), orderId, updateOTP.getTotalPrice());
        return null;
    }
}
