package com.web.backend.dto;

import com.fasterxml.jackson.annotation.*;
import com.web.backend.repository.entity.*;
import lombok.*;
import lombok.experimental.*;

import java.io.*;
import java.util.*;

/**
 * DTO for {@link com.web.backend.repository.entity.ProductUtil}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductUtilDto implements Serializable {
    private Boolean status;
    private long productId;
    private double price;
    private String color;
    private String size;
    private int quantity;
    private List<ProductUtil> productUtils;
}