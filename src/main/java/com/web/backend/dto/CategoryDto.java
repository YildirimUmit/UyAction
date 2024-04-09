package com.web.backend.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import lombok.experimental.*;

import java.io.*;

/**
 * DTO for {@link com.web.backend.repository.entity.Category}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CategoryDto implements Serializable {
    private Long id;
    private Boolean status;
    private String catagoryName;
    private String description;
}