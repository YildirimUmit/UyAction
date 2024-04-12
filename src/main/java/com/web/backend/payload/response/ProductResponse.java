package com.web.backend.payload.response;

import com.web.backend.repository.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.*;

@Data
@Builder
@AllArgsConstructor
public class ProductResponse {
    private Long productId;
    private String productName;
    private Long productCreatedDate;
    private Long productUpdatedDate;
    private List<ProductUtil> productUtils;
    private  List<Category> categories;
}
