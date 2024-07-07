package com.web.backend.request;

import com.web.backend.repository.entity.*;
import lombok.Data;

import java.util.*;

@Data
public class ProductCreateRequest {
    private String productName;
    private Integer quantity;
    private String brand;
}
