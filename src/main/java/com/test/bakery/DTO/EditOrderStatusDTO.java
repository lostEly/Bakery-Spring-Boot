package com.test.bakery.DTO;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class EditOrderStatusDTO {
//    private List<Long> orderId;
//    private List<String> statusName;
    private Map<Long, String> orderStatusMap;
}