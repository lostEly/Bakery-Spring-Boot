package com.test.bakery.DTO;

import com.test.bakery.model.Product;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class UpdateOTP {
    @NotEmpty(message = "products are mandatory")
    private List<Product> products;
    @NotEmpty(message = "login is mandatory")
    private String login;
    @NotEmpty(message = "totalPrice is mandatory")
    private double totalPrice;
}
