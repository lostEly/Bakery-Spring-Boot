package com.test.bakery.controller;

import com.test.bakery.DTO.BasketDTO;
import com.test.bakery.DTO.DeleteOTP;
import com.test.bakery.DTO.UpdateOTP;
import com.test.bakery.model.Order;
import com.test.bakery.model.OrderToProduct;
import com.test.bakery.model.Product;
import com.test.bakery.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/")
@CrossOrigin()
public class ProductController {
    Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * @return amount of products in a basket
     */
    @PostMapping("")
    public int addProductToBasket(@RequestBody BasketDTO basket) {
        Long orderId = productService.getOrderInfo(basket.getLogin());
        return productService.addProductToBasket(orderId,
                basket.getProductId(), basket.getAmount());
    }

    @DeleteMapping("cart")
    public ResponseEntity<String> deleteOtp(@RequestBody DeleteOTP deleteOTP) {
        Long orderId = productService.getOrderInfo(deleteOTP.getLogin());
        OrderToProduct otp = productService.getByOrderOrderIdAndProductProductId(
                orderId,
                deleteOTP.getProductId());
        productService.deleteOtp(otp);
        return ResponseEntity.status(HttpStatus.OK).body("Otp deleted successfully");
    }

    //@TODO
    //Вместо спика продуктов передавать мапу из продактИД - prodAmount
    /**
     * Order's status becomes "Ongoing" and sets total price
     */
    @PutMapping("cart")
    public Order makeOrder(@RequestBody UpdateOTP updateOTP)
    {
        Long orderId = productService.getOrderInfo(updateOTP.getLogin());
        return productService.updateOTP(updateOTP.getProducts(), orderId, updateOTP.getTotalPrice());
    }

}
