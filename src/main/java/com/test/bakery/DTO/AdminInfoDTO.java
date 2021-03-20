package com.test.bakery.DTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AdminInfoDTO {
    @NotEmpty
    private String str;
}
