package com.web.backend.repository.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;


@Data
@Builder
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false,insertable = true,updatable = false)
    private Long productId;


    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "product_id")
    private List<ProductUtil> productUtils = new ArrayList<ProductUtil>();

    @Column(name = "product_name")
    private String productName;


    @Column(name = "brand")
    private String brand;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "product_id")
    private List<ProductCategory> productCategories = new ArrayList<>();

}
