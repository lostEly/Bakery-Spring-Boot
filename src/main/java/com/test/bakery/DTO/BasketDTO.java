package com.test.bakery.DTO;

import lombok.Data;

@Data
public class BasketDTO {
    private String login;
    private Long productId;
    private int amount;
}
