package com.web.backend.repository;

import com.web.backend.dto.*;
import com.web.backend.repository.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.history.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{


    @Query("select c from Category c where c.id = ?1 and c.status=false")
    @Override
    Optional<Category> findById(Long aLong);

    @Query("select c from Category c where c.id = ?1 and c.status = true")
    Optional<Category> findByIdAndStatusTrue(Long id);


    @Transactional
    @Modifying
    @Query("update Category c set c.description = :#{#category.description}, c.catagoryName =:#{#category.catagoryName}, c.status = :#{#category.status} , c.updatedBy=:#{#category.updatedBy},c.updateAt=:#{#category.updateAt}  where c.id = :#{#category.id}")
    int updateDescriptionAndCatagoryNameAndStatusById(Category category);

}