package com.test.bakery.DTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OrderIdDTO {
    @NotEmpty(message = "orderId is mandatory")
    private Long orderId;
}
