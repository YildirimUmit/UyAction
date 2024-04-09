package com.web.backend.repository.entity;

import lombok.*;

import javax.persistence.*;
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
    @Column(name = "product_id", nullable = false)
    private long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "is_deleted", nullable = false)
    private boolean deleted;

    @Column(name = "color")
    private String color;

    @ManyToMany
    @JoinTable(name = "product_categories",
            joinColumns = @JoinColumn(name = "product_product_id"),
            inverseJoinColumns = @JoinColumn(name = "categories_id"))
    private List<Category> categories = new ArrayList<>();

}
