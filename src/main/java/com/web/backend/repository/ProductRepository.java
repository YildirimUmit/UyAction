package com.web.backend.repository;


import com.web.backend.repository.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;


public interface ProductRepository extends JpaRepository<Product, Long> {

    Product getByProductIdAndStatusFalse(Long productId);

    List<Product> getAllByStatusFalse();



}
