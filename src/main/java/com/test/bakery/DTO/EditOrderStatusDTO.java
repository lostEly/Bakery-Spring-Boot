package com.test.bakery.DTO;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
public class EditOrderStatusDTO {
    @NotNull
    private Map<String, String> orderStatusMap;
}