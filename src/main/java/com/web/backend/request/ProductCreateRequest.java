package com.web.backend.request;

import lombok.Data;

@Data
public class ProductCreateRequest {
    private String productName;
    private Integer quantity;
    private String brand;

}
