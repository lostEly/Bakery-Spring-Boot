package com.test.bakery.DTO;

import lombok.Data;

@Data
public class AddProductDTO {
    String productName;
    String description;
    Double price;
    String category;
    String image;
}
