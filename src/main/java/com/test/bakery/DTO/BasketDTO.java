package com.test.bakery.DTO;

import lombok.Data;

@Data
public class BasketDTO {
    // изменил с public, может не работать)
    private Long orderId;
    private String productName;
    private int amount;
}
