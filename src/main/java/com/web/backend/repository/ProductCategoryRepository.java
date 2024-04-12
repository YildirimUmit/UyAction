package com.web.backend.repository;

import com.web.backend.repository.entity.*;
import org.springframework.data.jpa.repository.*;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}