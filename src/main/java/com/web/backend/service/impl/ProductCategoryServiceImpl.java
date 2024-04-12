package com.web.backend.service.impl;


import com.web.backend.dto.*;
import com.web.backend.enums.*;
import com.web.backend.exception.enums.*;
import com.web.backend.exception.exceptions.*;
import com.web.backend.repository.*;
import com.web.backend.repository.entity.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.modelmapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl {

    private final ProductCategoryRepository productCategoryRepository;

    @Autowired
    ModelMapper modelMapper1;


    public ProductCategory createProductCategory(Language language, ProductCategory productCategory){
        log.debug("[{}][createCategory] -> request: {}", this.getClass().getSimpleName(), productCategory);
        ProductCategory productCategory1 = null;
        try {
            productCategory1=productCategoryRepository.save(productCategory);
            return  productCategory1;
        }catch (Exception exception){
            throw new CategoryNotCreatedException(language, FriendlyMessageCodes.NOT_CREATED_EXCEPTION, "Product category request: " + productCategory.toString());
        }
    }

}
