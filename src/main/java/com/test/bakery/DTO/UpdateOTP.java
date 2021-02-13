package com.test.bakery.DTO;

import com.test.bakery.model.Product;
import lombok.Data;

import java.util.List;

//получить список товаров из корзины, отправить их кол-во, стоимость, и ТОТАЛ
@Data
public class UpdateOTP {
    private List<Product> products;
    private String login;
    private double totalPrice;
}
