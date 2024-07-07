package com.web.backend.dto;

import com.fasterxml.jackson.annotation.*;
import com.web.backend.enums.*;
import lombok.*;
import lombok.experimental.*;

import java.io.*;

/**
 * DTO for {@link com.web.backend.repository.entity.ProductUtil}
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductUtilDto implements Serializable {
    private boolean status;
    private long productUtilId;
    private long productId;
    private long price;
    private String color;
    private Size size;
    private int quantity;
    private String image;
}