package com.web.backend.repository.entity;

import com.fasterxml.jackson.annotation.*;
import com.web.backend.enums.*;
import com.web.backend.enums.Size;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "product_util")
public class ProductUtil  extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_util_id", nullable = false)
    private long productUtilId;

    @Column(name = "product_id", nullable = false)
    private long productId;

    @Column(name = "price", nullable = false)
    private long price;

    @Column(name = "color")
    private String color;

    @Enumerated(EnumType.STRING)
    @Column(name = "size")
    public Size size;

    @Column(name = "quantity", nullable = false)
    private int quantity;


    @Column(name= "image",columnDefinition = "Text")
    private String image;




}
