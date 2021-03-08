package com.test.bakery.DTO;

import lombok.Data;

import java.util.Map;

@Data
public class EditOrderStatusDTO {
    private Map<String, String> orderStatusMap;
}