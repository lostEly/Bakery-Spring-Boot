package com.test.bakery.DTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AddProductDTO {
    @NotEmpty(message = "productName is mandatory")
    String productName;
    @NotEmpty(message = "description is mandatory")
    String description;
    @NotEmpty(message = "price is mandatory")
    Double price;
    @NotEmpty(message = "category is mandatory")
    String category;
    @NotEmpty(message = "image is mandatory")
    String image;
}
